package com.ticketmanor.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ticketmanor.model.Customer;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;

public class TicketRecommenderTest {

	@Test
	public void testSortAvailableSeats() {
		Event e = new Event();
		List<Ticket> availableSeats = new ArrayList<>();
		availableSeats.add(new Ticket(e, "Orchestra 1", 37.50));
		availableSeats.add(new Ticket(e, "Balcony 7", 24.00));
		availableSeats.add(new Ticket(e, "Ceiling 5", 15.00));
		
		TicketRecommender.sortAvailableSeats(availableSeats);
		Ticket currentTicket = availableSeats.get(0);
		for(int i=1; i<availableSeats.size();i++){
			Ticket nextTicket = availableSeats.get(i);
			assertTrue(currentTicket.getPrice()<=nextTicket.getPrice());
		}
	}

	
	//For bonus
	class DaoImpl implements Dao{

		@Override
		public List<Ticket> getAvailableSeats(Event e) {
			List<Ticket> ret = new ArrayList<>();
			ret.add(new Ticket(e, "Orchestra 1", 37.50));
			ret.add(new Ticket(e, "Balcony 7", 24.00));
			ret.add(new Ticket(e, "Ceiling 5", 15.00));
			return ret;
		}
		
	}
	
	public void testgetPreferredSeats(){
		TicketRecommender.setDao(new DaoImpl());
		List<Ticket> availableSeats = TicketRecommender.getPreferredSeats(new Event(), new Customer());
		Ticket currentTicket = availableSeats.get(0);
		for(int i=1; i<availableSeats.size();i++){
			Ticket nextTicket = availableSeats.get(i);
			assertTrue(currentTicket.getPrice()<=nextTicket.getPrice());
		}
	}
}
