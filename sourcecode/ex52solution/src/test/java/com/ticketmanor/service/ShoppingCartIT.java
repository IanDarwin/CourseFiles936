package com.ticketmanor.service;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Let's try to re-use all the tests in the ShoppingCartTest
 * by subclassing it.
 */
public class ShoppingCartIT extends ShoppingCartTest {

	private static final String LOOKUP_STRING = 
		"ex52solution-1.0.0-SNAPSHOT/ShoppingCartEjb!com.ticketmanor.service.ShoppingCart";

	private static InitialContext ctx;
	
	/** This is the now-familiar (we hope) factory-pattern-in-BeforeClass Test method */
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = new InitialContext();
	}
	
	//T Create an @Before method to look up the shopping cart
	// and assign it to the existing "cart", using the LOOKUP_STRING
	// defined above. 
	//-
	@Before @Override
	public void init() throws Exception {
		cart = (ShoppingCart)ctx.lookup(LOOKUP_STRING);
	}
	//+

	//T BONUS: You should @remove a SFSB when done with it.
	// Add a method to be run at the end of each test to invoke
	// the @Remove-annotated method on the cart EJB.

	//T If you followed our advice above, you don't have to write any @Test methods!
}
