package com.models;

public class Customer {

	private String firstname;
	private String lastname;
	
	public Customer() {
		
	}

	public Customer(String firstname, String lastname) {
//		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	
}
