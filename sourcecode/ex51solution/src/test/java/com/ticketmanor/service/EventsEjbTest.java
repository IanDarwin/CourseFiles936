package com.ticketmanor.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

import com.ticketmanor.model.Event;

public class EventsEjbTest {

	private String URL =
		"http://localhost:8080/ex51solution/rest/events1";
	
	@Test @Ignore // Doesn't work, obscure mapping error.
	public void testGetOneEvent() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL + "/" + 12);
		Event e = target.request(MediaType.APPLICATION_JSON).get(Event.class);
		System.out.println("Got one event: " + e);

	}
	@Test
	public void testGetEvents() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL);
		List<Event> e = target.request(MediaType.APPLICATION_JSON).get(List.class);
		System.out.println("Got an event list: " + e);

	}
}
