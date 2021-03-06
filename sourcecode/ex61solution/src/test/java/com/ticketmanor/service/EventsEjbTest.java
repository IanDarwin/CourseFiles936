package com.ticketmanor.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Ignore;
import org.junit.Test;

import com.ticketmanor.model.Event;
import com.ticketmanor.model.Movie;

/**
 * An EJB_Container test for the EventsEjb.
 * Runs as a Unit test since it doesn't require deployment or
 * a running server.
 */
public class EventsEjbTest {

	@Test 
	@Ignore("no provider found!")				// XXX
	public void test() throws Exception {

		final Properties p = new Properties();
		p.put("TicketManorDataSource", "new://" + "Resource?type=DataSource");
		p.put("TicketManorDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("TicketManorDataSource.JdbcUrl", "jdbc:hsqldb:mem:TicketManorDataSource");

		final Context context = EJBContainer.createEJBContainer(p).getContext();

		EventsEjb events = (EventsEjb) context.lookup("java:global/injection-of-entitymanager/EventsBean");

		LocalDateTime today = LocalDateTime.now();
		// Movies today are going to the dogs?
		events.addEvent(new Event(new Movie("Sam Peckinpah", "Straw Dogs", 1971), today, null));
		events.addEvent(new Event(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992), today, null));
		events.addEvent(new Event(new Movie("Walt Becker", "Old Dogs", 2009), today, null));

		List<Event> list = events.getAllEvents();
		assertEquals("List.size()", 3, list.size());

		for (Event event : list) {
			events.deleteEvent(event);
		}
	}
}
