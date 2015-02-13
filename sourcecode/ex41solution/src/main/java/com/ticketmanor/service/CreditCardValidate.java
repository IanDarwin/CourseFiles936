//Ian -- replace this with the logic in the javsrc project

package com.ticketmanor.service;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless @Remote(CreditValidateInterface.class)
public class CreditCardValidate implements CreditValidateInterface {
	
	/* (non-Javadoc)
	 * @see com.ticketmanor.service.CreditValidateInterface#validate(java.lang.String)
	 */
	@Override
	public boolean validate(String cardNumberString){
		if(cardNumberString == null ){return false;}
		return(
				cardNumberString.charAt(cardNumberString.length()-1)+"")
				.equals(calculatecheckDigit(createDigits(cardNumberString))+"");
	}
	
	int[] createDigits(String cardNumberString){
		int[] digits = new int[cardNumberString.length()];
		for(int i=0; i<cardNumberString.length();i++){
			digits[i] = Integer.parseInt(""+cardNumberString.charAt(i));
		}
		return digits;
	}
	
	int calculatecheckDigit(int[] digits){
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
