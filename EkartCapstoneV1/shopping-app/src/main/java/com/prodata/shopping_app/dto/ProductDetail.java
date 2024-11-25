package com.prodata.shopping_app.dto;

public class ProductDetail {

	private Long id;
	private String name;
	private String desc;
	private Double price;
	private Integer qty;

	public ProductDetail(Long id, String name, String desc, Double price, Integer qty) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.qty = qty;
	}

	public ProductDetail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public ProductDetail(String name, String desc, Double price) {
		this.name = name;
		this.desc = desc;
		this.price = price;
	}

	public ProductDetail(Long id, String name, String desc, Double price) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}

}
