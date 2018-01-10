package jsonb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
	
	private static final String STATIC_JSON = "{\"firstName\":\"Top\",\"lastName\":\"Dog\"}";

	// Our interface to the JSON-B system
	static Jsonb jsonb;
	
	Person person;
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("PersonTest.beforeAll()");
		jsonb = JsonbBuilder.create();
	}
	
	@BeforeEach
	public void beforeTest() {
		person = new Person("Top", "Dog");
	}

	/** Test serialize */
	@Test
	public void testToJson() {
		String result = jsonb.toJson(person);
		System.out.println(result);
		assertEquals("toJson", STATIC_JSON, result);
	}
	
	/** Test de-serialize */
	@Test
	public void testFromJson() {
		Person p = jsonb.fromJson(STATIC_JSON, Person.class);
		assertEquals("Top", p.firstName, "firstname");
		assertEquals("Dog", p.surName, "last/surname");
	}

}
