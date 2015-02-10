package com.ticketmanor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDaoTest {

	static EntityManagerFactory factory;
	CustomerDao dao;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("ex31");
	}

	@Before
	public void setupDao() {
		dao = new CustomerDao();
	}

	@Test
	public void testSave() {
		System.out.println("CustomerDaoTest.testSave()");
		Customer c = new Customer();
		c.setFirstName("Gunter");
		c.setLastName("Zarcoscys");
		c.setCity("Toronto");
		c.setCountry("Canada");

		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		dao.saveCustomer(em, c);

		tx.commit();
		long id = c.getId();
		em.close();

		em = factory.createEntityManager();
		Customer c2 = em.find(Customer.class, id);
		assertNotNull(c2);
		assertEquals("Toronto", c2.getCity());
		// What would we have to do to be able to write
		// assertEquals(c, c2);
		em.close();
		System.out.println("We got our Customer object back!");
	}
}
