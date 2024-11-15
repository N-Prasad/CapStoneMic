package com.prodata.shopping_app.model;

public class Product {

	private Long productId;
	private String productName;
	private String productDescription;
	private Double productPrice;

	public Product(String productName, String productDescription, Double productPrice) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	public Product(Long productId, String productName, String productDescription, Double productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
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
