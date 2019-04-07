package com.ticketmanor.microservice;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ticketmanor.model.FeedbackForm;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * CDI Bean
 */
@Named @RequestScoped
public class MicroService {

	String firstName, lastName, email;
	String comments;
	String greeting;
	
	public String submit() {
		System.out.printf("MicroService.submit(%s)\n", firstName + ' ' + lastName);

		// Here would be some code to send the information to TicketManor
		// First we construct a FeedbackForm (from the datamodel project)
		FeedbackForm form = 
			new FeedbackForm(0, getName(), email, LocalDateTime.now(), comments);

		//T BONUS ONLY Copy all the JMS code from the JMS Client exercise
		// This is basically all the code in both try/catch statements
		// But not the form construction (which we've done for you using the
		// full version of the constructor), and you should also DELETE the 
		// bogus "second" send of an invalid object.
		// Near the end of the copied code, use JMS to send the form to TicketManor via JMS.

		// Here is some display logic
		String dayPart = dayPart(LocalTime.now().getHour());
		greeting = String.format("<b>Good %s, %s!!</b>", dayPart, firstName);
		return "/thanks.web";
	}

	public static String dayPart(int hour) {
		if (hour >= 18) {
			return "Evening";
		}
		if (hour >= 12) {
			return "Afternoon";
		}
		return "Morning";
	}

	public String getFirstName() {
		return firstName;
	}

	/** Set the first name. MUST sanitize to prevent script kiddie HTML attacks */
	public void setFirstName(String firstName) {
		firstName = firstName.replaceAll("[^a-zA-Z ']", "");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** We want first and last name separate; TicketManor wants a single name.
	 * Merge two into one, and we're done!
	 * @return The full name
	 */
	public String getName() {
		return firstName + ' ' + lastName;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
