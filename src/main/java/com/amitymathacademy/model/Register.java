package com.amitymathacademy.model;

import java.util.List;

public class Register {

	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	List<String> courses;

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(email);
		for(String s : courses){
			sb.append(" "+s);
		}
		return sb.toString();
	}
}

/**
 * 
 * 
 create table register(id int primary key auto_increment, email varchar(50), 
  course varchar(50), reg_date long, created_date varchar(30));
 */
