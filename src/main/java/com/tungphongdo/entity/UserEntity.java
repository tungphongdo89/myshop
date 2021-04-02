package com.tungphongdo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "telephone")
	private long telephone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "enabled")
	private int enabled;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "userEntity")
	private Set<UserRoleEntity> userRoleEntities = new HashSet<UserRoleEntity>();
	
	@OneToMany(mappedBy = "userEntity")
	private Set<BillEntity> billEntities = new HashSet<BillEntity>();

	

	public UserEntity(String username, String password, String address, Long telephone, String email, String gender,
			int enabled, Set<UserRoleEntity> userRoleEntities, Set<BillEntity> billEntities) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.gender = gender;
		this.enabled = enabled;
		this.userRoleEntities = userRoleEntities;
		this.billEntities = billEntities;
	}

	public UserEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleEntity> getUserRoleEntities() {
		return userRoleEntities;
	}

	public void setUserRoleEntities(Set<UserRoleEntity> userRoleEntities) {
		this.userRoleEntities = userRoleEntities;
	}

	public Set<BillEntity> getBillEntities() {
		return billEntities;
	}

	public void setBillEntities(Set<BillEntity> billEntities) {
		this.billEntities = billEntities;
	}
	
	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UserRoleEntity usersRole: this.userRoleEntities) {
			authorities.add(new SimpleGrantedAuthority(usersRole.getRoleEntity().getRoleName()));
		}
		return authorities;
	}
	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isAccountEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	

}
