package com.ticketmanor.service;

import javax.ejb.Remote;

@Remote
public interface CreditValidateInterface {

	public abstract boolean validate(String cardNumberString);

}