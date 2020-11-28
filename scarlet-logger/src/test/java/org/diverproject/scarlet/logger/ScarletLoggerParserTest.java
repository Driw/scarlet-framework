package org.diverproject.scarlet.logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.logger.simple.SimpleLogger;
import org.junit.jupiter.api.Test;

class ScarletLoggerParserTest {

	private static LoggerFactory loggerFactory;

	@Test
	void get() {
		Logger logger = ScarletLoggerParser.get(ScarletLoggerParserTest.class);
		assertNotNull(logger);
		assertTrue(logger instanceof SimpleLogger);
		assertEquals(logger, ScarletLoggerParser.get(ScarletLoggerParserTest.class.getName()));
	}

	@Test
	void getDefault() {
		Logger logger = ScarletLoggerParser.getDefault();
		assertNotNull(logger);
		assertEquals(ScarletLoggerParser.DEFAULT_LOGGER_NAME, logger.getName());
	}

}
