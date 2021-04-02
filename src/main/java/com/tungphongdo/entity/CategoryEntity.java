package com.tungphongdo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryEntity")
	private Set<ProductEntity> productEntities = new HashSet<ProductEntity>();

	public CategoryEntity(String categoryName, Set<ProductEntity> productEntities) {
		super();
		this.categoryName = categoryName;
		this.productEntities = productEntities;
	}

	public CategoryEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(Set<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}
	
	

}
