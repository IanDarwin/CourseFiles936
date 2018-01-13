package jpa;

import java.util.*;
import javax.persistence.*;

public class QuerySamples {

	EntityManager em = null; // just to compile

	public void processQueries() {
		Query q = em.createQuery(
			"from Customer c where c.country = 'CA'");
		List<Customer> customers = q.getResultList();

		Query q2 = em.createQuery(
			"from Customer c where c.name = 'Jo Hlo'");
		Customer c2 = (Customer)q2.getSingleResult();

		TypedQuery<Customer> q3 = em.createQuery(
			"from Customer c", Customer.class);
		Customer c3 = q3.getSingleResult();
	}

	class Customer {
		// just to compile
	}
}

