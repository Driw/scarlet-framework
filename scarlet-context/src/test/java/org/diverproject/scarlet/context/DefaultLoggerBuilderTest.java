package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.simple.SimpleLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultLoggerBuilderTest {

	private DefaultLoggerBuilder loggerBuilder;

	@BeforeEach
	void setUp() {
		this.loggerBuilder = new DefaultLoggerBuilder();
	}

	@Test
	void generateKey() {
		assertEquals(DefaultLoggerBuilderTest.class.getName(), this.loggerBuilder.generateKey(DefaultLoggerBuilderTest.class));
	}

	@Test
	void generateLoggerClass() {
		assertEquals(SimpleLogger.class, this.loggerBuilder.generateLoggerClass(DefaultLoggerBuilder.class));
	}

}
