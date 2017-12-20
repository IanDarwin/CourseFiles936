package events;

import javax.enterprise.event.Observes;

/**
 * Updates the list of contributions by author.
 * Try renaming the class to ContributionsList
 * and "observe" that it still works without any
 * other configuration changes.
 * @author Ian Darwin
 */
public class EventReceiver {
	
	public void updateContributorsList(@Observes Recipe recipe) {
		System.out.println("Updating list of contributions by " + recipe.getAuthor());
		// ...
	}
}
