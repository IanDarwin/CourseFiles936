package com.ticketmanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ticketmanor.model.Event;

@Path("/events2")
public class EventsResource {
	
	@EJB EventsEjb ejb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEvents() {
		return ejb.getAllEvents();
	}

}
