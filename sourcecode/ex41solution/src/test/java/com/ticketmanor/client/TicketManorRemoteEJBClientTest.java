package com.ticketmanor.client;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.ticketmanor.service.CreditValidateInterface;

public class TicketManorRemoteEJBClientTest {

	private static final String LOOKUP_STRING = 
		"ex41solution-1.0.0-SNAPSHOT/CreditCardValidate!com.ticketmanor.service.CreditValidateInterface";

	private InitialContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx = new InitialContext();
	}

	@Test
	public void test() {
		try {
			
			CreditValidateInterface creditCardValidate = (CreditValidateInterface)ctx.lookup(LOOKUP_STRING);
			assertTrue("This shoule have been true", creditCardValidate.validate("4111111111111111"));
			assertFalse("This shoul give false", creditCardValidate.validate("799273987101"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
