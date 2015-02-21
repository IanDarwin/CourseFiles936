package com.ticketmanor.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** A LOCAL unit test for the credit card validation logic; make sure this
 * passes before you try to deploy the validator as an EJB!
 * @author Chris Mawata
 */
public class CreditCardValidateTest {

	private CreditCardValidate testSubject;
	private String[] samplesCheck3;
	private String[] samplesCheckGood;

	@Before
	public void setUp() throws Exception {
		testSubject = new CreditCardValidate();
		samplesCheck3 = new String[] { "79927398710", "79927398711", "79927398712",
				"79927398713", "79927398714", "79927398715", "79927398716",
				"79927398717", "79927398718", "79927398719"};
		samplesCheckGood = new String[] { "79927398713","4111111111111111","1076211283172708","4417123456789113"};
	}

	@Test
	public void testCreateDigits() {
		for (int position = 0; position < samplesCheck3.length; position++) {
			int[] digits = testSubject.createDigits(samplesCheck3[position]);
			for (int i = 0; i < digits.length; i++) {
				assertTrue(("" + digits[i]).equals(samplesCheck3[position].charAt(i)
						+ ""));
			}
		}
	}

	@Test
	public void testSumDigits() {
		int[] sums = new int[] { 62, 63, 64, 65, 66, 67, 68, 69, 70, 71 };
		for (int i = 0; i < samplesCheck3.length; i++) {
			assertEquals("Sums are incorrect", sums[i],
					testSubject.sumDigits(testSubject.createDigits(samplesCheck3[i])));
		}
	}

	@Test
	public void testCheckSum() {
		// int[] sums = new int[]{62,63,64,65,66,67,68,69,70,71};
		for (int i = 0; i < samplesCheck3.length; i++) {
			assertEquals("Checksum is incorrect", 3,
					testSubject.calculatecheckDigit(testSubject
							.createDigits(samplesCheck3[i])));
		}
	}
	
	@Test
	public void testCheckSumGood() {
		for (int i = 0; i < samplesCheckGood.length; i++) {
			String sample = samplesCheckGood[i];
			assertEquals("Checksum is incorrect", sample.charAt(sample.length()-1)+"",
					testSubject.calculatecheckDigit(testSubject
							.createDigits(sample))+"");
		}
	}

//	{"4111 1111 1111 1111", true}, 
//	{"1076 2112 8317 2708", true},
//	{"3011 8231 2176 8115", false}, 
//	{"4417 1234 5678 9112", false}, 
//	{"4417 1234 5678 9113", true},
}
