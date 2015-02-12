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

import org.junit.Test;

import com.ticketmanor.model.FeedbackForm;

public class CommentSender {

	@Test
	public void main() throws Exception {

		Context ctx = new InitialContext();
		ConnectionFactory connFactory = 
			(ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
		System.out.println("ConnectionFactory OK");
		Destination destination = 
			(Destination) ctx.lookup(Constants.QUEUE_NAME);
		System.out.println("Destination OK");
		Connection connection = connFactory.createConnection();
		System.out.println("Connection OK");
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		System.out.println("JMS Session OK");
		MessageProducer producer = session.createProducer(destination);
		System.out.println("MessageProducer OK");
		
		// This should cause a Feedback item to be logged on the server side.
		FeedbackForm comment = new FeedbackForm();
		comment.setCustName("Whiney Whitefoot");
		comment.setCustEmail("ww@gmail.moc");
		comment.setComment("I actually love your site!!");
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
