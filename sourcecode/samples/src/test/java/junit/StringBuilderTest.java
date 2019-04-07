package junit;

import org.junit.*;
import static org.junit.Assert.*;

public class StringBuilderTest {

	private StringBuilder sb;

	@Before
	public void init() {
		sb = new StringBuilder();
	}

	@Test
	public void testConcatenation() {
		sb.append("Robin");
		sb.append(' ').append("Williams");
		String actual = sb.toString();
		assertEquals("Name concatenation", "Robin Williams", actual);
	}

}
