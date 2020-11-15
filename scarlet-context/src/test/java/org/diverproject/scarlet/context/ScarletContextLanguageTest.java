package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ScarletContextLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, ScarletContextLanguage.REPLACING_CONTEXT_INSTANCE.getCode());
	}

}
