package com.ticketmanor;

import java.util.Date;

public class Event {
	private long id;
	private String what = "Concert"; 	// Musical, Concert, etc.
	private String who;
	private Date when;
	/** The Venue at which the event takes place. */
	private String where;
	
	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public Date getDate() {
		return when;
	}

	public void setDate(Date when) {
		this.when = when;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("%s %s at %s, on %s", what, who, where, when);
	}
}
