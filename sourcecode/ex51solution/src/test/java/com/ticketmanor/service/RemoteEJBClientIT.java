package com.ticketmanor.service;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.service.CreditValidateInterface;

public class RemoteEJBClientIT {

	private static final String LOOKUP_STRING = 
		"ex51solution-1.0.0-SNAPSHOT/CreditCardValidate!com.ticketmanor.service.CreditValidateInterface";

	private InitialContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx = new InitialContext();
	}

	//T Write a test that looks up the CreditValidateInterface in JNDI,
	// and invoke it with two sample card numbers from the local client
	// test, one valid one and one invalid one. Use assertTrue() and assertFalse().
	//H Use the LOOKUP_STRING defined above!!
	@Test
	public void test() throws NamingException {
		//-
		CreditValidateInterface creditCardValidate = (CreditValidateInterface)ctx.lookup(LOOKUP_STRING);
		assertTrue("This should have been true", creditCardValidate.isValidCard("4111111111111111"));
		assertFalse("This should give false", creditCardValidate.isValidCard("799273987101"));
		//+
	}
}
