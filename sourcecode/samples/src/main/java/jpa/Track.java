package jpa;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Track {
	@Id Long id;
	String name;
	@OneToOne	// One of these two MUST be
	@Embedded	// commented out for JPA to work
	Duration runningTime;
}
