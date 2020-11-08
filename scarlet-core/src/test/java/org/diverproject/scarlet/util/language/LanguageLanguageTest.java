package org.diverproject.scarlet.util.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, LanguageLanguage.LOAD_LANGUAGE_MAPPER_INPUT_EXCEPTION.getCode());
	}

}
