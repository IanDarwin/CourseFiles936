package com.ticketmanor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ticketmanor.model.FeedbackForm;

public class CommentSender {

	public static void main(String[] args) throws Exception {

		Context ctx = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");
		Destination destination = (Destination) ctx.lookup(Constants.QUEUE_NAME);
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(destination);
		
		// This should cause a Feedback item to be logged on the server side.
		FeedbackForm comment = new FeedbackForm();
		ObjectMessage message = session.createObjectMessage();
		message.setObject(comment);
		producer.send(message);
		
		// This should cause an error message on the server side.
		TextMessage message2 = 
			session.createTextMessage("Here is another message just to razz you");
		producer.send(message2);
		
		producer.close();
	}
}
