package com.ticketmanor.service;

import javax.ejb.Remote;

//T Annotate this interface for RMI access to the implementing EJB
//-
@Remote
//+
public interface CreditValidateInterface {

	/*
	 * Validate the credit card number, given as a String
	 * @param input The string form of the credit card number.
	 * @return True iff the value is a valid credit card number.
	 */
	public abstract boolean isValidCard(String input);

}
