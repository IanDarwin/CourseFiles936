package com.ticketmanor.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ticketmanor.model.Event;

@Stateless
@Local @Remote
//T Annotate this with a REST annotation
//-
@Path("events1")
//+
public class EventsEjb {
	
	@PersistenceContext EntityManager em;
	
	//T Annotate this method for HTTP GET, with JSON as the output type
	//-
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//+
	public List<Event> getAllEvents() {
		final TypedQuery<Event> query = 
				em.createQuery("from Event e", Event.class);
		return query.getResultList();
	}
	
	//T Annotate this method for HTTP GET, with JSON as the output type
	// and a single path-parameter, e.g., events1/123
	//-
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//+
	public Event getEventById(@PathParam("id") long id) {
		return em.find(Event.class, id);
	}
	
	/** Get a list of Events on the given date */
	public List<Event> getEventsForDate(LocalDate selectedDate) {
		final TypedQuery<Event> q = 
				em.createQuery("from Event e where e.date like " + selectedDate,
				Event.class);
		return q.getResultList();
	}
	
	/** Get events that will occur in the next 'n' days */
	public List<Event> getEventsNextNDays(int nDays) {
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.from(start).plusDays(nDays);
		return em
				.createQuery("from Event e where e.date >= ?1 AND e.date <= ?2", Event.class)
				.setParameter(1, start)
				.setParameter(2, end)
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addEvent(Event event) {
		em.persist(event);
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteEvent(Event event) {
		em.remove(event);
	}
}
