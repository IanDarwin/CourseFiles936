package com.ticketmanor.service;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class LoggingInterceptor {

	public LoggingInterceptor() {
		System.out.println("LoggingInterceptor.init()");
	}

	// @AroundInvoke applies to business method; see also @AroundTimeout for timeout methods, etc.
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Throwable {
		log("About to call " + ctx.getMethod().getName());
		Object o = ctx.proceed();	// The actual call!
		log("Returned from method " + ctx.getMethod().getName());
		return o;
	}
	
	// Following is how it would normally be used; commented out b/c it can't
	// (or at least shouldn't!) be used on itself.

	// @Interceptors({LoggingInterceptor.class,LoggingInterceptor.class})
	public void validateCredit() {
		System.out.println("LoggingInterceptor.validateCredit(): I was here");
	}

	void log(String mesg) {
		System.out.println(mesg);
	}
}
