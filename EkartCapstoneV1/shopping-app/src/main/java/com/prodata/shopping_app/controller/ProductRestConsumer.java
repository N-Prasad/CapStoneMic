package com.prodata.shopping_app.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prodata.shopping_app.model.Product;

@FeignClient("product-app")
@RequestMapping("/api/products")
public interface ProductRestConsumer {

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts();
	// ??? return type should same as actual service or body of response????

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id);

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product);

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetail);

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id);

}
