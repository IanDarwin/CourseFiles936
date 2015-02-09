package com.ticketmanor;

import org.junit.*;
import static org.junit.Assert.*;

public class StringBuilderTest {

	@Test
	public void testConcatenation() {
		StringBuilder sb = new StringBuilder("Ian");
		sb.append(' ').append("Darwin");
		String actual = sb.toString();
		assertEquals("Name concatenation", "Ian Darwin", actual);
	}
}
