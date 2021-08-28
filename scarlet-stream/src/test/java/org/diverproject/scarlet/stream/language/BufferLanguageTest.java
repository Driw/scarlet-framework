package org.diverproject.scarlet.stream.language;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufferLanguageTest {

	@Test
	void getCode() {
		assertEquals(1, BufferLanguage.BUFFER_FACTORY_IO_EXCEPTION.getCode());
	}
}