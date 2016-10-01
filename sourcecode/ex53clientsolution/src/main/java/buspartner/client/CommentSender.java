package buspartner.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ticketmanor.Constants;
import com.ticketmanor.model.FeedbackForm;

public class CommentSender {

	public static void main(String[] args) throws Exception {

		//T Note the ton of JMS API that we are using just to connect:
		Context ctx = new InitialContext();
		ConnectionFactory connFactory = 
			(ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
		Destination destination = 
			(Destination) ctx.lookup(Constants.QUEUE_NAME);
		String username = "testuser", password = "4jelBas";
		Connection connection = connFactory.createConnection(
				username, password);
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(destination);
		System.out.println("Boilerplate Setup Finished OK");
		
		// Instantiate a FeedbackForm object, populate its fields
		// with (possibly whimsical) test data
		FeedbackForm comment = new FeedbackForm();
		comment.setCustName("Whiney Whitefoot");
		comment.setCustEmail("ww@gmail.moc");
		comment.setComment("I actually love your site!!");
		
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
		
		// THIS WILL CAUSE AN ERROR IN THE LOG FILE ON THE SERVER.
		// THIS IS INTENTIONAL!
		// It's just here to test that you detect non-ObjectMessages in the queue.
		TextMessage message2 = 
			session.createTextMessage("Here is a TextMessage just to razz you");
		producer.send(message2);
		
		connection.close();
		session.close();
		
		System.err.println("Warning: JMS is one-way communication, so a green bar\n" + 
				"is no guarantee of success. Check the server log file!");
	}
}
