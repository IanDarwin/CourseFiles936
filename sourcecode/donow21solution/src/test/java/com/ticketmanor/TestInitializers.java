package com.ticketmanor;

import static org.junit.Assert.*;
import org.junit.*;

/** Structure of JUnit class for testing any factory-based object */
public class TestInitializers {

	static MyFactory factory;
	ClassUnderTest target;

	@BeforeClass // Runs once, has only to create/initialize the factory
	public static void initMyStaticStuff() {
		factory = new MyFactory();
	}

	@Before // Runs before each @Test method, uses factory to create target
	public void setUpMore() {
		target = factory.createObject();
	}

	@Test
	public void testName() {
		target.setName("Ian Darwin");
		String f = target.getName();
		assertTrue(f.contains("Ian"));
	}
	
	@AfterClass // Cleanup: close factory to free resources
	public static void closeDown() {
		factory.close();
	}

	// Dummy classes to make the above compile
	/** World's cheesiest factory */
	static class MyFactory {
		ClassUnderTest createObject() {
			return new ClassUnderTest();
		}

		public void close() {
			// Empty, but might need to free some resources here...
		}
	}

	/** Stand-in for an actual Class being tested */
	static class ClassUnderTest extends Object {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
// END main
