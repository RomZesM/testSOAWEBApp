package com.romTestProj.SOAWebAppTest.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	@Size(min = 2, max = 150, message = "Name length must be between 2 to 150 characters")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public Person(){
	
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
	
	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
