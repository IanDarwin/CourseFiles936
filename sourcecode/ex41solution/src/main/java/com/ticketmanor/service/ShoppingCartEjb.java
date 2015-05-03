package com.ticketmanor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;

import com.ticketmanor.model.OrderItem;
import com.ticketmanor.model.Sellable;

/** A Shopping Cart implemented as a Stateful Session Bean */
@Stateful
public class ShoppingCartEjb implements ShoppingCart {
	
	private List<OrderItem> cart = new ArrayList<>(5);
	
	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#addToCart(com.ticketmanor.model.Sellable)
	 */
	@Override
	public void addToCart(Sellable s) {
		for (OrderItem item : cart) {
			Sellable sellable = item.getSellable();
			if (sellable.equals(s)) {
				item.setQuantity(item.getQuantity() + 1);
				System.out.println("EQ: " + item);
				return;
			}
		}
		OrderItem item = new OrderItem(s, 1);
		System.out.println("NF: " + item);
		cart.add(item);
	}
	
	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#removeFromCart(com.ticketmanor.model.Sellable)
	 */
	@Override
	public boolean removeFromCart(Sellable s) {
		Iterator<OrderItem> it = cart.iterator();
		while (it.hasNext()) {
			OrderItem item = it.next();
			if (item.getSellable().equals(s)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#getOrderItems()
	 */
	@Override
	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(cart);
	}
	
	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#getTotalPrice()
	 */
	@Override
	public double getTotalPrice() {
		double total = 0;
		for (OrderItem item : cart) {
			total += item.getQuantity() * item.getSellable().getPrice();
		}
		return total;
	}

	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#getCartSize()
	 */
	@Override
	public int getCartSize() {
		return cart.size();
	}

	/* (non-Javadoc)
	 * @see com.ticketmanor.service.ShoppingCart#getItemCount()
	 */
	@Override
	public int getItemCount() {
		int n = 0;
		for (OrderItem oi : cart) {
			System.out.println("GIC: " + oi);
			n += oi.getQuantity();
		}
		return n;
	}
}
