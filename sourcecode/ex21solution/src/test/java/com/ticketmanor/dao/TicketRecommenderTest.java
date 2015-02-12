package com.ticketmanor.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;

public class TicketRecommenderTest {

	private Event e;
	
	@Before
	public void setUp() throws Exception {
	e = new Event();
	}

	@Test
	public void testSortSeats() {
		List<Ticket> ret = new ArrayList<>();
		ret.add(new Ticket(e, "Orchestra 1", 37.50));
		ret.add(new Ticket(e, "Balcony 7", 24.00));
		ret.add(new Ticket(e, "Ceiling 5", 15.00));
	}

}
