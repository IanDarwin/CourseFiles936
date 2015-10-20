package com.ticketmanor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.model.Event;

/** 
 * A test case for the EventsLister. Students generate this test
 * from scratch, using the New JUnit Test Wizard in the IDE.
 * @author Ian Darwin
 */
public class EventsListerTest {

	private EventsLister lister;

	@Before
	public void init() {
		lister = new EventsLister();
	}
	
	@Test
	public void testGetEventsForDate() {
		final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		final List<Event> events = lister.getEventsForDate(now);
		assertTrue(events.size() > 0);
		Event e = events.get(0);
		assertEquals(now, e.getDate());
	}

	@Test
	public void testGetEventsNextNDays() {
		LocalDateTime now = LocalDateTime.now();
		final List<Event> events = lister.getEventsNextNDays(3);
		assertTrue(events.size() > 0);
		Event e = events.get(0);
		assertNotNull(e);
		// Check that e.date > now
		assertTrue(e.getDate().isAfter(now));
	}

}
