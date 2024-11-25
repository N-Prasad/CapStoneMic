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

import com.prodata.shopping_app.model.Inventory;

@FeignClient("inventory-app")
public interface InventoryConsumer {
	
	@GetMapping("/api/inventories/all")
	public ResponseEntity<List<Inventory>> getAllInventories();

	@GetMapping("/api/inventories/{inventoryId}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long inventoryId);
	
	@GetMapping("/api/inventories/product/{productId}")
	public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Long productId);

	@PostMapping("/api/inventories")
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory);

	@PutMapping("/api/inventories/{inventoryId}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long inventoryId, @RequestBody Inventory updatedInventory);

	@DeleteMapping("/api/inventories/{inventoryId}")
	public ResponseEntity<Void> deleteInventory(@PathVariable Long inventoryId);

}
