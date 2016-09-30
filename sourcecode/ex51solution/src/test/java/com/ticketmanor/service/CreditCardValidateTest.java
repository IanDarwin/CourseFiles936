package com.ticketmanor.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** A LOCAL unit test for the credit card validation logic; make sure this
 * passes before you try to deploy the validator as an EJB!
 * @author Course 936 Team
 */
@RunWith(Parameterized.class)
public class CreditCardValidateTest {
	private CreditValidateInterface testSubject;
	static final Object[][] samplesCheck  = {
			{ "79927398713", true },
			{ "4111111111111111", true },
			{ "1076211283172708", true },
			{ "4417123456789113", true },
			{ "4417123456789112", false },

			{ "79927398710", false },
			{ "79927398711", false },
			{ "79927398712", false },

			{ "79927398713", true },
			{ "79927398714", false },
			{ "79927398715", false },
			{ "79927398716", false },

			{ "79927398717", false },
			{ "79927398718", false },
			{ "79927398719", false },
	};

	@Parameters
	public static List<Object[]> getSamples() {
		return Arrays.asList(samplesCheck);
	}

	@Before
	public void setUp() throws Exception {
		testSubject = new CreditCardValidate();
	}
	
	String input;
	boolean expected;
	
	public CreditCardValidateTest(String input, boolean expected) {
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void testValues() {
		boolean actual = testSubject.isValidCard(input);
		assertEquals(input, expected, actual);
	}
}
