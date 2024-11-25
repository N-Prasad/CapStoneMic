package com.prodata.shopping_app.model;

import java.util.Objects;

public class Inventory {
	private Long inventoryId;
	private Long productId;
	private Integer quantity;

	public Inventory() {

	}

	public Inventory(Long inventoryId, Long productId, Integer quantity) {
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Inventory(Long productId, Integer quantity) {
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
