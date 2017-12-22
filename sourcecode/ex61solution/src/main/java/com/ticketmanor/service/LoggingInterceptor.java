package com.ticketmanor.service;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Interceptor
public class LoggingInterceptor {

	// @AroundInvoke applies to business method; see also @AroundTimeout for timeout methods, etc.
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Throwable {
		log("About to call " + ctx.getMethod().getName());
		Object o = ctx.proceed();	// The actual call!
		log("Returned from method " + ctx.getMethod().getName());
		return o;
	}
	
	// @Interceptors({LoggingInterceptor.class,LoggingInterceptor.class})
	public void validateCredit() {
		System.out.println("LoggingInterceptor.validateCredit(): I was here");
	}

	void log(String mesg) {
		System.out.println(mesg);
	}
}
