package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.logger.ScarletLoggers;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerLanguage;
import org.junit.jupiter.api.Test;

public class LoggerFactoryTest {

	@Test
	public void testGet() {
		Logger loggerFactoryTest = LoggerFactory.get(LoggerFactoryTest.class);
		assertNotNull(loggerFactoryTest);
		assertEquals(loggerFactoryTest, LoggerFactory.get(LoggerFactoryTest.class));

		ScarletLoggers.getInstance().add(LoggerClassExample.class.getName(), LoggerClassExample.class);
		assertThrows(ScarletRuntimeContextException.class, () -> LoggerFactory.get(LoggerClassExample.class));
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

	public static class LoggerClassExample extends DefaultLoggerLanguage {
		public LoggerClassExample(String name) {
			super(name);
		}
	}

}
