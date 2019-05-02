package com.ticketmanor.microservice;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ticketmanor.Constants;
import com.ticketmanor.model.FeedbackForm;

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

		// Some business logic
		sendToBusinessPartner(form);

		// Here is some display logic
		String dayPart = dayPart(LocalTime.now().getHour());
		greeting = String.format("<b>Good %s, %s!!</b>", dayPart, firstName);
		return "/thanks.web";
	}
	
	public void sendToBusinessPartner(FeedbackForm form) {
		//T BONUS ONLY Copy all the JMS code from the JMS Client exercise
		// This is basically all the code in both try/catch statements
		// But not the form construction (which we've done for you above, using the
		// full version of the constructor), and you should also DELETE the 
		// bogus "second" send of an invalid object.
		// Near the end of the copied code, use JMS to send the form to TicketManor via JMS.
		//
		//-
		// Bonus Only!
		// TODO Note the ton of JMS API that we are using just to connect:
		try {
			Context ctx = new InitialContext();
			
			System.out.println("----------");
			System.out.println("Getting " + Constants.JMS_REMOTE_CONNECTION_FACTORY);
			ConnectionFactory connFactory = (ConnectionFactory) ctx.lookup(Constants.JMS_REMOTE_CONNECTION_FACTORY);
			System.out.println("Got Factory: " + connFactory.getClass());
			System.out.println("Getting " + Constants.JMS_QUEUE_NAME);
			Destination destination = (Destination) ctx.lookup(Constants.JMS_QUEUE_NAME);
			System.out.println("Got Destination: " + destination);
			String username = (String) ctx.getEnvironment().get("java.naming.security.principal");
			String password = (String) ctx.getEnvironment().get("java.naming.security.credential");
			Connection connection = connFactory.createConnection(
					username, password);
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			producer.send(session.createObjectMessage(form));
			System.out.println("Feedback Form sent to partner TicketManor via JMS");
		} catch (NamingException | JMSException e) {
			throw new RuntimeException("JMS setup did NOT complete: " + e, e);
		}
		//+
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
