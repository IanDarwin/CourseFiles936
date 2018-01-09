package ejb3;

import javax.ejb.*;
import java.util.concurrent.Future;

/**
 * Client code for the OrderBean EJB.
 * Presume this class is a web-tier component
 */
public class OrderBeanClient {
	@EJB OrderBean orders;

	Future<Boolean> orderSent; // same as return type of EJB method

	public void submitOrder() {
		orderSent = orders.processOrderInWarehouse();
	}

	public String getOrderProcessingStatus() {
		try {
			if (orderSent.isDone()) {
				return orderSent.get() ? "Processed" : "Failed";
			} else {
				return "Still processing, check back later!";
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to get status (" + e + ")", e);
		}
	}

	// Might also want a 'cancel' method
}
