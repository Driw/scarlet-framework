package org.diverproject.scarlet.logger.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.log4j.Level;
import org.junit.jupiter.api.Test;

class ScarletLoggerLevelTest {

	@Test
	void getLabel() {
		assertEquals("DEBUG", ScarletLoggerLevel.DEBUG.label());
	}

	@Test
	void getLevel() {
		assertEquals(Level.DEBUG, ScarletLoggerLevel.DEBUG.level());
	}

}
