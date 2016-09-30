package com.ticketmanor.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ticketmanor.model.Customer;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;

public class TicketRecommender {
	private static Dao dao = new Dao();
	
	/** Get a list of Tickets in the customer's preferred order.
	 * This is the method you are asked to 'split' in the Exercise.
	 */
	public static List<Ticket> getPreferredSeats(Event e, Customer c) {
		final List<Ticket> availableSeats = dao.getAvailableSeats(e);
		// Initial implementation for price-sensitive customer: lowest price first
		// Uses modern Java "lambda expression", short for an inner class that implements Comparable.
		Collections.sort(availableSeats, (t1,t2)->t1.getPrice() < t2.getPrice() ? -1 : +1);
		return availableSeats; 	// Has been sorted in place
	}
	
	// This is a totally fake DAO for this exercise.
	// Do not change the DAO class at all.
	public static class Dao {

		public List<Ticket> getAvailableSeats(Event e) {
			List<Ticket> ret = new ArrayList<>();
			ret.add(new Ticket(e, "Orchestra 1", 37.50));
			ret.add(new Ticket(e, "Balcony 7", 24.00));
			ret.add(new Ticket(e, "Ceiling 5", 15.00));
			return ret;
		}
		
	}
}
