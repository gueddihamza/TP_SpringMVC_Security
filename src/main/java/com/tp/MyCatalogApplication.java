package com.tp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tp.dao.ProductRepository;
import com.tp.entities.Product;

@SpringBootApplication
public class MyCatalogApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	private List<Product> products=null;
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Add Products
		productRepository.save(new Product(null,"RTX 2080 TI",12000,24,null,true));
		productRepository.save(new Product(null,"Desktop Workstation",35000,20,null,true));
		productRepository.save(new Product(null,"Bimo",1,150,null,true));
		productRepository.save(new Product(null,"Face Masks",20,1400,null,true));
		
	//Show All Products
		products = productRepository.findAll();
		for(Product p : products) {
			System.out.println(p.toString());
		}
		
		//Get products with a given label
		String keyword="Face";
		products = productRepository.findByLabelContains(keyword);
		for(Product p : products) {
			System.out.println(p.toString());
		}
		
		//Get one Product with a given ID
		Product p = productRepository.findById(3L).get();
		p.toString();
}
	
}