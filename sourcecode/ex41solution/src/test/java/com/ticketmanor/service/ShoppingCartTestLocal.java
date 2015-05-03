package com.ticketmanor.service;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.OrderItem;
import com.ticketmanor.model.Sellable;
import com.ticketmanor.model.Ticket;
import com.ticketmanor.model.Venue;

public class ShoppingCartTestLocal {
	
	private ShoppingCart cart;
	private Sellable product;
	static final Act act = new Act(ActType.TROUPE, "The Outliers");
	static final Venue venue = new Venue("Rectangle Square Gardens");
	static final Event evt = new Event(act, LocalDateTime.now(), venue);

	@Before
	public void init() {
		cart = new ShoppingCartEjb();
		product = new Ticket(evt, "Front Row Centre", 12.98);
	}

	@Test
	public void testAddToCart() {
		cart.addToCart(product);
		List<OrderItem> orderItems = cart.getOrderItems();
		assertEquals(orderItems.size(), 1);
		assertEquals(orderItems.get(0).getSellable(), product);
	}

	@Test
	public void testRemoveFromCart() {
		cart.addToCart(product);
		assertTrue(cart.removeFromCart(product));
		List<OrderItem> orderItems = cart.getOrderItems();
		assertEquals(orderItems.size(), 0);
	}

	@Test
	public void testGetTotalPrice() {
		assertEquals(product.getPrice(), cart.getTotalPrice(), 0.001);
		cart.addToCart(product);
		assertEquals(product.getPrice(), 2*cart.getTotalPrice(), 0.001);
	}

	@Test
	public void testGetCartSizeAndItemCount() {
		// "Make that a pair of tickets"
		cart.addToCart(product);
		cart.addToCart(product);

		// Adding an existing object should not change cart size!
		assertEquals(1, cart.getCartSize());

		// Adding an existing object should bump total quantity.
		assertEquals(2, cart.getItemCount());
	}

}
