package com.ticketmanor.ui.jsf;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class LocalDateTimeJsfConverterTest {
	
	LocalDateTimeJsfConverter target;

	final String stringDate = "June 1, 2015 7:30 PM";
	final LocalDateTime localDateTime = LocalDateTime.of(2015, 06, 01, 19, 30, 56, 0);
	
	@Before
	public void setUp() throws Exception {
		target = new LocalDateTimeJsfConverter();
	}

	@Test
	public void testGetAsObject() {
		LocalDateTime asObject = (LocalDateTime) target.getAsObject(null, null, stringDate);
		System.out.println("Converted to LocalDateTime: " + asObject);
		assertEquals(2015, asObject.getYear());
		assertEquals(19, asObject.getHour());
	}

	@Test
	public void testGetAsString() {
		final String asString = target.getAsString(null, null, localDateTime);
		System.out.println("Converted to String" + asString);
		assertEquals(stringDate, asString);
	}
}
