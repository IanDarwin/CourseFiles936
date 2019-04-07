package jsonb;

import static org.junit.Assert.assertEquals;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** Simple test to ensure that JSON-B annotations on Person class are OK. 
 * Also demonstrates how to use JSON-B API at its most basic level. 
 */
public class PersonTest {
	
	private static final String STATIC_JSON = "{\"firstName\":\"Top\",\"lastName\":\"Dog\"}";

	// Our interface to the JSON-B system
	static Jsonb jsonb;
	
	Person person;
	
	@BeforeClass
	public static void beforeAll() {
		jsonb = JsonbBuilder.create(); // omits optional configuration
	}
	
	@Before
	public void beforeTest() {
		person = new Person("Top", "Dog");
	}

	/** Test serialize */
	@Test
	public void testToJson() {
		System.out.println("PersonTest.testToJson()");
		String result = jsonb.toJson(person);
		System.out.println(result);
		assertEquals(STATIC_JSON, result);
	}
	
	/** Test de-serialize */
	@Test
	public void testFromJson() {
		System.out.println("PersonTest.testFromJson()");
		Person p = jsonb.fromJson(STATIC_JSON, Person.class);
		assertEquals("Top", p.firstName);
		assertEquals("Dog", p.surName);
	}

}
