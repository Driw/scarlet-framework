package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringUtilsLanguageTest {

	@Test
	public void testGetCode() {
		assertEquals(1, StringUtilsLanguage.VAR_LOWER_CASE.getCode());
	}

}
