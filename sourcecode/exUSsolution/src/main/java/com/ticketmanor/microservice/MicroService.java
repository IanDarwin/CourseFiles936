package com.ticketmanor.microservice;

import javax.inject.Named;
import java.time.LocalTime;

/**
 * CDI Bean
 */
@Named
public class MicroService {

	String firstName, lastName, email;
	String greeting;
	
	public String signup() {
		System.out.printf("MicroService.signup(%s)\n", firstName + ' ' + lastName);

		// Here would be some code to save the signup in a database

		// Here is some display logic
		int hour = LocalTime.now().getHour();
		String dayPart = "Morning";
		if (hour >18) {
			dayPart = "Evening";
		} else if (hour > 12) {
			dayPart = "Afternoon";
		}
		greeting = String.format("<b>Good %s, %s!!</b>", dayPart, firstName);
		return "/thanks.web";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGreeting() {
		return greeting;
	}
}
