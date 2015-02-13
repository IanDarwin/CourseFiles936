package com.ticketmanor.service;

public class CreditCardValidate {

	
	/*
	 * 
	 * The "IBM" or "Luhn" algorithm has been described as: "the permutation s = (0)(1,2,4,8,7,5)(3,6)(9);
	 * if n is even, s(a1) + a2 + s(a3)... else a2 + s(a2) + a3..." 
	 * (at http://www.academic.marist.edu/mwa/cccard.html) and
	 * "For a card with an even number of digits, double every odd numbered digit and subtract 9 if the product 
	 * is greater than 9. Add up all the even digits as well as the doubled-odd digits, and the result must be 
	 * a multiple of 10 or it's not a valid card.
	 * If the card has an odd number of digits, perform the same addition doubling the even numbered digits instead." (http://www.phrack.org/show.php?p=47&a=8, quoted at http://www.merriampark.com/anatomycc.htm).
	 */
	
	
	public boolean validate(String cardNumberString){
		if(cardNumberString == null ){return false;}
		int[] digits = createDigits(cardNumberString);
		System.out.println(sumDigits(digits));
		return false;
	}
	
	int[] createDigits(String cardNumberString){
		int[] digits = new int[cardNumberString.length()];
		for(int i=0; i<cardNumberString.length();i++){
			digits[i] = Integer.parseInt(""+cardNumberString.charAt(i));
		}
		return digits;
	}
	
	int calculatecheckDigit(int[] digits){
		int ret = 0;
		for(int i=digits.length-2;i>=0;i=i-2){
			int doubleDigit = digits[i]*2;
			if(doubleDigit>9){doubleDigit = doubleDigit%10+1;}
			digits[i] = doubleDigit;
		}
		digits[digits.length-1] = 0;
		return (sumDigits(digits)*9)%10;
	}
	
	int sumDigits(int[] digits){
		int ret = 0;
		for (int i : digits) {
			ret+=i;
		}
		return ret;
	}
}
