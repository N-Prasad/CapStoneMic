package com.prodata.order_app.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lineItemId;

	@NotNull
	private Long productId;

	@NotEmpty(message = "Product Name should not be empty")
	private String productName;

	@NotNull
	@Positive
	private Integer quantity;

	@NotNull
	@Positive
	private Double price;

	public LineItem() {
		// TODO Auto-generated constructor stub
	}

	public LineItem(@NotNull Long productId, @NotEmpty(message = "Product Name should not be empty") String productName,
			@NotNull @Positive Integer quantity, @NotNull @Positive Double price) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lineItemId, price, productId, productName, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		return Objects.equals(lineItemId, other.lineItemId) && Objects.equals(price, other.price)
				&& Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName)
				&& Objects.equals(quantity, other.quantity);
	}

	public Long getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(Long lineItemId) {
		this.lineItemId = lineItemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
