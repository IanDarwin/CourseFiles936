package com.ticketmanor.service;

import javax.ejb.Remote;

//T Annotate this interface for RMI access to the implementing EJB
//-
@Remote
//+
public interface CreditValidateInterface {

	/** This "IBM" or "Luhn" algorithm has been described as:
	 * "the permutation s = (0)(1,2,4,8,7,5)(3,6)(9);
	 * if n is even, s(a1) + a2 + s(a3)... else a2 + s(a2) + a3..."
	 * (at http://www.academic.marist.edu/mwa/cccard.html) and
	 * "For a card with an even number of digits, double every odd numbered digit and subtract 9 if the product 
	 * is greater than 9. Add up all the even digits as well as the doubled-odd digits, and the result must 
	 * be a multiple of 10 or it's not a valid card. 
	 * If the card has an odd number of digits, perform the same addition doubling the 
	 * even numbered digits instead." (http://www.phrack.org/show.php?p=47&a=8, quoted at
	 * http://www.merriampark.com/anatomycc.htm).
	 * @param input The string form of the credit card number.
	 * @return True iff the value is a valid credit card number.
	 */
	public abstract boolean isValidCard(String input);

}
