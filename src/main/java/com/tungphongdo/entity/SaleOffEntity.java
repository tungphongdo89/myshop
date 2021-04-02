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
@Table(name = "sale_off")
public class SaleOffEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "saleName")
	private String saleName;
	
	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "endTime")
	private String endTime;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@OneToMany(mappedBy = "saleOffEntity")
	private Set<SaleOffDetailEntity> saleOffDetailEntities = new HashSet<SaleOffDetailEntity>();

	public SaleOffEntity(String saleName, String startTime, String endTime, String description, String image,
			Set<SaleOffDetailEntity> saleOffDetailEntities) {
		super();
		this.saleName = saleName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.image = image;
		this.saleOffDetailEntities = saleOffDetailEntities;
	}

	public SaleOffEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<SaleOffDetailEntity> getSaleOffDetailEntities() {
		return saleOffDetailEntities;
	}

	public void setSaleOffDetailEntities(Set<SaleOffDetailEntity> saleOffDetailEntities) {
		this.saleOffDetailEntities = saleOffDetailEntities;
	}
	
	

}
