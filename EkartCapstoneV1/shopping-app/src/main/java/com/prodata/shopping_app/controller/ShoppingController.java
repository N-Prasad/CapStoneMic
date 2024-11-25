package com.prodata.shopping_app.controller;

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

import com.prodata.shopping_app.consumer.InventoryConsumer;
import com.prodata.shopping_app.consumer.ProductRestConsumer;
import com.prodata.shopping_app.dto.ProductDetail;
import com.prodata.shopping_app.model.Inventory;
import com.prodata.shopping_app.model.Product;

@RestController
@RequestMapping("api/shopping")
public class ShoppingController {

	@Autowired
	private ProductRestConsumer feignProduct;

	@Autowired
	private InventoryConsumer feignInventory;

	@GetMapping("/getallproducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		return feignProduct.getAllProducts();
	}

	@GetMapping("/getproduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return feignProduct.getProductById(id);
	}

	// @PostMapping("/createproduct")
//	public ResponseEntity<Product> createProduct(Product product) {
//		return feignProduct.createProduct(product);		
//	}

	@PutMapping("/updateproduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return feignProduct.updateProduct(id, product);
	}

	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		return feignProduct.deleteProduct(id);
	}

	@PostMapping("/createproduct")
	public ResponseEntity<String> postMethodName(@RequestBody ProductDetail prodDetail) {
		// Create product in prod service then inventory
		Product prod = new Product(prodDetail.getName(), prodDetail.getDesc(), prodDetail.getPrice());
		ResponseEntity<Product> productResponse = feignProduct.createProduct(prod);
		if (productResponse.getStatusCode().equals(HttpStatus.CREATED)) {
			prod = productResponse.getBody();
			Inventory inventory = new Inventory(prod.getProductId(), prodDetail.getQty());
			ResponseEntity<Inventory> inventoryReponse = feignInventory.createInventory(inventory);
			if (inventoryReponse.getStatusCode().equals(HttpStatus.CREATED)) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Product and Inventory created successfully");
			}
		}
		return ResponseEntity.badRequest().build();
	}

}
