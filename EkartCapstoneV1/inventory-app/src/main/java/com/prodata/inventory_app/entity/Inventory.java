package com.prodata.inventory_app.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;

	@NotNull(message = "Product ID cannot be null")
	private Long productId;

	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;

	public Inventory() {

	}

	public Inventory(Long inventoryId, @NotNull(message = "Product ID cannot be null") Long productId,
			@Min(value = 0, message = "Quantity cannot be negative") Integer quantity) {
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Inventory(@NotNull(message = "Product ID cannot be null") Long productId,
			@Min(value = 0, message = "Quantity cannot be negative") Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + Objects.toString(inventoryId, "null") + ", productId="
				+ Objects.toString(productId, "null") + ", quantity=" + Objects.toString(quantity, "null") + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, productId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(inventoryId, other.inventoryId) && Objects.equals(productId, other.productId)
				&& Objects.equals(quantity, other.quantity);
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
