package com.ticketmanor.servicesmarterlazier;

import javax.naming.InitialContext;

import com.ticketmanor.service.CreditCardValidateTest;
import com.ticketmanor.service.CreditValidateInterface;
import org.junit.Before;

public class RemoteEJBClientIT extends CreditCardValidateTest {

	public RemoteEJBClientIT(String input, boolean expected) {
		super(input, expected);
	}

	CreditValidateInterface testSubject;

	private static final String LOOKUP_STRING = 
		"ex51-1.0.0-SNAPSHOT/CreditCardValidate!com.ticketmanor.service.CreditValidateInterface";

	@Before @Override
	public void setUp() throws Exception {
		testSubject = (CreditValidateInterface)
				new InitialContext().lookup(LOOKUP_STRING);
	}

	// The "extends" lets JUnit use the inherited testValues() method. Done!
}

