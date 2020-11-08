package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.Test;

class FloatParserTest {

	@Test
	void testParseFloat() {
		FloatParser floatParser = new FloatParser();
		floatParser.setRaw("1.2");
		assertEquals(1.2F, floatParser.parseFloat());

		floatParser.setExponent(Float.MIN_EXPONENT - 1);
		assertThrows(NumberUtilsRuntimeException.class, floatParser::parseFloat);

		floatParser.setExponent(Float.MAX_EXPONENT + 1);
		assertThrows(NumberUtilsRuntimeException.class, floatParser::parseFloat);
	}

}
