package com.ticketmanor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	//T Create an id field as well as fields like street, city, state and country.
	//-
	@Id @GeneratedValue(strategy=GenerationType.AUTO) long id;
	String street;
	String city;
	String state;
	String country;
	//+
	
	public Address() {
		// empty
	}
	
	public Address(String street, String city, String state, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	//T Get your IDE to generate accessors for all EXCEPT setId(), or,
	// just generate them all and delete setId(). We don't want it
	// since the ID is auto-generated, and since you really don't want a rogue web
	// page to be able to change the id.
	//-
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	//+
	
	//T Optional: generate hashCode/equals, toString(), etc.
}
