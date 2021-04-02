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
@Table(name = "bill")
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "payments")
	private String payments;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "totalMoney")
	private String totalMoney;
	
	@Column(name = "status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "billEntity")
	Set<ProductDetailBillEntity> productDetailBillEntities = new HashSet<ProductDetailBillEntity>();

	public BillEntity(String date, String payments, String note, String totalMoney, UserEntity userEntity,
			Set<ProductDetailBillEntity> productDetailBillEntities) {
		super();
		this.date = date;
		this.payments = payments;
		this.note = note;
		this.totalMoney = totalMoney;
		this.userEntity = userEntity;
		this.productDetailBillEntities = productDetailBillEntities;
	}

	public BillEntity() {
		super();
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<ProductDetailBillEntity> getProductDetailBillEntities() {
		return productDetailBillEntities;
	}

	public void setProductDetailBillEntities(Set<ProductDetailBillEntity> productDetailBillEntities) {
		this.productDetailBillEntities = productDetailBillEntities;
	}

	
	
	

}
