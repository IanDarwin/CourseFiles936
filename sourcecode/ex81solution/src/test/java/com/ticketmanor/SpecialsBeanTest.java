package com.ticketmanor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialsBeanTest {

	// This is pretty perfunctory, just to show that the EJB works locally
	@Test
	public void testOnce() {
		String actual = new SpecialsBean().getNextCouponCode();
		assertNotNull(actual);
		assertTrue(actual.startsWith("Special"));
	}
}
