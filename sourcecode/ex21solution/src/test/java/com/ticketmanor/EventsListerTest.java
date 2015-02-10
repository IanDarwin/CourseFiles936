package com.ticketmanor;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.model.Event;

public class EventsListerTest {

	private EventsLister lister;

	@Before
	public void inti() {
		lister = new EventsLister();
	}
	@Test
	public void testGetEventsForDate() {
		LocalDateTime d = LocalDateTime.of(2014, 6, 6, 19,00,00);
		final List<Event> events = lister.getEventsForDate(d);
		assertTrue(events.size() > 0);
		Event e = events.get(0);
		assertEquals(d, e.getDate());
	}

	@Test
	public void testGetEventsNextNDays() {
		final List<Event> events = lister.getEventsNextNDays(3);
		assertTrue(events.size() > 0);
		Event e = events.get(0);
		assertNotNull(e);
		// Extra points if you actually test that e.when > d, but
		// the Date API makes that hard; one reason Java 8 (and the
		// real TicketManor codebase) use the new LocalDateTime class.
	}

}
