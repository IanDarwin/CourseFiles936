package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ticketmanor.microservice.MicroService;

public class MicroServiceTest {

	@Test
	public void testFirstnameSanitize() {
		MicroService target = new MicroService();
		// xkcd.com/327
		target.setFirstName("Robert'; Drop table students; --");
		assertEquals("name sanitize I",
			"Robert' Drop table students ", target.getFirstName());

		target.setFirstName("Robert<b><script>window.alert('Foo')");
		assertEquals("name sanitize II",
			"Robertbscriptwindowalert'Foo'", target.getFirstName());
	}

	@Test
	public void testDayPart() {
		assertEquals("Morning", MicroService.dayPart(0));
		assertEquals("Morning", MicroService.dayPart(8));
		assertEquals("Morning", MicroService.dayPart(11));
		assertEquals("Afternoon", MicroService.dayPart(12));
		assertEquals("Afternoon", MicroService.dayPart(17));
		assertEquals("Evening", MicroService.dayPart(18));
	}

}
