package com.ticketmanor.service;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;
import com.ticketmanor.model.Venue;

/** A plain JUnit test for the arithmetic and logic in the Cart */
public class ShoppingCartEjbUnitTest {

	ShoppingCartEjb ejb;
	
	@Before
	public void setUp() throws Exception {
		ejb = new ShoppingCartEjb();
	}

	@Test
	public void testCart() {
		Ticket t = mockTicket();
		
		ejb.addToCart(t);
		assertEquals("items list size", 1, ejb.getOrderItems().size());
		assertEquals("total price", 123.45, ejb.getTotalPrice(), 0.0001);
		
		// Add item again, list size should not change, price should
		ejb.addToCart(t);
		assertEquals("items list size", 1, ejb.getOrderItems().size());
		assertEquals("total price", 2 * 123.45, ejb.getTotalPrice(), 0.0001);
		
		assertTrue(ejb.removeFromCart(t));
		assertEquals("items list size", 0, ejb.getOrderItems().size());
		assertEquals("total price", 0, ejb.getTotalPrice(), 0.0001);
		
	}

	/**
	 * @return
	 */
	public Ticket mockTicket() {
		Act act = new Act(ActType.MOVIE);
		act.setTitle("A one act wonder");
		Venue where = new Venue();
		Event e = new Event(act, LocalDateTime.now(), where);
		Ticket t = new Ticket();
		t.setEvent(e);
		t.setPrice(123.45);
		t.setSeatNumber("Box 1");
		return t;
	}

}
