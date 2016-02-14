package com.ticketmanor;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named("authenticator")
public class Authenticator {

	public void logout() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			((HttpServletRequest)ctx.getExternalContext().getRequest()).logout();
		} catch (ServletException e) {
			System.out.println("How odd: logout failed");
			ctx.addMessage(null, new FacesMessage("Logout failed."));
		}
	}
}
