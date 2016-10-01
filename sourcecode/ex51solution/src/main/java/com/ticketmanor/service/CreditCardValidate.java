package com.ticketmanor.service;

//-
import javax.ejb.Stateless;
//+
/**
 * Credit Card Validation
 */
//-
@Stateless
//+
public class CreditCardValidate implements CreditValidateInterface {

	/** Returns a credit card string with spaces, dashes, etc., stripped out.
	 * Consider it a token of user-friendly in an age of really really stupid web sites.
	 * @param input The user-entered credit card string
	 * @return The cleaned up credit card string.
	 */
	String clean(final String input) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/*
	 * Validate the card; see algorithm.txt for details on the algorithm.
	 * @see com.ticketmanor.service.CreditValidateInterface#isValidCard(java.lang.String)
	 */
	@Override
	public boolean isValidCard(String input) {
		input = clean(input);
		final int len = input.length();
		int sum = 0;
		boolean doubleIt = false;

		for (int i = len - 1; i >= 0; i--) {
			int val = input.charAt(i) - '0';
			int addend = 0;
			if (doubleIt) {
				addend = val * 2;
				if (addend > 9) {
					addend -= 9;
				}
			} else {
				addend = val;
			}
			sum += addend;
			doubleIt = !doubleIt;
		}
		return sum % 10 == 0;
	}
}
