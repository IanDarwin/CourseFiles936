package com.ticketmanor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

	public void testRunQuery(){
		em = emf.createEntityManager();
		Customer cust = new Customer();
		cust.setFirstName("John");
		cust.setLastName("Doe");
		entityTransaction = em.getTransaction();
		entityTransaction.begin();
		testSubject.saveCustomer(em, cust);
		entityTransaction.commit();
		em = emf.createEntityManager();
		Query q = em.createQuery("from Customer c where c.firstName = 'John'");
		cust = (Customer)q.getSingleResult();
		assertEquals("Customer last name not stored", "Doe", cust.getLastName());
		q = em.createQuery("from Customer c where c.firstName = 'Oprah'");
		List<Customer> customerList = q.getResultList();
		assertEquals("Incorrect entry", customerList.size(), 0);
	}
	
	public void testQueryFirstAndLastName(){
		em = emf.createEntityManager();
		Customer cust = new Customer();
		cust.setFirstName("John");
		cust.setLastName("Doe");
		cust.setCity("Reston");
		entityTransaction = em.getTransaction();
		entityTransaction.begin();
		testSubject.saveCustomer(em, cust);
		entityTransaction.commit();
		em = emf.createEntityManager();
		Query q = em.createQuery("select c.firstName, c.lastName from Customer c where c.city = 'Reston'");
		List<String[]> firstNameLastNameList = (List<String[]>)q.getResultList();
		String[] firstNameLastName = firstNameLastNameList.get(0);
		assertEquals("Customer first name not stored", "John", firstNameLastName[0]);
		assertEquals("Customer last name not stored", "Doe", firstNameLastName[1]);
	}
	
	@BeforeClass
	public static void setupResources(){
		emf = Persistence.createEntityManagerFactory("ex31");
	}
}
