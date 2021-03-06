package com.ticketmanor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;
	private CustomerDao testSubject;

	//T Note that most of this setup is the same as the JPA Basic exercise

	@BeforeClass
	public static void setupResources(){
		emf = Persistence.createEntityManagerFactory("ex41solution_test");
	}

	@Before
	public void setUp() throws Exception {
		testSubject = new CustomerDao();
		em = emf.createEntityManager();
	}

	@After
	public void cleanup() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	//T Write a testSaveCustomer method: create a customer and an address,
	// tie them together, get an entitymanager and use the DAO to save the customer.
	// Remember to create a transaction around your change to the database.
	// Also remember to annotate your test method!

	//-
	@Test
	public void testSaveCustomer() {
		Customer cust = new Customer();
		cust.setFirstName("John");
		cust.setLastName("Doe");
		Address addy = new Address();
		addy.setStreet("123 Main St");
		addy.setCity("Darston");
		addy.setState("ZZ");
		cust.setAddress(addy);
		entityTransaction = em.getTransaction();
		entityTransaction.begin();
		testSubject.saveCustomer(em, cust);
		entityTransaction.commit();
		em.close();
		long customerId = cust.getId();
		assertTrue("Customer id was not set", customerId > 0);
		long addressId = cust.getAddress().getId();
		assertTrue("Address id was not set", addressId > 0);
		em = emf.createEntityManager();
		cust = em.find(Customer.class, customerId);
		assertNotNull("Find Saved Customer by ID " + customerId, cust);
		assertEquals("Customer first name stored", "John", cust.getFirstName());
		assertEquals("Customer last name stored", "Doe", cust.getLastName());
		assertEquals("Address City stored", "Darston", cust.getAddress().getCity());
	}
	//+

	//T Examine these methods briefly (no code changes needed)
	// Note that @Test(expected= allows us to test error handling
	// WITHOUT having to write try/catch and report on errors
	@Test(expected=NullPointerException.class)
	public void testSaveNullCustomer() {
		Customer cust = null;
		testSubject.saveCustomer(em, cust);
	}

	@Test(expected=NullPointerException.class)
	public void testSaveNoNameCustomer() {
		Customer cust = new Customer();
		testSubject.saveCustomer(em, cust);
	}

	//T Add any number of other test methods, similar to the above
	// but exercising different test ideas, as discussed in the manual.
	// If you get lost, see the web hints for some approaches.

	//-
	@Test
	public void testRunQuery(){
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
		@SuppressWarnings("unchecked")
		List<Customer> customerList = q.getResultList();
		assertEquals("Incorrect entry", customerList.size(), 0);
	}
	
	@Test
	public void testQueryFirstAndLastName(){
		Customer cust = new Customer();
		cust.setFirstName("John");
		cust.setLastName("Doe");
		Address address = new Address(
				"213 Main St", "Reston", "QT", "Nostate");
		cust.setAddress(address);
		entityTransaction = em.getTransaction();
		entityTransaction.begin();
		testSubject.saveCustomer(em, cust);
		entityTransaction.commit();
		em = emf.createEntityManager();
		List<Object[]> firstNameLastNameList = em.createQuery("select c.firstName, c.lastName from Customer c where c.address.city = 'Reston'", Object[].class).getResultList();
		Object[] firstNameLastName = firstNameLastNameList.get(0);
		assertEquals("Customer first name not stored", "John", firstNameLastName[0]);
		assertEquals("Customer last name not stored", "Doe", firstNameLastName[1]);
	}

	//+
}
