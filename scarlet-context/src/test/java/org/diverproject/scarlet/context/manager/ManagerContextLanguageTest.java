package org.diverproject.scarlet.context.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ManagerContextLanguageTest {

	@Test
	void testGetCode() {
		assertEquals(1, ManagerContextLanguage.MANAGER_REGISTERED_SUCCESSFULLY.getCode());
	}

}
