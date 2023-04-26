package com.security.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
private	String username;
private	String password;
private	String role;


	
	public User() {
	super();
	// TODO Auto-generated constructor stub
}
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
	

