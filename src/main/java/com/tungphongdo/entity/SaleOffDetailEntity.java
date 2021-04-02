package com.tungphongdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sale_off_detail")
public class SaleOffDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "priceOff")
	private double priceOff;
	
	@ManyToOne
	@JoinColumn(name = "sale_off_id")
	private SaleOffEntity saleOffEntity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	public SaleOffDetailEntity(double priceOff, SaleOffEntity saleOffEntity, ProductEntity productEntity) {
		super();
		this.priceOff = priceOff;
		this.saleOffEntity = saleOffEntity;
		this.productEntity = productEntity;
	}

	public SaleOffDetailEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPriceOff() {
		return priceOff;
	}

	public void setPriceOff(double priceOff) {
		this.priceOff = priceOff;
	}

	public SaleOffEntity getSaleOffEntity() {
		return saleOffEntity;
	}

	public void setSaleOffEntity(SaleOffEntity saleOffEntity) {
		this.saleOffEntity = saleOffEntity;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	
	

}
