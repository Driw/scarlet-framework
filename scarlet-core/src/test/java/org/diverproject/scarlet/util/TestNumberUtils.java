package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Number Utils")
public class TestNumberUtils {

	@Test
	@DisplayName("Has integer format")
	public void testIsIntegerFormat() {
		String[] integers = new String[]{"1", "111", "-1", "-111"};
		String[] notIntegers = new String[]{" 1 ", "1 ", " 1", "a", "1a", "a1", "-1a"};

		for (String str : integers)
			assertTrue(NumberUtils.hasIntegerFormat(str));

		for (String str : notIntegers)
			assertFalse(NumberUtils.hasIntegerFormat(str));
	}

	@Test
	@DisplayName("Is Float")
	public void testHasFloatFormat() {
		String[] dotFloats = new String[]{"1.", ".1", "1.1", "-1.", "-.1", "-1.1", "1.1e+1", "1.1e-1"};
		String[] commaFloats = new String[]{"1,", ",1", "1,1", "-1,", "-,1", "-1,1", "1,1e+1", "1,1e-1"};
		String[] dotNofFloats = new String[]{".", "a.", "1a.", "a1.", ".a", ".a1", ".1a", "1.1+e1", "1.1-e1", " 1.", ".1 "};
		String[] commaNotFloats = new String[]{",", "a,", "1a,", "a1,", ",a", ",a1", ",1a", "1,1+e1", "1,1-e1", " 1,", ",1 "};

		for (String str : dotFloats) {
			assertTrue(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_ANY_TYPE));
			assertTrue(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_DOT_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_COMMA_TYPE));
		}

		for (String str : commaFloats) {
			assertTrue(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_ANY_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_DOT_TYPE));
			assertTrue(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_COMMA_TYPE));
		}

		for (String str : dotNofFloats) {
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_ANY_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_DOT_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_COMMA_TYPE));
		}

		for (String str : commaNotFloats) {
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_ANY_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_DOT_TYPE));
			assertFalse(NumberUtils.hasFloatFormat(str, NumberUtils.DECIMAL_COMMA_TYPE));
		}
	}

	@Test
	@DisplayName("Has float precision")
	public void testHasFloatPrecision() {
		assertTrue(NumberUtils.hasFloatPrecision("0.00000", 7));
		assertTrue(NumberUtils.hasFloatPrecision("0.000000", 7));
		assertFalse(NumberUtils.hasFloatPrecision("0.0000000", 7));

		assertTrue(NumberUtils.hasFloatPrecision("00000.0", 7));
		assertTrue(NumberUtils.hasFloatPrecision("000000.0", 7));
		assertFalse(NumberUtils.hasFloatPrecision("0000000.0", 7));
	}

	@Test
	@DisplayName("Is numeric")
	public void testIsNumeric() {
		String numbers = "0123456789";

		for (char c = 0; c < 255; c++) {
			if (numbers.contains(Character.toString(c)))
				assertTrue(NumberUtils.isNumeric(c));
			else
				assertFalse(NumberUtils.isNumeric(c));
		}
	}

	@Test
	@DisplayName("Compare String numbers")
	public void testCompareStringNumbers() {
		assertEquals(NumberUtils.compareStringNumber("a", "1"), NumberUtils.COMPARE_FAILURE);
		assertEquals(NumberUtils.compareStringNumber("1", "a"), NumberUtils.COMPARE_FAILURE);
		assertEquals(NumberUtils.compareStringNumber("a", "a"), NumberUtils.COMPARE_FAILURE);

		assertEquals(NumberUtils.compareStringNumber("-1", "-1"), NumberUtils.COMPARE_EQUALS);
		assertEquals(NumberUtils.compareStringNumber("0", "0"), NumberUtils.COMPARE_EQUALS);
		assertEquals(NumberUtils.compareStringNumber("1", "1"), NumberUtils.COMPARE_EQUALS);
		assertEquals(NumberUtils.compareStringNumber("+1", "1"), NumberUtils.COMPARE_EQUALS);
		assertEquals(NumberUtils.compareStringNumber("1", "+1"), NumberUtils.COMPARE_EQUALS);

		assertEquals(NumberUtils.compareStringNumber("-2", "-1"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("-20", "-1"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("-20", "-2"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("-20", "-3"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("1", "2"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("1", "20"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("2", "20"), NumberUtils.COMPARE_MINOR);
		assertEquals(NumberUtils.compareStringNumber("3", "20"), NumberUtils.COMPARE_MINOR);

		assertEquals(NumberUtils.compareStringNumber("-1", "-2"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("-1", "-20"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("-2", "-20"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("-3", "-20"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("2", "1"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("20", "1"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("20", "2"), NumberUtils.COMPARE_MAJOR);
		assertEquals(NumberUtils.compareStringNumber("20", "3"), NumberUtils.COMPARE_MAJOR);
	}
}
