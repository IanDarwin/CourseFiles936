package ejb3;

import javax.ejb.*;
import javax.jms.*;

@MessageDriven(mappedName="jms/MyQueue", activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/MyQueue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MyMDB implements MessageListener {
	@Override
	public void onMessage(Message m) {
		if (m instanceof TextMessage) {
			try {
				String text = ((TextMessage)m).getText();
			} catch (JMSException e) {
				// Handle the error
			}
			// do something with "text" - log it, store in database, etc.
		} else {
			throw new IllegalArgumentException(
				"Expected TextMessage, got " + m.getClass().getName());
		}
	}
}
