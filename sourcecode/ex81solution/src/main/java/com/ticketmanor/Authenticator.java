package com.ticketmanor;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class Authenticator {

	public String logout() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		//-
		try {
			((HttpServletRequest)ctx.getExternalContext().getRequest()).logout();
			ctx.addMessage(null, new FacesMessage("You have been logged out."));
		} catch (ServletException e) {
			System.out.println("How odd: logout failed");
			ctx.addMessage(null, new FacesMessage("Logout failed."));
		}
		return "/";
		//+
		//R ctx.addMessage(null, new FacesMessage("Log out function not written yet - bonus part of exercise."));
		//R return ""; // stay on same page
	}
}
