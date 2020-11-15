package org.diverproject.scarlet.logger.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Simple Message")
public class SimpleMessageTest {

	@Test
	@DisplayName("Get output")
	public void testGetOutput() {
		SimpleMessage simpleLoggerMessage = new SimpleMessage("a logger output text");
		assertEquals(simpleLoggerMessage.getOutput(), "a logger output text");

		simpleLoggerMessage.setOutput("another logger output text");
		assertEquals(simpleLoggerMessage.getOutput(), "another logger output text");
	}
}
