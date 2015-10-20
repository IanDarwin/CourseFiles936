package com.ticketmanor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Venue;

/** This is a heavily-cut-down version of the TicketManor EventEJB, 
 * for standalone use in the Unit Testing exercise.
 * It generates fake data, not live data, but we only need it to learn
 * how to write unit tests for an existing class.
 * It is mostly self-contained.
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
		Event evt = fakeEvent();
		LocalDateTime d = evt.getDate().plusDays(nDays);
		evt.setDate(d);
		results.add(evt);
		return results;
	}

	private Event fakeEvent() {
		
		Act act = new Act(ActType.MUSICAL, "The Tail End");
		Venue venue = new Venue("Circular Lister Gardens");
		Event e = new Event(act, LocalDateTime.now(), venue);
		return e;
	}
}
