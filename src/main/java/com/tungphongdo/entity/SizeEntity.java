package com.tungphongdo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class SizeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "sizeName")
	private String sizeName;
	
	@OneToMany(mappedBy = "sizeEntity")
	private Set<ProductDetailEntity> productDetailEntities  = new HashSet<ProductDetailEntity>();

	public SizeEntity(String sizeName, Set<ProductDetailEntity> productDetailEntities) {
		super();
		this.sizeName = sizeName;
		this.productDetailEntities = productDetailEntities;
	}

	public SizeEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Set<ProductDetailEntity> getProductDetailEntities() {
		return productDetailEntities;
	}

	public void setProductDetailEntities(Set<ProductDetailEntity> productDetailEntities) {
		this.productDetailEntities = productDetailEntities;
	}
	
	
}
