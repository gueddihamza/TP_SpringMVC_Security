package com.tp.dao;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
		
	public List<Product> findByLabelContains(String keyword);
	public Page<Product> findByLabelContains(String keyword , Pageable pageable);
	}
