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

/** 
 * Local test: Test the logic in the given ShoppingCart, without having
 * to deploy it into any kind of container.
 * @author Ian Darwin
 */
public class ShoppingCartTest {
	
	//T AFTER the Unit Tests pass AND you have created an Interface for remote use,
	// change this from the class to the Interface (if the Refactoring doesn't do it).
	//-
	protected ShoppingCart cart;
	//+
	//R protected ShoppingCartEjb cart;
	//T Examine the other fields we have set up for you.
	static final Act act = new Act(ActType.TROUPE, "The Outliers");
	static final Venue venue = new Venue("Rectangle Square Gardens");
	static final Event evt = new Event(act, LocalDateTime.now(), venue);
	static final Sellable product = new Ticket(evt, "Front Row Centre", 12.98);

	@Before
	public void init() throws Exception {
		cart = new ShoppingCartEjb();
	}

	@Test
	public void testAddToCart() {
		//T Add 'product' to the cart, check that getOrderItems().size is 1
		//R fail("test not implemented");
		//-
		cart.addToCart(product);
		List<OrderItem> orderItems = cart.getOrderItems();
		assertEquals(1, orderItems.size());
		assertEquals(product, orderItems.get(0).getSellable());
		//+
	}

	@Test
	public void testRemoveFromCart() {
		//T Check that adding then removing a product leaves 0 items in cart
		//R fail("test not implemented");
		//-
		cart.addToCart(product);
		assertTrue(cart.removeFromCart(product));
		List<OrderItem> orderItems = cart.getOrderItems();
		assertEquals(orderItems.size(), 0);
		//+
	}

	@Test
	public void testGetCartSizeAndItemCount() {
		//T Add the product TWICE to the cart; ensure that now:
		// getCartSize() returns 1
		// getItemCount() returns 2
		//R fail("test not implemented");
		//-
		// "Make that a pair of tickets"
		cart.addToCart(product);
		cart.addToCart(product);

		// Adding an existing object should not change cart size!
		assertEquals(1, cart.getCartSize());

		// Adding an existing object should bump total quantity.
		assertEquals(2, cart.getItemCount());
		//+
	}

	@Test
	public void testGetTotalPrice() {
		assertEquals(0d, cart.getTotalPrice(), 0.001);
		cart.addToCart(product);
		assertEquals(product.getPrice(), cart.getTotalPrice(), 0.001);
		cart.addToCart(product);
		assertEquals(2 * product.getPrice(), cart.getTotalPrice(), 0.001);
	}
}
