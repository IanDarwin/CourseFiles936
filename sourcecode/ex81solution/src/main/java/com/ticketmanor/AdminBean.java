package com.ticketmanor;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named @RequestScoped
public class AdminBean {

	@EJB
	SpecialsBean engine;

	public String getNextCouponCode() {
		String s = engine.getNextCouponCode();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have created Coupon Code " + s));
		return "index";	// end up on same page
	}
}
