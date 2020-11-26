package org.diverproject.scarlet.logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ScarletLoggerFactoryTest {

	@Test
	void testGetDefault() {
		assertNull(LoggerFactory.getDefault());
	}

	@Test
	void testGet() {
		Logger logger = LoggerFactory.get(LoggerFactory.class);
		assertNotNull(logger);
		logger.debug("The message arg1 {}, arg2 {}", "value1", "value2");
		logger.info("The message arg1 {}, arg2 {}", "value1", "value2");
		logger.notice("The message arg1 {}, arg2 {}", "value1", "value2");
		logger.packet("The message arg1 {}, arg2 {}", "value1", "value2");
		logger.warn("The message arg1 {}, arg2 {}", "value1", "value2");
		logger.error("The message arg1 {}, arg2 {}", "value1", "value2");
		//logger.info("The message with exception", new Exception("The exception"));
	}

}
