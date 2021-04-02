package com.tungphongdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "about")
public class AboutEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "self")
	private String self;
	
	@Column(name = "history")
	private String history;
	
	@Column(name = "hiring")
	private String hiring;

	public AboutEntity() {
		super();
	}

	public AboutEntity(int id, String self, String history, String hiring) {
		super();
		this.id = id;
		this.self = self;
		this.history = history;
		this.hiring = hiring;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getHiring() {
		return hiring;
	}

	public void setHiring(String hiring) {
		this.hiring = hiring;
	}
	
	

}
