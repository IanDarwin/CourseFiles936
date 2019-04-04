package com.ticketmanor.ui.jsf;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ticketmanor.model.Event;
import com.ticketmanor.service.EventsEjb;

@Named @RequestScoped
public class EventsBean {	 // Should be EventsJsfBean

	@EJB
	EventsEjb ejb;

	List<Event> results;

	static final int DEFAULT_NDAYS = 5;
	
	private int nDays = DEFAULT_NDAYS;

	public int getNumDays() {
		return nDays;
	}

	public void setNumDays(int nDays) {
		this.nDays = nDays;
	}
	
	/** A JSF Action handler should return the next page path as a String */
	public String getEventsNextNDays() {
		System.out.println("EventsJsfBean.getEventsNextNDays(); nDays = " + nDays);
		results = ejb.getEventsNextNDays(nDays);
		return "eventsList";
	}

	/** This method will be called to retrieve the results */
	public List<Event> getResultList() {
		return results == null ? Collections.emptyList() : results;
	}
}
