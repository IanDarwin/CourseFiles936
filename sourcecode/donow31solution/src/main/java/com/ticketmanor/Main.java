package com.ticketmanor;

import javax.persistence.*;

/**
 * This class contains methods for CRUD operations on Customer objects
 */
public class Main {

	/** Create a Customer object and save into the database */
	public static void main(String[] args) {

		// Set up JPA as per JavaSE way of doing things
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("donow31solution");
		EntityManager em = emf.createEntityManager();

		// Create customer object
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");

		// This is JavaSE so we have to manage transactions ourselves
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		// Now save the object
		em.persist(customer);

		// And commit the transaction
		entityTransaction.commit();

		// All done. But let's read it back to verify
		long id = customer.getId();
		
		// Let's get a new EntityManager
		em = emf.createEntityManager();
		Customer customer2 = em.find(Customer.class, id);

		// Check that they're the same
		if (customer.getName().equals(customer2.getName())) {
			System.out.println("They match");
		} else {
			System.out.println("Arrgghh! They don't match");
		}
		
		//+
	}
}
