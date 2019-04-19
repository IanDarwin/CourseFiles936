package jpa;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A Duration class represents the running time of a recording.
 * @author Learning Tree Java Team
 */
@Entity		// One of these two must
@Embeddable	// be commented out for JPA to work
public class Duration {
	@Id Long id;	// Remove if using @Embeddable
	int hours, minutes, seconds;
}
