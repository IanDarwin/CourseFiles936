package jsonb;

import javax.json.bind.annotation.JsonbProperty;

public class Person {

	public String firstName;
	@JsonbProperty(value="lastName")
	public String surName;

	public Person() {
		// empty
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.surName = lastName;
	}
}
