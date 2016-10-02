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
	// T Create a private Address field 
	// REMEMBER TO ANNOTATE IT
	// Use Eclipse to generate accessors for it.
	//-
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	//+
	// Delete city and country
	String city;
	String country;
	
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
	//-
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	//+
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getId() {
		return id;
	}
	
}
