package org.diverproject.scarlet.logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class DefaultMapLoggerTest {

	private DefaultMapLogger<Logger> mapLogger;

	@BeforeEach
	void setUp() {
		this.mapLogger = new DefaultMapLogger<>();
	}

	@Test
	void get() {
		assertNull(this.mapLogger.get("default"));

		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");

		this.mapLogger.add(logger);
		Logger loggerGet = this.mapLogger.get("default");
		assertNotNull(loggerGet);
		assertEquals(logger, loggerGet);
	}

	@Test
	void add() {
		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");

		assertTrue(this.mapLogger.add(logger));
		assertFalse(this.mapLogger.add(logger));
	}

	@Test
	void contains() {
		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");

		assertFalse(this.mapLogger.contains("default"));
		assertFalse(this.mapLogger.contains(logger));

		assertTrue(this.mapLogger.add(logger));
		assertTrue(this.mapLogger.contains("default"));
		assertTrue(this.mapLogger.contains(logger));
	}

	@Test
	void hasAvailableName() {
		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");

		assertTrue(this.mapLogger.hasAvailableName("default"));

		assertTrue(this.mapLogger.add(logger));
		assertFalse(this.mapLogger.hasAvailableName("default"));
	}

	@Test
	void clear() throws IOException {
		assertEquals(0, this.mapLogger.size());

		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");
		assertTrue(this.mapLogger.add(logger));
		assertEquals(1, this.mapLogger.size());

		this.mapLogger.clear();
		assertEquals(0, this.mapLogger.size());

		logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");
		doThrow(new IOException("Failure on close logger")).when(logger).close();
		assertTrue(this.mapLogger.add(logger));
		assertThrows(LoggerException.class, () -> this.mapLogger.clear());
	}

	@Test
	void size() {
		assertEquals(0, this.mapLogger.size());

		Logger logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default");
		assertTrue(this.mapLogger.add(logger));
		assertEquals(1, this.mapLogger.size());

		logger = Mockito.mock(Logger.class);
		when(logger.getName()).thenReturn("default2");
		assertTrue(this.mapLogger.add(logger));
		assertEquals(2, this.mapLogger.size());
	}

}
