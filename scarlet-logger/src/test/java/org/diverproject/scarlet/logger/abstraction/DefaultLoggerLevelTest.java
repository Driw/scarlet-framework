package org.diverproject.scarlet.logger.abstraction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DefaultLoggerLevelTest {

	@Test
	void testCode() {
		assertEquals(1, DefaultLoggerLevel.NONE.getCode());
	}

}
