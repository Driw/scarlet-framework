package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.Test;

class DoubleParserTest {

	@Test
	void testParseDouble() {
		DoubleParser doubleParser = new DoubleParser();
		doubleParser.setRaw("1.2");
		assertEquals(1.2D, doubleParser.parseDouble());

		doubleParser.setExponent(Double.MIN_EXPONENT - 1);
		assertThrows(NumberUtilsRuntimeException.class, doubleParser::parseDouble);

		doubleParser.setExponent(Double.MAX_EXPONENT + 1);
		assertThrows(NumberUtilsRuntimeException.class, doubleParser::parseDouble);
	}

}
