package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BinaryUtilsLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, BinaryUtilsLanguage.SHIFT_OFFSET.getCode());
	}

}
