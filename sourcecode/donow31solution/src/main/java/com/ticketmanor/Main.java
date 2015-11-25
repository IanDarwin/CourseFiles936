package com.ticketmanor;

import javax.persistence.*;

import com.ticketmanor.model.Customer;

/**
 * Main program for initial interaction with JPA
 */
public class Main {

	/** Create a Customer object and save into the database */
	public static void main(String[] args) {

		//T Set up JPA as per JavaSE way of doing things
		//H The persistence unit name is found in src/main/resources/META-INF/persistence.xml
		EntityManagerFactory emf = null;
		EntityManager em = null;
		//-
		emf = Persistence.createEntityManagerFactory("donow31solution");
		em = emf.createEntityManager();
		//+

		// Create customer object
		Customer customer = new Customer(), customer2 = null;
		customer.setFirstName("John");
		customer.setLastName("Doe");

		// This is JavaSE so we have to manage transactions ourselves
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		//T Now save the object
		//-
		em.persist(customer);
		//+

		// And commit the transaction
		entityTransaction.commit();

		// All done. But let's read it back to verify
		long id = customer.getId();
		
		//T Let's set "em" to a newly-created EntityManager, use it to "find" the Customer object
		// identified by "id", and save it in the variable "customer2" declared above
		//-
		em = emf.createEntityManager();
		customer2 = em.find(Customer.class, id);
		//+

		// Now we'll check that they're the same
		if (customer.getFullName().equals(customer2.getFullName())) {
			System.out.println("They match");
		} else {
			System.out.println("Arrgghh! They don't match");
		}

		// Now let's count how many Customers objects there are
		String query = null;
		//T Assign to "query" a JPQL command that will "select" the count of c from Customer c
		//-
		query = "select count(c) from Customer c";
		//+
		Long n = em.createQuery(query, Long.class).getSingleResult();
		System.out.printf("There are %d Customers%n", n);
		
		// All done? Close the EntityManager
		em.close();
	}
}
