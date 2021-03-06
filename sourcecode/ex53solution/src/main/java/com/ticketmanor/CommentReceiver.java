package com.ticketmanor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ticketmanor.model.FeedbackForm;

/** 
 * This is a JMS Receiver (MessageListener); the @MessageDriven 
 * annotation makes it also be a Message-Driven Bean.
 * @author Ian Darwin
 */
//T Study these annotations!
@MessageDriven(mappedName=Constants.QUEUE_NAME,
  activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType",
		propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destination",
		propertyValue = Constants.QUEUE_NAME), 
	@ActivationConfigProperty(propertyName = "acknowledgeMode",
		propertyValue = "Auto-acknowledge")
})
public class CommentReceiver 
	//T Make this class implement the correct interface to be an MDB
	//-
	implements MessageListener
	//+
	{
	
	//T Note that this will be provided by the container
	@PersistenceContext EntityManager em;
	
	//T Add two annotations; one to ensure that you are overriding
	// a method correctly (as per the first todo step above) and
	// the second that will ensure that this method's results
	// get committed to the database at the end of the method,
	// i.e., that a transaction is required for this method.
	//-
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	//+
	public void onMessage(Message msg) {
		//T Note the following validity-checking test (no code change needed here)
		if (!(msg instanceof ObjectMessage)) {
			System.err.println(
				"Wanted ObjectMessage but got sent a " + msg.getClass().getSimpleName());
			return;
		}

		//T Recall that the message sender sends us a wrapped object
		// Convert (downcast) the incoming Message to the correct subtype and extract
		// the "FeedbackForm" entity from it (needs another downcast), then display the 
		// key fields using System.out.println(), and persist it to the database,
		// using the provided EntityManager.
		// Do all this inside a try-catch for JMS-specific exception(s).
		//-
		try {
			ObjectMessage wrapper = (ObjectMessage) msg;
			FeedbackForm comment = (FeedbackForm) wrapper.getObject();
			System.out.printf("Customer %s <%s> said %s%n", 
					comment.getCustName(), comment.getCustEmail(), comment.getComment());
			em.persist(comment);
			System.out.println("Feedback saved to database!");
		} catch (JMSException jmsexc) {
			System.err.println("Failed to get feedback object from wrapper: " + jmsexc);
			jmsexc.printStackTrace(System.err);
		}
		//+
	}
}
