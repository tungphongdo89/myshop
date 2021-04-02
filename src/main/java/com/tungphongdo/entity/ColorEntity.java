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
@Table(name = "color")
public class ColorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "colorName")
	private String colorName;
	
	@OneToMany(mappedBy = "colorEntity")
	private Set<ProductDetailEntity> productDetailEntities  = new HashSet<ProductDetailEntity>();

	public ColorEntity(String colorName, Set<ProductDetailEntity> productDetailEntities) {
		super();
		this.colorName = colorName;
		this.productDetailEntities = productDetailEntities;
	}

	public ColorEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Set<ProductDetailEntity> getProductDetailEntities() {
		return productDetailEntities;
	}

	public void setProductDetailEntities(Set<ProductDetailEntity> productDetailEntities) {
		this.productDetailEntities = productDetailEntities;
	}
	
	
}
