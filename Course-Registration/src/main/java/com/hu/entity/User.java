package com.hu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Integer enabled = 1;
	
	// constructors
	public User() {}

	public User(String userName, String password, Integer enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	// getters and setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	//toString
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + "]";
	}
	
	
	
}