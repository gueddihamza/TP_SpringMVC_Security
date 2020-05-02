package com.tp.web;

import java.awt.print.Pageable;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tp.dao.ProductRepository;
import com.tp.entities.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping(path = "/index")
	public String index() {
		return "index";
	}

	@GetMapping(path = "/products")
	public String products(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		Page<Product> productsPage = productRepository.findByLabelContains(keyword, PageRequest.of(page, size));
		model.addAttribute("productsListPage", productsPage);
		model.addAttribute("pages", new int[productsPage.getTotalPages()]);
		model.addAttribute("size", size);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		return "products";
	}

	@GetMapping(path = "/deleteProduct")
	public String delete(Long id, String page, String size, String keyword) {
		productRepository.deleteById(id);
		return "redirect:/products?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}

	@GetMapping(path = "/productForm")
	public String formProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("mode","new");
		return "productForm";
	}

	@PostMapping(path = "/saveProduct")
	public String saveProduct(Model model ,@Valid Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "productForm";
		}
		productRepository.save(product);
		model.addAttribute("product",product);
		return "confirmation";
	}

	@GetMapping(path = "/editProduct")
	public String editProduct(Model model, Long id) {
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		model.addAttribute("mode","edit");
		return "productForm";
	}
}
