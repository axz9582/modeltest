package com.amitymathacademy.model;

public class StudentUser {

	int id;
	String email;
	String password;
	String firstName;
	String lastName;
	String verifyCode;
	String pwdResetCode;//use to reset password, will be deleted after reset
	String phone;
	int yearLevel;
	boolean active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getYearLevel() {
		return yearLevel;
	}
	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	public String toString(){
		return "??" +id+"/"+email+"/"+ pwdResetCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwdResetCode() {
		return pwdResetCode;
	}
	public void setPwdResetCode(String pwdResetCode) {
		this.pwdResetCode = pwdResetCode;
	}
}
