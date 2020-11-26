package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.logger.Logger;
import org.junit.jupiter.api.Test;

public class ScarletLoggerFactoryTest {

	@Test
	public void testGet() {
		Logger loggerFactoryTest = LoggerFactory.get(ScarletLoggerFactoryTest.class);
		assertNotNull(loggerFactoryTest);
		assertEquals(loggerFactoryTest, LoggerFactory.get(ScarletLoggerFactoryTest.class));
	}

	@Test
	public void testGetLoggerBuilder() {
		assertNotNull(LoggerFactory.getLoggerBuilder());
		assertTrue(LoggerFactory.getLoggerBuilder() instanceof DefaultLoggerBuilder);
	}

	@Test
	public void testSetLoggerBuilder() {
		DefaultLoggerBuilder defaultLoggerBuilder = new DefaultLoggerBuilder();
		assertNotEquals(defaultLoggerBuilder, LoggerFactory.getLoggerBuilder());

		LoggerFactory.setLoggerBuilder(defaultLoggerBuilder);
		assertEquals(defaultLoggerBuilder, LoggerFactory.getLoggerBuilder());
	}

}
