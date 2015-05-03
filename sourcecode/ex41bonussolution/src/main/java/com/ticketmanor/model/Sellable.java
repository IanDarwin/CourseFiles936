package com.ticketmanor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/** Interface that all sellable products implement;
 * can't be an interface because JPA requires classes in relationships
 * (which makes sense...)
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Sellable {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	double price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	/** Get the price of quantity one of this item */
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
