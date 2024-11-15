package com.prodata.inventory_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodata.inventory_app.entity.Inventory;
import com.prodata.inventory_app.exception.InventoryNotFoundException;
import com.prodata.inventory_app.repository.InventoryCrudRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryCrudRepository inventoryRepository;

	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	public Inventory getInventoryById(Long inventoryId) {
		return inventoryRepository.findById(inventoryId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found with ID " + inventoryId));
	}

	public Inventory getInventoryByProductId(Long productId) {
		return inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found for Product ID " + productId));
	}

	public Inventory addInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	public Inventory updateInventory(Long inventoryId, Inventory updatedInventory) {
		Inventory existingInventory = getInventoryById(inventoryId);
		existingInventory.setProductId(updatedInventory.getProductId());
		existingInventory.setQuantity(updatedInventory.getQuantity());
		return inventoryRepository.save(existingInventory);
	}

	public void deleteInventory(Long inventoryId) {
		Inventory existingInventory = getInventoryById(inventoryId);
		inventoryRepository.delete(existingInventory);
	}

}
