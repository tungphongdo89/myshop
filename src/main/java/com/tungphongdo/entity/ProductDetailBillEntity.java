package com.tungphongdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProductDetailBillEntity")
public class ProductDetailBillEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bill_id")
	private BillEntity billEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_detail_id")
	private ProductDetailEntity productDetailEntity;
	
	@Column(name = "bought_amount")
	private int boughtAmount;
	
	
	
	public ProductDetailBillEntity(BillEntity billEntity, ProductDetailEntity productDetailEntity, int boughtAmount) {
		super();
		this.billEntity = billEntity;
		this.productDetailEntity = productDetailEntity;
		this.boughtAmount = boughtAmount;
	}


	public ProductDetailBillEntity() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BillEntity getBillEntity() {
		return billEntity;
	}

	public void setBillEntity(BillEntity billEntity) {
		this.billEntity = billEntity;
	}

	public ProductDetailEntity getProductDetailEntity() {
		return productDetailEntity;
	}

	public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
		this.productDetailEntity = productDetailEntity;
	}

	public int getBoughtAmount() {
		return boughtAmount;
	}

	public void setBoughtAmount(int boughtAmount) {
		this.boughtAmount = boughtAmount;
	}

	

}
