package org.diverproject.scarlet.logger.message;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.DEBUG;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.INFO;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diverproject.scarlet.logger.abstraction.DefaultLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Advanced Logger Message")
public class AdvancedLoggerMessageTest {

	@Test
	@DisplayName("Get output")
	public void testGetOutputNoOriginLevel() {
		AdvancedMessage loggerMessage = new AdvancedMessage();
		loggerMessage.setDateFormatted("2019-07-30 01:02:03");
		loggerMessage.setMessage("a text logger");
		loggerMessage.setOrigin(null);
		loggerMessage.setLevel(null);

		assertEquals(loggerMessage.getOutput(), "2019-07-30 01:02:03 | a text logger");
	}

	@Test
	@DisplayName("Get output")
	public void testGetOutputNoOrigin() {
		AdvancedMessage loggerMessage = new AdvancedMessage();
		loggerMessage.setDateFormatted("2019-07-30 01:02:03");
		loggerMessage.setMessage("a text logger");
		loggerMessage.setOrigin(null);
		loggerMessage.setLevel(NONE);

		assertEquals(loggerMessage.getOutput(), "2019-07-30 01:02:03 | a text logger");

		loggerMessage.setLevel(DEBUG);
		assertEquals(loggerMessage.getOutput(), "[DEBUG ] 2019-07-30 01:02:03 | a text logger");
	}

	@Test
	@DisplayName("Get output")
	public void testGetOutputNoLevel() {
		AdvancedMessage loggerMessage = new AdvancedMessage();
		loggerMessage.setDateFormatted("2019-07-30 01:02:03");
		loggerMessage.setMessage("a text logger");
		loggerMessage.setOrigin(DefaultLogger.getOrigin(1)); String origin = this.getOrigin();
		loggerMessage.setLevel(null);

		assertEquals(loggerMessage.getOutput(), "2019-07-30 01:02:03 | " + origin + " - a text logger");
	}

	@Test
	@DisplayName("Get output")
	public void testGetOutput() {
		AdvancedMessage loggerMessage = new AdvancedMessage();
		loggerMessage.setDateFormatted("2019-07-30 01:02:03");
		loggerMessage.setMessage("a text logger");
		loggerMessage.setOrigin(DefaultLogger.getOrigin(1)); String origin = this.getOrigin();
		loggerMessage.setLevel(INFO);

		assertEquals(loggerMessage.getOutput(), "[INFO  ] 2019-07-30 01:02:03 | " + origin + " - a text logger");
	}

	private String getOrigin() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];

		return String.format(
			"%s.%s:%d",
			stackTraceElement.getClassName(),
			stackTraceElement.getMethodName(),
			stackTraceElement.getLineNumber()
		);
	}
}
