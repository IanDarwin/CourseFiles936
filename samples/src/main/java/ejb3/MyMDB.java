package ejb3;

import javax.ejb.*;
import javax.jms.MessageListener;

@MessageDriven(mappedName="jms/MyQueue", activationConfig =  {
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MyMDB implements MessageListener {
	public void onMessage(Message m) {
		if (m instanceof TextMessage) {
			String text = ((TextMessage)m).getText();
			// do something with "text" - log it, store in database, etc.
		} else {
			throw new IllegalArgumentException(
			"Expected TextMessage, got " + m.getClass().getName());
		}
	}
}
