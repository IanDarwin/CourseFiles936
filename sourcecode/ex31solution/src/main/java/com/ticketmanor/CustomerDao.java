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
		em.persist(cust);
		
		em.flush();
		
		newId = cust.getId();
		//+
		return newId;
	}
}
