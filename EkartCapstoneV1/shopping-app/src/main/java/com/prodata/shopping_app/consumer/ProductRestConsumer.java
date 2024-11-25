package com.prodata.shopping_app.consumer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prodata.shopping_app.model.Product;

@FeignClient("product-app")
public interface ProductRestConsumer {

	@GetMapping("/api/products")
	public ResponseEntity<List<Product>> getAllProducts();
	// ??? return type should same as actual service or body of response????

	@GetMapping("/api/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id);

	@PostMapping("/api/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product);

	@PutMapping("/api/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetail);

	@DeleteMapping("/api/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id);

}
