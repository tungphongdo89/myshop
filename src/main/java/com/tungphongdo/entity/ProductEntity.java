package com.tungphongdo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntity;
	
	@OneToMany(mappedBy = "productEntity")
	private Set<ProductDetailEntity> productDetailEntities = new HashSet<ProductDetailEntity>();
	
	@OneToMany(mappedBy = "productEntity")
	private Set<SaleOffDetailEntity> saleOffDetailEntities = new HashSet<SaleOffDetailEntity>();

	public ProductEntity(String productName, String description, double price, String image,
			CategoryEntity categoryEntity, Set<ProductDetailEntity> productDetailEntities,
			Set<SaleOffDetailEntity> saleOffDetailEntities) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.image = image;
		this.categoryEntity = categoryEntity;
		this.productDetailEntities = productDetailEntities;
		this.saleOffDetailEntities = saleOffDetailEntities;
	}

	public ProductEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public Set<ProductDetailEntity> getProductDetailEntities() {
		return productDetailEntities;
	}

	public void setProductDetailEntities(Set<ProductDetailEntity> productDetailEntities) {
		this.productDetailEntities = productDetailEntities;
	}

	public Set<SaleOffDetailEntity> getSaleOffDetailEntities() {
		return saleOffDetailEntities;
	}

	public void setSaleOffDetailEntities(Set<SaleOffDetailEntity> saleOffDetailEntities) {
		this.saleOffDetailEntities = saleOffDetailEntities;
	}
	
	
	
	
	
}
