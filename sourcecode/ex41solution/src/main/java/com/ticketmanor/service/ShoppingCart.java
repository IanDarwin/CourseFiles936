package com.ticketmanor.service;

import java.util.List;

import com.ticketmanor.model.OrderItem;
import com.ticketmanor.model.Sellable;

public interface ShoppingCart {

	/** Add the item to the cart; if the card already has an OrderItem
	 * for this Sellable we increment its quantity, else, we make
	 * a new OrderItem for it, and stash that in the card.
	 * @param s The Sellable item (Ticket, Recording, etc.)
	 */
	public abstract void addToCart(Sellable s);

	/**
	 * Remove the given Sellable from the Cart.
	 * @param s The Sellable to be removed from the Cart.
	 * @return True if the remove succeeded, false otherwise.
	 */
	public abstract boolean removeFromCart(Sellable s);

	/**
	 * Get a list of items in the cart.
	 * @return a list of items in the cart.
	 */
	public abstract List<OrderItem> getOrderItems();

	/**
	 * Get the total number of items in the cart
	 * considering the quantity of each item.
	 * @return The total number of items in the cart
	 */
	public abstract int getItemCount();
	
	/**
	 * Get the number of distinct items in the cart
	 * not considering the quantity of each item selected.
	 * Basically the length of the List.
	 * @return The number of distinct items in the cart.
	 */
	public abstract int getCartSize();

	/**
	 * Get the total price of objects in the cart.
	 * @return The price in $ of all items.
	 */
	public abstract double getTotalPrice();

}