package com.ticketmanor.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import com.ticketmanor.model.Event;

public class EventsEjbTest {

	private String URL1 =
		"http://localhost:8080/ex61solution/rest/events1";
	
	private String URL2 =
		"http://localhost:8080/ex61solution/rest/events2";
	
	//-
	@Test @Ignore // Doesn't work ATM, problem mapping new LocalDateTime class
	public void testGetSingleEvent1() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL1 + "/" + 12);
		Event e = target.request(MediaType.APPLICATION_JSON).get(Event.class);
		System.out.println("Got one event: " + e);
	}
	//+
	
	@Test
	public void testGetEvents1() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL1);
		List<Event> e = target.request(MediaType.APPLICATION_JSON).get(List.class);
		//T Prove that the list contains at least one event
		//-
		System.out.printf("Got a list of %d events%n", e.size());
		assertTrue(e.size() > 0);
		//+
	}
	
	@Test
	public void testGetEvents2() {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(URL2);
		List<Event> e = target.request(MediaType.APPLICATION_JSON).get(List.class);
		//T Prove that the list contains at least one event
		//-
		System.out.printf("Got a list of %d events%n", e.size());
		assertTrue(e.size() > 0);
		//+
	}
}
