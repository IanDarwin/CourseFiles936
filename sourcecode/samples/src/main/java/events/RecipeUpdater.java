package events;

import javax.enterprise.event.Observes;

/**
 * Receives Recipes as they get updated
 * @author ian
 *
 */
public class RecipeUpdater {
	
	public void updateRecipe(@Observes Recipe recipe) {
		System.out.println("Updating " + recipe);
		// ...
	}
}
