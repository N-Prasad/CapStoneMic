package com.prodata.product_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodata.product_app.entity.Product;
import com.prodata.product_app.repository.ProductCRUDRepository;

@Service
public class ProductCRUDService {

	@Autowired
	ProductCRUDRepository prodRepo;

	public Optional<Product> getProductById(Long id) {
		Optional<Product> pro = prodRepo.findById(id);
		return pro;
	}

	public Optional<Product> getProductByName(String productName) {
		Optional<Product> prod = prodRepo.findByProductName(productName);
		return prod;
	}

	// getAll
	public List<Product> fetchAllProducts() {
		return prodRepo.findAll();
	}

	public Product saveProduct(Product product) {
		return prodRepo.save(product);
	}

	public void deleteProduct(Product product) {
		prodRepo.delete(product);
	}

}
