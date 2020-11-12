package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArrayUtilsLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, ArrayUtilsLanguage.SUB_ARRAY_LENGTH_INVALID.getCode());
	}

}
