package com.prodata.shopping_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodata.shopping_app.model.Product;

@RestController
@RequestMapping("api/Shopping")
public class ShoppingController {
	
	@Autowired
	private ProductRestConsumer feignProduct;
	
	@GetMapping("/products/all")
	public ResponseEntity<List<Product>> getAllProducts() {		
		return feignProduct.getAllProducts();
	}
	
	

}
