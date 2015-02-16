package com.ticketmanor.rest;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;

@Path("/sports")
public class SportsResource {

	@PersistenceUnit(name="ticketManor") EntityManagerFactory emf; // Web tier is multi-threaded
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getSportsEvents() {
		return emf.createEntityManager().createQuery("FROM Event e WHERE e.what.type = ?1", Event.class)
				.setParameter(1, ActType.SPORTS).getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Transactional(value=TxType.REQUIRED)
	public Event getSportsEvent(@PathParam("id") long id) {
		System.out.println("ConcertResource.getConcert(" + id + ")");
		return emf.createEntityManager().find(Event.class, id);
	}
}
