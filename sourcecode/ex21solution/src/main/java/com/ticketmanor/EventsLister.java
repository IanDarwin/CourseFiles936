package com.ticketmanor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;

/** This is a heavily-cut-down version of the TicketManor EventEJB.
 * It generates fake data, not live data, but we only need it to learn
 * how to write unit tests for an existing class.
 * It is mostly self-contained, and uses the obsolete Date API because
 * that will be familiar to most students.
 * @author Ian Darwin
 */
public class EventsLister {
	
	/** Get events for the given day */
	public List<Event> getEventsForDate(LocalDateTime selectedDate) {
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
		LocalDateTime d = e.getDate();
		e.setDate(d.plusDays(1));
		results.add(e);
		return results;
	}

	private Event fakeEvent() {
		
		Event e = new Event();
		e.setWhat(new Act(ActType.MUSICAL, "The Twinning Sisters"));
		e.setDate(LocalDateTime.now());

		return e;
	}
}
