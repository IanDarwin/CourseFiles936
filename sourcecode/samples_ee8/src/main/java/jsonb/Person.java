package jsonb;

import javax.json.bind.annotation.JsonbProperty;

public class Person {
	String firstName;
	@JsonbProperty(value="lastName")
	String surName;
}
