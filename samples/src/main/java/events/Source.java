package events;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class Source {

	@Inject Event<Recipe> event;
	
	public void newRecipe() {
		Recipe recipe = new Recipe();
		
		// Fill in fields of Recipe here...
		recipe.setTitle("How to graxify blings");
		// ...
		
		// Now notify any Recipe observers
		event.fire(recipe);
	}
}
