package com.ticketmanor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity @Table(name="order_items")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 8431243797141861574L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@OneToOne
	private Sellable sellable;
	private int quantity;
	
	public OrderItem(Sellable sellable, int quantity) {
		this.setSellable(sellable);
		this.setQuantity(quantity);
	}
	/** Note default quantity is 1; you don't add 0-item entries... */
	public OrderItem(Sellable sellable) {
		this(sellable, 1);
	}
	public Sellable getSellable() {
		return sellable;
	}
	public void setSellable(Sellable sellable) {
		this.sellable = sellable;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderItem@" + hashCode() + "(" + getSellable() + " qty " + getQuantity() + ")";
	}
}