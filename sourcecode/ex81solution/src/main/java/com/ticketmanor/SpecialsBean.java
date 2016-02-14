package com.ticketmanor;

import java.time.LocalDateTime;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * This is meant to represent an Administrative Function of
 * assigning new discount codes; we don't want end users creating
 * their own discounts(!) so this has a RolesAllowed.
 */
@Stateless
@RolesAllowed("administrator")
public class SpecialsBean {

	/**
	 * This method has RolesAllowed "administrator" so if it works, you have admin privs.
	 * It's nothing very fancy, but the fact that you can or cannot call it is 
	 * all that matters.
	 */
	public String getNextCouponCode() {

		LocalDateTime now = LocalDateTime.now();

		return "Special" + now.getMinute() + now.getDayOfMonth();

	}
}
