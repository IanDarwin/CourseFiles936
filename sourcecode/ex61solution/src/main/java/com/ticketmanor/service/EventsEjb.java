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
import javax.ws.rs.Path;

import com.ticketmanor.model.Event;
import com.ticketmanor.model.Location;

/** Slightly mis-named; should be ConcertsEjb */
@Stateless
@Local @Remote
@Path("eventsEjb")
// Following annotation NOT needed in this exercise as it is configured via XML.
// @Interceptors(LoggingInterceptor.class)
public class EventsEjb {

	@PersistenceContext EntityManager em;
	
	public List<Event> getAllEvents() {
		final TypedQuery<Event> query = 
				em.createQuery("from Event e", Event.class);
		return query.getResultList();
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

	// @GET
	public List<Event> getEventsNextNDays(int nDays, Location locn) {
		// XXX Doesn't handle location yet!
		return getEventsNextNDays(nDays);
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
