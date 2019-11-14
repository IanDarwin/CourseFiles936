package com.ticketmanor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.ticketmanor.model.OrderItem;
import com.ticketmanor.model.Sellable;

/** A Shopping Cart implemented as a Stateful Session Bean */
@Stateful
public class ShoppingCartEjb implements ShoppingCart {
	
	private List<OrderItem> cart = new ArrayList<>(5);
	
	//T What is the default transactional state for Stateful EJBs,
	// and why do we not need a Transaction annotation on this method?
	@Override
	public void addToCart(Sellable s) {
		for (OrderItem item : cart) {
			Sellable sellable = item.getSellable();
			if (sellable.getPrice() == null) {
				throw new IllegalArgumentException("Cannot add Sellable with null Price!");
			}
			if (sellable.equals(s)) {
				item.setQuantity(item.getQuantity() + 1);
				return;
			}
		}
		OrderItem item = new OrderItem(s, 1);
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
		//T Iterate over the OrderItems in 'cart'; for each one, add its selling
		// price (quantity multiplied by sellable.price) into 'total'.
		//-
		for (OrderItem item : cart) {
			if (item.getSellable() == null) {
				throw new IllegalArgumentException("Cart item with null Sellable!");
			}
			if (item.getSellable().getPrice() == null) {
				throw new IllegalArgumentException("Cart item with Sellable with null Price!");
			}
			total += item.getQuantity() * item.getSellable().getPrice();
		}
		//+
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
			n += oi.getQuantity();
		}
		return n;
	}

	//T Imagine that there is a saveCartForLater() method that persists
	// the user's cart into the database with JPA. What annotation(s) would it need?
	//-
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	//+
	public void saveCartForLater() {
		// This would write cart contents to JPA
	}

	//T @Remove methods may not be transactional. What additional annotation
	// should be applied here to say that we don't want Tx for this method?
	@Remove
	//-
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	//+
	public void close() {
		//
	}
}
