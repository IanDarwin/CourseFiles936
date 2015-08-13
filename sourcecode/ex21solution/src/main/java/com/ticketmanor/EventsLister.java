package com.ticketmanor;

import java.util.*;

/** This is a heavily-cut-down version of the TicketManor EventEJB, for standalone use.
 * It generates fake data, not live data, but we only need it to learn
 * how to write unit tests for an existing class.
 * It is mostly self-contained, and uses the obsolete Date API because
 * that will be familiar to most students.
 * @author Ian Darwin
 */
public class EventsLister {
	
	/** Get events for the given day */
	public List<Event> getEventsForDate(Date selectedDate) {
		final List<Event> events = new ArrayList<>();
		Event e = fakeEvent();
		e.setDate(selectedDate);
		events.add(e);
		return events;
	}
	
	/** Get events that will occur in the next 'n' days */
	public List<Event> getEventsNextNDays(int nDays) {
		List<Event> results = new ArrayList<>();
		Event e = fakeEvent();
		Date d = e.getDate();
		// Note: d.setDate takes a day-of-month, e.setDate a Date argument.
		d.setDate(d.getDate() + nDays);
		e.setDate(d);
		results.add(e);
		return results;
	}

	private Event fakeEvent() {
		
		Event e = new Event();
		e.setWhat("Musical");
		e.setWho("The Twinning Sisters");
		e.setDate(new Date());

		return e;
	}
}
