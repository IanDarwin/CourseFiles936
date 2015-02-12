package com.ticketmanor;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;
	private CustomerDao testSubject;

	@Before
	public void setUp() throws Exception {
		testSubject = new CustomerDao();
	}

	@Test
	public void testSaveCustomer() {
		em = emf.createEntityManager();
		Customer cust = new Customer();
		cust.setFirstName("John");
		cust.setLastName("Doe");
		entityTransaction = em.getTransaction();
		entityTransaction.begin();
		testSubject.saveCustomer(em, cust);
		entityTransaction.commit();
		long id = cust.getId();
		assertTrue("Customer id was not set", id>0);
		em = emf.createEntityManager();
		cust = em.find(Customer.class, id);
		assertEquals("Customer first name not stored", "John", cust.getFirstName());
		assertEquals("Customer last name not stored", "Doe", cust.getLastName());
	}

	@BeforeClass
	public static void setupResources(){
		emf = Persistence.createEntityManagerFactory("ex31");
	}
}
