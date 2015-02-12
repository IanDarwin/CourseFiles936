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
@MessageDriven(mappedName=Constants.QUEUE_NAME, 
activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType",
		propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destination",
		propertyValue = Constants.QUEUE_NAME), 
	@ActivationConfigProperty(propertyName = "acknowledgeMode",
		propertyValue = "Auto-acknowledge")
})
public class CommentReceiver implements MessageListener {

	@PersistenceContext EntityManager em;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void onMessage(Message msg) {
		if (!(msg instanceof ObjectMessage)) {
			System.err.println(
				"Wanted ObjectMessage but got sent a " + msg.getClass().getName());
			return;
		}
		ObjectMessage wrapper = (ObjectMessage) msg;
		try {
			FeedbackForm comment = (FeedbackForm) wrapper.getObject();
			System.out.println("Got sender: " + comment.getCustName() + "--" + comment.getCustEmail());
			System.out.println("Got comment: " + comment.getComment());
			em.persist(comment);
		} catch (JMSException jmserr) {
			System.err.println("Failed to get object from wrapper: " + jmserr);
		}
	}
}
