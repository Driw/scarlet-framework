package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HexadecimalUtilsLanguageTest {

	@Test
	void testgetCode() {
		assertEquals(1, HexadecimalUtilsLanguage.TO_HEX_NEGATIVE_BYTE.getCode());
	}

}
