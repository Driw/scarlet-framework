package org.diverproject.scarlet.stream.buffer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufferFactoryTest {

	@Test
	void inputOf() {
		int capacity = 32;
		BufferInput bufferInput = BufferFactory.inputOf(capacity);
		assertNotNull(bufferInput);
		assertEquals(capacity, bufferInput.capacity());
		assertTrue(bufferInput.isFull());
		assertFalse(bufferInput.isClosed());

		bufferInput = BufferFactory.inputOf(new byte[capacity]);
		assertNotNull(bufferInput);
		assertEquals(capacity, bufferInput.capacity());
		assertTrue(bufferInput.isFull());
		assertFalse(bufferInput.isClosed());
	}

	@Test
	void outputOf() {
	}

	@Test
	void testOutputOf() {
	}
}