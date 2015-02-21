package com.ticketmanor.client;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.service.CreditValidateInterface;

public class RemoteEJBClientTest {

	private static final String LOOKUP_STRING = 
		"ex41solution-1.0.0-SNAPSHOT/CreditCardValidate!com.ticketmanor.service.CreditValidateInterface";

	private InitialContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx = new InitialContext();
	}

	//T Write a test that looks up the CreditValidateInterface in JNDI,
	// and invoke it with two sample card numbers from the local client
	// test, one valid one and one invalid one. Use assertTrue() and assertFalse().
	//-
	@Test
	public void test() {
		try {
			CreditValidateInterface creditCardValidate = (CreditValidateInterface)ctx.lookup(LOOKUP_STRING);
			assertTrue("This should have been true", creditCardValidate.validate("4111111111111111"));
			assertFalse("This should give false", creditCardValidate.validate("799273987101"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//+
}