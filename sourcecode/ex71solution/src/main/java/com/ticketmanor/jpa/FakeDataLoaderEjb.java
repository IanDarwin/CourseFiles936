package com.ticketmanor.jpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Venue;

/** 
 * The EJB JPA portion of "Load some fake data".
 * Yes, we know some of the combinations are ridiculous.
 * In fact, most of them probably are.
 * @author Ian Darwin
 */
@Stateless
@Startup
public class FakeDataLoaderEjb {

	@PersistenceContext EntityManager em;
	
	@PostConstruct
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void loadFakeData() {
		System.out.println("FakeDataLoaderEjb.loadFakeData()");
		
		Act firstAct = em.find(Act.class, 1L); // must exist
		List<Act> sportsTeams = em.createQuery("from Act act where act.type = ?1", Act.class).
				setParameter(1, ActType.SPORTS).
				getResultList();
		
		List<Venue> allVenues = em.createQuery("from Venue v", Venue.class).getResultList();
		// Create and persist events; this part triggers on
		// whether there are any events still in the future
		// (should only invoke once during the week of a course!).
		LocalDateTime start = LocalDateTime.now().withHour(19).withMinute(0);
		TypedQuery<Event> findCurrent = em.createQuery("from Event e where e.date > ?1", Event.class);
		findCurrent.setParameter(1, start);
		if (findCurrent.getResultList().size() == 0) {
			int nTeams = 0, nVenues=0;
			for (int i = 0; i < 30; i+=2) {
				Event ev = new Event(firstAct, start.plusDays(i), em.find(Venue.class, 1L));
				em.persist(ev);
				nTeams++; nTeams %= sportsTeams.size();
				nVenues++; nVenues %= allVenues.size();
				Event sp = new Event(sportsTeams.get(nTeams), start.plusDays(i), allVenues.get(nVenues));
				em.persist(sp);
			}
		}
	}
}
