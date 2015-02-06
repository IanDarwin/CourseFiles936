package com.ticketmanor;

import java.util.*;

/** This is a heavily-cut-down version of the TicketManor EventBean.
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
		e.when = selectedDate;
		events.add(e);
		return events;
	}
	
	/** Get events that will occur in the next 'n' days */
	public List<Event> getEventsNextNDays(int nDays) {
		List<Event> results = new ArrayList<>();
		Event e = fakeEvent();
		Date d = e.when;
		d.setDate(d.getDate() + 1);
		e.when = d;
		results.add(e);
		return results;
	}

	private Event fakeEvent() {
		
		Event e = new Event();
		e.what = "Musical";
		e.who = "The Twinning Sisters";
		e.when = new Date();

		return e;
	}
}
