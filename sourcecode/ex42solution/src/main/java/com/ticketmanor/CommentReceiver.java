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
	//T Make this class implement the correct interface
	//-
	implements MessageListener
	//+
	{
	
	//T Note that this will be provided by the container
	@PersistenceContext EntityManager em;
	
	@Override
	//T Add an annotation that will ensure that this method's
	// results get committed to the database at the end of the method.
	//-
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	//+
	public void onMessage(Message msg) {
		if (!(msg instanceof ObjectMessage)) {
			System.err.println(
				"Wanted ObjectMessage but got sent a " + msg.getClass().getName());
			return;
		}
		//T Recall that the message sender sends us a wrapped object
		// Convert the incoming object to the correct type, extract
		// the "FeedbackForm" entity from it, display the key fields using
		// System.out.println(), and persist it to the database,
		// using the provided EntityManager.
		//-
		ObjectMessage wrapper = (ObjectMessage) msg;
		try {
			FeedbackForm comment = (FeedbackForm) wrapper.getObject();
			System.out.println("Got sender: " + comment.getCustName() + "--" + comment.getCustEmail());
			System.out.println("Got comment: " + comment.getComment());
			em.persist(comment);
			System.err.println("Feedback saved to database!");
		} catch (JMSException jmserr) {
			System.err.println("Failed to get feedback object from wrapper: " + jmserr);
			jmserr.printStackTrace(System.err);
		}
		//+
	}
}
