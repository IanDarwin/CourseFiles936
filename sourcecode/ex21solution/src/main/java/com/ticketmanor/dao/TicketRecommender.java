package com.ticketmanor.dao;

import java.util.Collections;
import java.util.List;

import com.ticketmanor.model.Customer;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;

public class TicketRecommender {
	
	private static Dao dao;
	
	/** Get a list of Tickets in the customer's preferred order.
	 * This is the method you are asked to 'split' in the Exercise.
	 */
	public static List<Ticket> getPreferredSeats(Event e, Customer c) {
		final List<Ticket> availableSeats = dao.getAvailableSeats(e);
		// Initial implementation for price-sensitive customer: lowest price first
		// Uses modern Java "lambda expression", short for an inner class that implements Comparable.
		// This Collections.sort() will be moved out of here by the refactoring!
		Collections.sort(availableSeats, (t1,t2)->t1.getPrice() < t2.getPrice() ? -1 : +1);
		//-
		// Sorting will be delegated to this new method:
		sortAvailableSeats(availableSeats);
		//+
		return availableSeats; 	// Has been sorted in place
	}

	//-
	static void sortAvailableSeats(final List<Ticket> availableSeats) {
		Collections.sort(availableSeats, (t1,t2)->t1.getPrice() < t2.getPrice() ? -1 : +1);
	}
	//+
	
	public static void setDao(Dao dao) {
		TicketRecommender.dao = dao;
	}
}
