package com.tungphongdo.controller;

public class Cart {
	private int productDetail_id;
	private int product_id;
	private int size_id;
	private int color_id;
	private String productName;
	private String sizeName;
	private String colorName;
	private double price;
	private int amount;

	public Cart(int productDetail_id, int product_id, int size_id, int color_id, String productName, String sizeName,
			String colorName, double price, int amount) {
		super();
		this.productDetail_id = productDetail_id;
		this.product_id = product_id;
		this.size_id = size_id;
		this.color_id = color_id;
		this.productName = productName;
		this.sizeName = sizeName;
		this.colorName = colorName;
		this.price = price;
		this.amount = amount;
	}

	public Cart() {
		super();
	}
	
	public int getProductDetail_id() {
		return productDetail_id;
	}

	public void setProductDetail_id(int productDetail_id) {
		this.productDetail_id = productDetail_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getSize_id() {
		return size_id;
	}

	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}

	public int getColor_id() {
		return color_id;
	}

	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
