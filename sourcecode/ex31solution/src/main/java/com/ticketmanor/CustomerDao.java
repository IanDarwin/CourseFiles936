package com.ticketmanor;

import javax.persistence.EntityManager;

/**
 * This class contains methods for CRUD operations on Customer objects
 */
public class CustomerDao {

	/** Save a customer object into the database */
	public Long saveCustomer(EntityManager em, Customer cust) {
		long newId = -1;
		// Sorry, we didn't have time to finish this -- Acme Outsourcing
		//-
		if (cust == null) {
			throw new NullPointerException("Customer may not be null");
		}
		if (cust.getFirstName() == null || cust.getLastName() == null) {
			throw new NullPointerException("Customer's names may not be null");
		}

		em.persist(cust);
		
		em.flush();
		
		newId = cust.getId();
		//+

		// Hint - check that names are not null, throw an exception if so.

		// Hint - save the Customer using the normal JPA method for saving

		// Hint - call the EntityManager flush() method before calling getId().

		return newId;
	}
}
