package com.ticketmanor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) long id;
	
	String firstName, lastName;
	//T Create a private Address field 
	// REMEMBER TO ANNOTATE IT
	// Use Eclipse to generate accessors for it.
	//-
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	//+

	//T Delete city and country, and their accessors
//	String city;
//	String country;
	
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
	
	// For JPA
	public long getId() {
		return id;
	}
	
}
