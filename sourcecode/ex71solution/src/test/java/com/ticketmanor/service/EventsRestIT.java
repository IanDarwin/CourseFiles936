package com.ticketmanor.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.ticketmanor.model.Event;

public class EventsRestIT {

	private String URL1 =
		"http://localhost:8080/ex71solution/rest/events1";
	
	private String URL2 =
		"http://localhost:8080/ex71solution/rest/events2";
	
	Client cl;
	
	@Before
	public void setup() {
		cl = ClientBuilder.newClient();
	}
	
	@Test
	public void testGetEvents1() {
		time("Events1", () -> {
			WebTarget target = cl.target(URL1);
			@SuppressWarnings("unchecked")
			List<Event> e = target.request(MediaType.APPLICATION_JSON).get(List.class);
			//T Assert that the list contains at least one event
			//-
			System.out.printf("Got a list of %d events%n", e.size());
			assertTrue(e.size() > 0);
			//+
		});
	}
	
	//T AFTER you build the "Indirect" version (EventsResource), annotate this to be a Test
	//-
	@Test
	//+
	public void testGetEvents2() {
		time("Events2", () -> {
			WebTarget target = cl.target(URL2);
			@SuppressWarnings("unchecked")
			List<Event> e = target.request(MediaType.APPLICATION_JSON).get(List.class);
			//T Prove that the list contains at least one event
			//-
			System.out.printf("Got a list of %d events%n", e.size());
			assertTrue(e.size() > 0);
			//+
		});
	}

	void time(String descr, Runnable r) {
		long time = System.currentTimeMillis();
		r.run();
		long now = System.currentTimeMillis();
		System.out.printf("%s took %d mSec%n", descr, now - time);
	}
}
