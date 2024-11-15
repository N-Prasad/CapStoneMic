package com.prodata.inventory_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodata.inventory_app.entity.Inventory;
import com.prodata.inventory_app.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventories")
@Validated
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<List<Inventory>> getAllInventories() {
		List<Inventory> inventories = inventoryService.getAllInventories();
		return ResponseEntity.ok(inventories);
	}

	@GetMapping("/{inventoryId}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long inventoryId) {
		Inventory inventory = inventoryService.getInventoryById(inventoryId);
		return ResponseEntity.ok(inventory);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Long productId) {
		Inventory inventory = inventoryService.getInventoryByProductId(productId);
		return ResponseEntity.ok(inventory);
	}

	@PostMapping
	public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
		Inventory createdInventory = inventoryService.addInventory(inventory);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
	}

	@PutMapping("/{inventoryId}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long inventoryId,
			@Valid @RequestBody Inventory updatedInventory) {
		Inventory inventory = inventoryService.updateInventory(inventoryId, updatedInventory);
		return ResponseEntity.ok(inventory);
	}

	@DeleteMapping("/{inventoryId}")
	public ResponseEntity<Void> deleteInventory(@PathVariable Long inventoryId) {
		inventoryService.deleteInventory(inventoryId);
		return ResponseEntity.noContent().build();
	}

}