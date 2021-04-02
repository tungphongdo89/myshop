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
@Table(name = "role")
public class RoleEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "roleName")
	private String roleName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roleEntity")
	private Set<UserRoleEntity> userRoleEntities = new HashSet<UserRoleEntity>();

	public RoleEntity(String roleName, Set<UserRoleEntity> userRoleEntities) {
		super();
		this.roleName = roleName;
		this.userRoleEntities = userRoleEntities;
	}

	public RoleEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRoleEntity> getUserRoleEntities() {
		return userRoleEntities;
	}

	public void setUserRoleEntities(Set<UserRoleEntity> userRoleEntities) {
		this.userRoleEntities = userRoleEntities;
	}
	
	

}
