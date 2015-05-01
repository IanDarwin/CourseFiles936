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

import com.ticketmanor.model.Venue;

@Path("/venues")
public class VenueResource {

	@PersistenceUnit EntityManagerFactory emf; // Web tier is multi-threaded
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venue> getConcerts() {
		return emf.createEntityManager().createQuery("FROM Venue").getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Transactional(value=TxType.REQUIRED)
	public Venue getItem(@PathParam("id") long id) {
		System.out.println("VenueResource.getItem(" + id + ")");
		return emf.createEntityManager().find(Venue.class, id);
	}
}
