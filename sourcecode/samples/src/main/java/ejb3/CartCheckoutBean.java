package ejb3;

import javax.ejb.*;
import java.util.List;

@Stateful
public class CartCheckoutBean {
	@EJB public CreditCardValidationBean validator;

	private List<Item> cart;

	public boolean checkout(Customer customer) {
		if (validator.validate(customer)) {
			// process items in "cart" to complete order
			return true;
		}
		return false;
	}

	/** A @Remove method is needed to "disconnect" from a SFSB */
	@Remove
	public void cleanup() {
		// empty
	}

	static class Item {
		// Just to make the above compile
	}

	static class CreditCardValidationBean {
		boolean validate(Object o) {
			return false;	// just to make it compile
		}
	}
}

