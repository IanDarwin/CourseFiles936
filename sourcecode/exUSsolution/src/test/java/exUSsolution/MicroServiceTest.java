package exUSsolution;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ticketmanor.microservice.MicroService;

public class MicroServiceTest {

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
