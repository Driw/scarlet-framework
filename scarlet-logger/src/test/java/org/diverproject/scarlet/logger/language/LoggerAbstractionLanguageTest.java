package org.diverproject.scarlet.logger.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoggerAbstractionLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, LoggerAbstractionLanguage.CANNOT_REGISTER_LOGGER_OBSERVER.getCode());
	}

}
