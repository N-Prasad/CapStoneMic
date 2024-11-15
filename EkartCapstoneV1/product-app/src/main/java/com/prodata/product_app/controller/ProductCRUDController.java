package com.prodata.product_app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prodata.product_app.entity.Product;
import com.prodata.product_app.exception.ProductNotFoundException;
import com.prodata.product_app.service.ProductCRUDService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductCRUDController {

	@Autowired
	private ProductCRUDService prodService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = prodService.fetchAllProducts();
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();// 204 No Content
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return prodService.getProductById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new ProductNotFoundException("product not found with id " + id));
	}

//	@GetMapping("/{name}")
//	public ResponseEntity<Product> getProductByName(@PathVariable String name) {
//		return prodService.getProductByName(name).map(ResponseEntity::ok)
//				.orElseThrow(() -> new ProductNotFoundException("product not found with name " + name));
//	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {// content sent as part of @RequestBody
		Product savedProduct = prodService.saveProduct(product);		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getProductId()).toUri();
		return ResponseEntity.created(location).body(savedProduct);//201 Created
		//return ResponseEntity<>(savedProduct, HttpStatus.OK); // 200 OK 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product productDetail) {
		Product product = prodService.getProductById(id).orElseThrow(() ->new ProductNotFoundException("product not found with id " + id));
		
		product.setProductName(productDetail.getProductName());
		product.setProductDescription(productDetail.getProductDescription());
		product.setProductPrice(productDetail.getProductPrice());
		
		Product updatedProd = prodService.saveProduct(product);
		return new ResponseEntity<>(updatedProd, HttpStatus.OK);		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		Product product = prodService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("product not found with id " + id));
		prodService.deleteProduct(product);
		return ResponseEntity.noContent().build();//204 No content as per industry standard and performance wise it is good
		
	}
}
