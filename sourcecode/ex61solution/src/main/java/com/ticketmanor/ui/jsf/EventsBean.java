package com.ticketmanor.ui.jsf;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ticketmanor.model.Event;
import com.ticketmanor.service.EventsEjb;

@ManagedBean
public class EventsBean {

	@EJB
	EventsEjb ejb;

	List<Event> results;

	static final int DEFAULT_NDAYS = 5;
	
	private int nDays = DEFAULT_NDAYS;
	
	/** A JSF Action handler should return the next page path as a String */
	public String getEventsNextNDays() {
		results = ejb.getEventsNextNDays(nDays);
		return "eventsList.xhtml";
	}

	/** This method will be called to retrieve the results */
	public List<Event> getResultList() {
		return results == null ? Collections.emptyList() : results;
	}

	public int getNumDays() {
		return nDays;
	}

	public void setNumDays(int nDays) {
		this.nDays = nDays;
	}
}
