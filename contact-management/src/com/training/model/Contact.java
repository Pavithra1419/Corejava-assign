package com.training.model;

import java.time.LocalDate;

public class Contact {
	
	private String name;
	private String address;
	private int mobileNum;
	private String profile;
	private LocalDate dob;
	private String email;
	private String group;
	
	
	public Contact() {
		super();
	}


	public Contact(String name, String address, int mobileNum, String profile, LocalDate dob, String email,
			String group) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNum = mobileNum;
		this.profile = profile;
		this.dob = dob;
		this.email = email;
		this.group = group;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getMobileNum() {
		return mobileNum;
	}


	public void setMobileNum(int mobileNum) {
		this.mobileNum = mobileNum;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return "Contact [name=" + name + ", address=" + address + ", mobileNum=" + mobileNum + ", profile=" + profile
				+ ", dob=" + dob + ", email=" + email + ", group=" + group + "]";
	}
	
	
	
	
	

}
