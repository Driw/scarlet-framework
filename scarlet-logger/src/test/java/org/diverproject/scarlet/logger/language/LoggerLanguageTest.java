package org.diverproject.scarlet.logger.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoggerLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, LoggerLanguage.DEFAULT_CLASS_LOGGER_NOT_SET.getCode());
	}

}
