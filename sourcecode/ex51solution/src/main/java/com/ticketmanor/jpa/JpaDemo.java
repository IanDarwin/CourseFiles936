package com.ticketmanor.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ticketmanor.model.Venue;

public class JpaDemo {
	
	protected static EntityManagerFactory entityMgrFactory = null;
	protected static EntityManager entityManager = null;

	public static void main(String[] args) throws Exception {
		long time = System.currentTimeMillis();
		entityMgrFactory = Persistence.createEntityManagerFactory("ticketManor");
		long time2 = System.currentTimeMillis();
		System.out.printf("Created EntityManagerFactory in %f seconds%n", (time2 - time)/1000d);;

		EntityManager entityManager = entityMgrFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
			
		@SuppressWarnings("unchecked")
		final List<Venue> venues = entityManager.createQuery("Select v from Venue v").getResultList();
		for (Venue v : venues) {
			System.out.println(v);
		}
		transaction.commit();
		entityManager.close();
	}

}
