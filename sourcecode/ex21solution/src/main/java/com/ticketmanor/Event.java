package com.ticketmanor;

import java.util.Date;

public class Event {
	long id;
	String what = "Concert"; 	// Musical, Concert, etc.
	String who;
	Date when;
	/** The Venue at which the event takes place. */
	String where;
	
	@Override
	public String toString() {
		return String.format("%s %s at %s, on %s", what, who, where, when);
	}
}
