package org.diverproject.scarlet.stream.language;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamLanguageTest {

	@Test
	void getCode() {
		assertEquals(1, StreamLanguage.GET_OBJECT_CONSTRUCTOR.getCode());
	}
}