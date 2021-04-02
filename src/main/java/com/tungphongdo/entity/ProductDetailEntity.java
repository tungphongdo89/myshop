package com.tungphongdo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")
public class ProductDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "productAmount")
	private int productAmount;
	
	@Column(name = "date")
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	private SizeEntity sizeEntity;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private ColorEntity colorEntity;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "productDetailEntity")
	private Set<ProductDetailBillEntity> productDetailBillEntities = new HashSet<ProductDetailBillEntity>();

	public ProductDetailEntity(int productAmount, String date, ProductEntity productEntity, SizeEntity sizeEntity,
			ColorEntity colorEntity, Set<ProductDetailBillEntity> productDetailBillEntities) {
		super();
		this.productAmount = productAmount;
		this.date = date;
		this.productEntity = productEntity;
		this.sizeEntity = sizeEntity;
		this.colorEntity = colorEntity;
		this.productDetailBillEntities = productDetailBillEntities;
	}

	public ProductDetailEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public SizeEntity getSizeEntity() {
		return sizeEntity;
	}

	public void setSizeEntity(SizeEntity sizeEntity) {
		this.sizeEntity = sizeEntity;
	}

	public ColorEntity getColorEntity() {
		return colorEntity;
	}

	public void setColorEntity(ColorEntity colorEntity) {
		this.colorEntity = colorEntity;
	}

	public Set<ProductDetailBillEntity> getProductDetailBillEntities() {
		return productDetailBillEntities;
	}

	public void setProductDetailBillEntities(Set<ProductDetailBillEntity> productDetailBillEntities) {
		this.productDetailBillEntities = productDetailBillEntities;
	}

	
	

}
