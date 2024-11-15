package com.prodata.product_app.entity;

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
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@NotEmpty(message = "Product Name should not be empty")
	private String productName;
	
	@NotEmpty(message = "Product Description should not be empty")
	private String productDescription;
	
	@NotNull
	@Positive(message = "Product price must be with positive value")
	private Double productPrice;
	
	public Product() {
		super();
	}

	public Product(Long productId, @NotEmpty(message = "Product Name should not be empty") String productName,
			@NotEmpty(message = "Product Description should not be empty") String productDescription,
			@NotNull @Positive(message = "Product price must be with positive value") Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	public Product(@NotEmpty(message = "Product Name should not be empty") String productName,
			@NotEmpty(message = "Product Description should not be empty") String productDescription,
			@NotNull @Positive(message = "Product price must be with positive value") Double productPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productDescription, productId, productName, productPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName)
				&& Objects.equals(productPrice, other.productPrice);
	}
//
//	@Override
//	public String toString() {
//		return "Product [" + (productId != null ? "productId=" + productId + ", " : "")
//				+ (productName != null ? "productName=" + productName + ", " : "")
//				+ (productDescription != null ? "productDescription=" + productDescription + ", " : "")
//				+ (productPrice != null ? "productPrice=" + productPrice : "") + "]";
//	}

	@Override
	public String toString() {
		return "Product [productId=" + Objects.toString(productId, "null") + ", productName="
				+ Objects.toString(productName, "null") + ", productDescription="
				+ Objects.toString(productDescription, "null") + ", productPrice="
				+ Objects.toString(productPrice, "null") + "]";
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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}
