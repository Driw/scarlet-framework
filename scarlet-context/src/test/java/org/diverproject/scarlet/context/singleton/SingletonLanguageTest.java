package org.diverproject.scarlet.context.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingletonLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, SingletonLanguage.SINGLETON_NOT_FOUND.getCode());
	}

}
