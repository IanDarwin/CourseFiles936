package com.ticketmanor.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.ticketmanor.model.Event;

public class EventsEjbTest {

	private String URL =
		"http://localhost:8080/ex51solution/rest/events1";
	
	@Test
	public void testGetEvents() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL);
		Event[] e = target.request(MediaType.APPLICATION_JSON).get(Event[].class);
		System.out.println("Got an event list: " + e);

	}
}
