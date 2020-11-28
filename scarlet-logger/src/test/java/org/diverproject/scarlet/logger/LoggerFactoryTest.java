package org.diverproject.scarlet.logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LoggerFactoryTest {

	@Test
	void testGetDefault() {
		assertNotNull(LoggerFactory.getDefault());
	}

	@Test
	void testGet() {
		assertNotNull(LoggerFactory.get(LoggerFactoryTest.class));
	}

}
