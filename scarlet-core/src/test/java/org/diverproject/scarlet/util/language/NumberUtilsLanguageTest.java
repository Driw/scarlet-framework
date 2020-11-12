package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberUtilsLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, NumberUtilsLanguage.HAS_INTEGER_LENGTH_MIN_MAX.getCode());
	}

}
