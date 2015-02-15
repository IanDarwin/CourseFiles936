package com.ticketmanor.jpa;

import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Venue;

/** 
 * The EJB JPA portion of "Load some fake data". 
 * @author Ian Darwin
 */
@Stateless
public class FakeDataLoaderEjb {

	@PersistenceContext EntityManager em;
	
	static final Act[] sportsTeams = {
		new Act(ActType.SPORTS, "Nicks vs Raptors"),
		new Act(ActType.SPORTS, "Blue Jays vs Mets"),
		new Act(ActType.SPORTS, "Red Sox vs Maple Leafs"),
		new Act(ActType.SPORTS, "Blackhawks vs Canucks"),
		new Act(ActType.SPORTS, "Real Madrid vs Barcelona")
	};
	static final Venue[] sportsVenues = {
		new Venue("Rogers Stadium"),
		new Venue("Candlestick Park"),
		new Venue("Maple Leaf Gardens"),
		new Venue("United Centre"),
		new Venue("London Palladium")
	};
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void loadFakeData() {
		
		// Persist acts and venues
		System.out.println("FakeDataLoaderEjb.loadFakeData()");
		Act act = new Act();
		act.setTitle("Sun Never Sets: The Musical");
		act.setType(ActType.MUSICAL);
		em.persist(act);
		Venue venue = em.find(Venue.class, 1L);
		for (Act a : sportsTeams) {
			em.persist(a);
		}
		for (Venue v : sportsVenues) {
			em.persist(v);
		}
		
		// Create and persist events
		int n = 0;
		LocalDateTime start = LocalDateTime.now().withHour(19).withMinute(0);
		for (int i = 0; i < 40; i+=2) {
			Event ev = new Event(act, start.plusDays(i), venue);
			em.persist(ev);
			n++; n %= 5;
			Event sp = new Event(sportsTeams[n], start.plusDays(i), sportsVenues[n]);
			em.persist(sp);
		}
		
	}
}
