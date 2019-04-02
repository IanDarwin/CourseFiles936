package buspartner.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.ticketmanor.Constants;
import com.ticketmanor.model.FeedbackForm;

public class CommentSender {
	static boolean debug = false;

	public static void main(String[] args) {
		ConnectionFactory connFactory = null;
		Destination destination = null;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		//T Note the ton of JMS API that we are using just to connect:
		try {
			Context ctx = new InitialContext();
			if (debug) {
				System.out.println("----------");
				NamingEnumeration<Binding> bindings = ctx.listBindings("");
				while (bindings.hasMore()) {
					Binding binding = bindings.next();
					System.out.println(binding.getName() + "-->" + binding.getObject());
				}
			}
			System.out.println("----------");
			System.out.println("Getting " + Constants.JMS_REMOTE_CONNECTION_FACTORY);
			connFactory = (ConnectionFactory) ctx.lookup(Constants.JMS_REMOTE_CONNECTION_FACTORY);
			System.out.println("Got it: " + connFactory);
			System.out.println("Getting " + Constants.JMS_QUEUE_NAME);
			destination = (Destination) ctx.lookup(Constants.JMS_QUEUE_NAME);
			//destination = (Destination) ctx.lookup("jboss:/" + Constants.JMS_QUEUE_NAME);
			System.out.println("Got it: " + destination);
			String username = (String) ctx.getEnvironment().get("java.naming.security.principal");
			String password = (String) ctx.getEnvironment().get("java.naming.security.credential");
			connection = connFactory.createConnection(
					username, password);
			session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			System.out.println("Boilerplate Setup Finished OK");
		} catch (NamingException | JMSException e) {
			System.err.println("JMS setup did NOT complete: " + e);
			if (debug) {
				e.printStackTrace();
			}
			System.exit(1);
		}

		// Instantiate a FeedbackForm object, populate its fields
		// with (possibly whimsical) test data
		FeedbackForm comment = new FeedbackForm();
		comment.setCustName("Whiney Whitefoot");
		comment.setCustEmail("ww@gmail.moc");
		comment.setComment("I really love your site!!");

		try {
			//T Create an ObjectMessage, and wrap the above FeedbackForm in it.
			//-
			ObjectMessage message = session.createObjectMessage();
			message.setObject(comment);
			//+

			//T Send the message using the producer
			//-
			// This should cause a Feedback item to be logged on the server side.
			producer.send(message);
			//+

			//T READ THIS COMMENT:
			// THIS SECOND MESSAGE SHOULD CAUSE AN ERROR IN THE LOG FILE ON THE SERVER.
			// THIS IS INTENTIONAL - WE ARE TESTING YOUR MessageListener's ERROR HANDLING!
			// It's just here to test that you detect non-ObjectMessages in the queue.
			TextMessage message2 = 
					session.createTextMessage("Here is a TextMessage just to razz you");
			producer.send(message2);

			producer.close();
			connection.close();
			session.close();

			System.out.println("Client Completed. Two Messages sent.");
			System.err.println("Warning: JMS is one-way communication, so 'no news'\n" + 
					"is no guarantee of success. Check the server log file!");
		} catch (JMSException e) {
			System.out.println("Client messaging failed: " + e);
			if (debug) {
				e.printStackTrace();
			}
			System.exit(1);
		}

		System.exit(0);
	}
}
