package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReflectionInterfaceErrorTest {

	@Test
	void testGetCode() {
		assertEquals(1, ReflectionInterfaceError.CLASS_IS_NOT_A_INTERFACE.getCode());
	}

}
