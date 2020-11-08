package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReflectionErrorTest {

	@Test
	void testCode() {
		assertEquals(1, ReflectionError.EMPTY_CONSTRUCTOR_EXCEPTION.getCode());
	}

}
