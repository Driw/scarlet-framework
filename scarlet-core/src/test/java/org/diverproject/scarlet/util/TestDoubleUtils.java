package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.Scarlet;
import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Double Utils")
public class TestDoubleUtils {

	@Test
	@DisplayName("Is a safe Double value")
	public void testIsSafeDouble() {
		assertTrue(DoubleUtils.isSafeDouble("1"));
		assertTrue(DoubleUtils.isSafeDouble("1."));
		assertTrue(DoubleUtils.isSafeDouble(".1"));
		assertTrue(DoubleUtils.isSafeDouble("1.1"));
		assertTrue(DoubleUtils.isSafeDouble("-1"));
		assertTrue(DoubleUtils.isSafeDouble("-1."));
		assertTrue(DoubleUtils.isSafeDouble("-.1"));
		assertTrue(DoubleUtils.isSafeDouble("-1.1"));
		assertTrue(DoubleUtils.isSafeDouble("+1"));
		assertTrue(DoubleUtils.isSafeDouble("+1."));
		assertTrue(DoubleUtils.isSafeDouble("+.1"));
		assertTrue(DoubleUtils.isSafeDouble("+1.1"));
		assertFalse(DoubleUtils.isSafeDouble("a1"));
		assertFalse(DoubleUtils.isSafeDouble("1a"));
		assertFalse(DoubleUtils.isSafeDouble("a1."));
		assertFalse(DoubleUtils.isSafeDouble("1a."));
		assertFalse(DoubleUtils.isSafeDouble("1.a"));
		assertFalse(DoubleUtils.isSafeDouble("a.1"));
		assertFalse(DoubleUtils.isSafeDouble(".a1"));
		assertFalse(DoubleUtils.isSafeDouble(".1a"));
		assertFalse(DoubleUtils.isSafeDouble("a1.1"));
		assertFalse(DoubleUtils.isSafeDouble("1a.1"));
		assertFalse(DoubleUtils.isSafeDouble("1.a1"));
		assertFalse(DoubleUtils.isSafeDouble("1.1a"));
		assertFalse(DoubleUtils.isSafeDouble("a-1"));
		assertFalse(DoubleUtils.isSafeDouble("-a1"));
		assertFalse(DoubleUtils.isSafeDouble("-1a"));
		assertFalse(DoubleUtils.isSafeDouble("a-1."));
		assertFalse(DoubleUtils.isSafeDouble("-a1."));
		assertFalse(DoubleUtils.isSafeDouble("-1a."));
		assertFalse(DoubleUtils.isSafeDouble("-1.a"));
		assertFalse(DoubleUtils.isSafeDouble("a-.1"));
		assertFalse(DoubleUtils.isSafeDouble("-a.1"));
		assertFalse(DoubleUtils.isSafeDouble("-.a1"));
		assertFalse(DoubleUtils.isSafeDouble("-.1a"));
		assertFalse(DoubleUtils.isSafeDouble("a-1.1"));
		assertFalse(DoubleUtils.isSafeDouble("-a1.1"));
		assertFalse(DoubleUtils.isSafeDouble("-1a.1"));
		assertFalse(DoubleUtils.isSafeDouble("-1.a1"));
		assertFalse(DoubleUtils.isSafeDouble("-1.1a"));
		assertFalse(DoubleUtils.isSafeDouble("a+1"));
		assertFalse(DoubleUtils.isSafeDouble("+a1"));
		assertFalse(DoubleUtils.isSafeDouble("+1a"));
		assertFalse(DoubleUtils.isSafeDouble("a+1."));
		assertFalse(DoubleUtils.isSafeDouble("+a1."));
		assertFalse(DoubleUtils.isSafeDouble("+1a."));
		assertFalse(DoubleUtils.isSafeDouble("+1.a"));
		assertFalse(DoubleUtils.isSafeDouble("a+.1"));
		assertFalse(DoubleUtils.isSafeDouble("+a.1"));
		assertFalse(DoubleUtils.isSafeDouble("+.a1"));
		assertFalse(DoubleUtils.isSafeDouble("+.1a"));
		assertFalse(DoubleUtils.isSafeDouble("a+1.1"));
		assertFalse(DoubleUtils.isSafeDouble("+a1.1"));
		assertFalse(DoubleUtils.isSafeDouble("+1a.1"));
		assertFalse(DoubleUtils.isSafeDouble("+1.a1"));
		assertFalse(DoubleUtils.isSafeDouble("+1.1a"));

		Scarlet.getInstance().setFloatType(NumberUtils.DECIMAL_COMMA_TYPE);
		assertTrue(DoubleUtils.isSafeDouble("1,0"));
		assertFalse(DoubleUtils.isSafeDouble("1.0"));

		Scarlet.getInstance().setFloatType(NumberUtils.DECIMAL_DOT_TYPE);
		assertTrue(DoubleUtils.isSafeDouble("1.0"));
		assertFalse(DoubleUtils.isSafeDouble("1,0"));
	}

	@Test
	@DisplayName("Is all a safe Double value")
	public void testIsAllSafeDouble() {
		String[] strings = new String[]{
			"1",
			"1.",
			".1",
			"1.1",
			"-1",
			"-1.",
			"-.1",
			"-1.1",
			"+1",
			"+1.",
			"+.1",
			"+1.1",
		};
		assertTrue(DoubleUtils.isAllSafeDouble(strings));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a1."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1a."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1.a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{".a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{".1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a1.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1.a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"1.1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a-1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a-1."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-a1."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1a."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1.a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a-.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-.a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-.1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a-1.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-a1.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1.a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"-1.1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a+1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a+1."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+a1."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1a."}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1.a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a+.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+.a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+.1a"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"a+1.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+a1.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1a.1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1.a1"}));
		assertFalse(DoubleUtils.isAllSafeDouble(new String[]{"+1.1a"}));
	}

	@Test
	@DisplayName("Double values")
	public void testParseDouble() {
		assertEquals(123D, DoubleUtils.parseDouble("123"));
		assertEquals(123D, DoubleUtils.parseDouble("123."));
		assertEquals(.123D, DoubleUtils.parseDouble(".123"));
		assertEquals(123.123D, DoubleUtils.parseDouble("123.123"));
		assertEquals(-123D, DoubleUtils.parseDouble("-123"));
		assertEquals(-123D, DoubleUtils.parseDouble("-123."));
		assertEquals(-.123D, DoubleUtils.parseDouble("-.123"));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123.123"));
		assertEquals(123D, DoubleUtils.parseDouble("123"));
		assertEquals(123D, DoubleUtils.parseDouble("123."));
		assertEquals(.123D, DoubleUtils.parseDouble(".123"));
		assertEquals(123.123D, DoubleUtils.parseDouble("123.123"));
		assertEquals(-123D, DoubleUtils.parseDouble("-123"));
		assertEquals(-123D, DoubleUtils.parseDouble("-123."));
		assertEquals(-.123D, DoubleUtils.parseDouble("-.123"));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123.123"));

		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123,"));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(",123"));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123,123"));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123,"));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-,123"));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123,123"));

		assertEquals(0D, DoubleUtils.parseDouble("123,", 0D));
		assertEquals(0D, DoubleUtils.parseDouble(",123", 0D));
		assertEquals(0D, DoubleUtils.parseDouble("123,123", 0D));
		assertEquals(0D, DoubleUtils.parseDouble("-123,", 0D));
		assertEquals(0D, DoubleUtils.parseDouble("-,123", 0D));
		assertEquals(0D, DoubleUtils.parseDouble("-123,123", 0D));

		assertEquals(123D, DoubleUtils.parseDouble("123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("123.", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble(".123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("123.123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("+123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("+123.", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble("+.123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("+123.123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123D, DoubleUtils.parseDouble("-123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123.D, DoubleUtils.parseDouble("-123.", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-.123D, DoubleUtils.parseDouble("-.123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123.123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("123,", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble(",123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("123,123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("+123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("+123,", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble("+,123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("+123,123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123D, DoubleUtils.parseDouble("-123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123.D, DoubleUtils.parseDouble("-123,", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-.123D, DoubleUtils.parseDouble("-,123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123,123", NumberUtils.DECIMAL_ANY_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("123.", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble(".123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("123.123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("+123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("+123.", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble("+.123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("+123.123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(-123D, DoubleUtils.parseDouble("-123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(-123.D, DoubleUtils.parseDouble("-123.", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(-.123D, DoubleUtils.parseDouble("-.123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123.123", NumberUtils.DECIMAL_DOT_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("123,", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble(",123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("123,123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(123D, DoubleUtils.parseDouble("+123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(123.D, DoubleUtils.parseDouble("+123,", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(.123D, DoubleUtils.parseDouble("+,123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(123.123D, DoubleUtils.parseDouble("+123,123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(-123D, DoubleUtils.parseDouble("-123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(-123.D, DoubleUtils.parseDouble("-123,", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(-.123D, DoubleUtils.parseDouble("-,123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertEquals(-123.123D, DoubleUtils.parseDouble("-123,123", NumberUtils.DECIMAL_COMMA_TYPE));

		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123.", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(".123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123.123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+123.testNvltestNvl", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+.123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+123.123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123.", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-.123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123.123", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(",123", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("123,123", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+123,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+,123", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("+123,123", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-,123", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("-123,123", NumberUtils.DECIMAL_DOT_TYPE));
	}

	@Test
	@DisplayName("Parse double values as Object")
	public void testParseDoubleObject() {
		assertEquals(123.123D, DoubleUtils.parseDoubleObject("123.123"));
	}

	@Test
	@DisplayName("Double values")
	public void testDoubleValues() {
		DoubleParser parser = new DoubleParser();

		Object[][] parametersTest = new Object[][]{
			{"123", NumberUtils.DECIMAL_ANY_TYPE, false, true, false, "123", 3, 123D},
			{"123.", NumberUtils.DECIMAL_ANY_TYPE, false, true, false, "123.", 3, 123D},
			{".123", NumberUtils.DECIMAL_ANY_TYPE, false, true, false, ".123", 3, .123D},
			{"123.123", NumberUtils.DECIMAL_ANY_TYPE, false, true, false, "123.123", 6, 123.123D},
			{"-123", NumberUtils.DECIMAL_ANY_TYPE, false, false, true, "123", 3, -123D},
			{"-123.", NumberUtils.DECIMAL_ANY_TYPE, false, false, true, "123.", 3, -123D},
			{"-.123", NumberUtils.DECIMAL_ANY_TYPE, false, false, true, ".123", 3, -.123D},
			{"-123.123", NumberUtils.DECIMAL_ANY_TYPE, false, false, true, "123.123", 6, -123.123D},
			{"123", NumberUtils.DECIMAL_DOT_TYPE, false, true, false, "123", 3, 123D},
			{"123.", NumberUtils.DECIMAL_DOT_TYPE, false, true, false, "123.", 3, 123D},
			{".123", NumberUtils.DECIMAL_DOT_TYPE, false, true, false, ".123", 3, .123D},
			{"123.123", NumberUtils.DECIMAL_DOT_TYPE, false, true, false, "123.123", 6, 123.123D},
			{"-123", NumberUtils.DECIMAL_DOT_TYPE, false, false, true, "123", 3, -123D},
			{"-123.", NumberUtils.DECIMAL_DOT_TYPE, false, false, true, "123.", 3, -123D},
			{"-.123", NumberUtils.DECIMAL_DOT_TYPE, false, false, true, ".123", 3, -.123D},
			{"-123.123", NumberUtils.DECIMAL_DOT_TYPE, false, false, true, "123.123", 6, -123.123D},
			{"123", NumberUtils.DECIMAL_COMMA_TYPE, false, true, false, "123", 3, 123D},
			{"123,", NumberUtils.DECIMAL_COMMA_TYPE, false, true, false, "123,", 3, 123D},
			{",123", NumberUtils.DECIMAL_COMMA_TYPE, false, true, false, ",123", 3, .123D},
			{"123,123", NumberUtils.DECIMAL_COMMA_TYPE, false, true, false, "123,123", 6, 123.123D},
			{"-123", NumberUtils.DECIMAL_COMMA_TYPE, false, false, true, "123", 3, -123D},
			{"-123,", NumberUtils.DECIMAL_COMMA_TYPE, false, false, true, "123,", 3, -123D},
			{"-,123", NumberUtils.DECIMAL_COMMA_TYPE, false, false, true, ",123", 3, -.123D},
			{"-123,123", NumberUtils.DECIMAL_COMMA_TYPE, false, false, true, "123,123", 6, -123.123D},
		};

		for (Object[] parameters : parametersTest) {
			Double DoubleParsed = DoubleUtils.parseDouble((String) parameters[0], (int) parameters[1], parser);
			assertEquals(parser.isExpression(), parameters[2]);
			assertEquals(parser.isPositive(), parameters[3]);
			assertEquals(parser.isNegative(), parameters[4]);
			assertEquals(parser.getValue(), parameters[5]);
			assertEquals(parser.getPrecision(), parameters[6]);
			assertNull(parser.getExponent());
			assertEquals(parser.parseDouble(), parameters[7]);
			assertEquals(DoubleParsed, parameters[7]);
		}

		Object[][] throwsParametersTest = new Object[][] {
			{"1234567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567890123456.", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{".1234567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234.567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"-1234567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"-1234567890123456.", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"-.1234567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"-1234.567890123456", NumberUtils.DECIMAL_ANY_TYPE, 16},
		};

		for (Object[] parameters : throwsParametersTest) {
			assertThrows(NumberUtilsRuntimeException.class, () -> {
				DoubleUtils.parseDouble((String) parameters[0], (int) parameters[1], parser);
				assertEquals(parser.getPrecision(), parameters[2]);
				parser.parseDouble();
			});
		}
	}

	@Test
	@DisplayName("Double expressions")
	public void testDoubleExpressions() {
		DoubleParser parser = new DoubleParser();

		Object[][] parametersTest = new Object[][]{
			{"1e0", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 1, 0, 1D},
			{"1e+0", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 1, 0, 1D},
			{"1e-0", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 1, 0, 1D},
			{"1e6", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 7, 6, 1000000D},
			{"1e+6", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 7, 6, 1000000D},
			{"1e-6", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 7, 6, .000001D},
			{"1.0e0", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 1, 0, 1D},
			{"1.0e+0", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 1, 0, 1D},
			{"1.0e-0", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 1, 0, 1D},
			{"1.0e6", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 7, 6, 1000000D},
			{"1.0e+6", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 7, 6, 1000000D},
			{"1.0e-6", NumberUtils.DECIMAL_DOT_TYPE, true, true, false, "1.0", 7, 6, .000001D},
			{"1,0e0", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 1, 0, 1D},
			{"1,0e+0", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 1, 0, 1D},
			{"1,0e-0", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 1, 0, 1D},
			{"1,0e6", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 7, 6, 1000000D},
			{"1,0e+6", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 7, 6, 1000000D},
			{"1,0e-6", NumberUtils.DECIMAL_COMMA_TYPE, true, true, false, "1,0", 7, 6, .000001D},
			{"1234567e0", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1234567", 7, 0, 1234567D},
			{"123456e1", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "123456", 7, 1, 1234560D},
			{"12345e2", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "12345", 7, 2, 1234500D},
			{"1234e3", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1234", 7, 3, 1234000D},
			{"123e4", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "123", 7, 4, 1230000D},
			{"12e5", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "12", 7, 5, 1200000D},
			{"1e6", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 7, 6, 1000000D},
			{"1234567e-0", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1234567", 7, 0, 1234567D},
			{"123456e-1", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "123456", 7, 1, 12345.6D},
			{"12345e-2", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "12345", 7, 2, 123.450D},
			{"1234e-3", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1234", 7, 3, 1.23400D},
			{"123e-4", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "123", 7, 4, .012300D},
			{"12e-5", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "12", 7, 5, .000120D},
			{"1e-6", NumberUtils.DECIMAL_ANY_TYPE, true, true, false, "1", 7, 6, .000001D},
		};

		for (Object[] parameters : parametersTest) {
			Double DoubleParsed = DoubleUtils.parseDouble((String) parameters[0], (int) parameters[1], parser);
			assertEquals(parser.isExpression(), parameters[2]);
			assertEquals(parser.isPositive(), parameters[3]);
			assertEquals(parser.isNegative(), parameters[4]);
			assertEquals(parser.getValue(), parameters[5]);
			assertEquals(parser.getPrecision(), parameters[6]);
			assertEquals(parser.getExponent(), parameters[7]);
			assertEquals(parser.parseDouble(), parameters[8]);
			assertEquals(DoubleParsed, parameters[8]);
		}

		Object[][] throwsParametersTest = new Object[][]{
			{"1e15", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1e+15", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1e-15", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12e14", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12e+14", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12e-14", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123e13", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123e+13", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123e-13", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234e12", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234e+12", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234e-12", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345e11", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345e+11", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345e-11", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456e10", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456e+10", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456e-10", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567e9", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567e+9", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567e-9", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678e8", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678e+8", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678e-8", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789e7", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789e+7", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789e-7", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567899e6", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567899e+6", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567899e-6", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901e5", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901e+5", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901e-5", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012e4", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012e+4", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012e-4", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567890123e3", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567890123e+3", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"1234567890123e-3", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901234e2", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901234e+2", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"12345678901234e-2", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012345e1", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012345e+1", NumberUtils.DECIMAL_ANY_TYPE, 16},
			{"123456789012345e-1", NumberUtils.DECIMAL_ANY_TYPE, 16},
		};

		for (Object[] parameters : throwsParametersTest) {
			assertThrows(NumberUtilsRuntimeException.class, () -> {
				DoubleUtils.parseDouble((String) parameters[0], (int) parameters[1], parser);
				assertEquals(parser.getPrecision(), parameters[2]);
				parser.parseDouble();
			});
		}

		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("ae0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("a1e0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1ae0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1e0,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(",1e0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1,1e0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.e0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(".1e0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.1e0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("ae+0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("a1e+0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1ae+0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1e+0,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(",1e+0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1,1e+0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.e+0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(".1e+0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.1e+0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("ae-0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("a1e-0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1ae-0", NumberUtils.DECIMAL_ANY_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1e-0,", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(",1e-0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1,1e-0", NumberUtils.DECIMAL_DOT_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.e-0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble(".1e-0", NumberUtils.DECIMAL_COMMA_TYPE));
		assertThrows(NumberUtilsRuntimeException.class, () -> DoubleUtils.parseDouble("1.1e-0", NumberUtils.DECIMAL_COMMA_TYPE));
	}

	@Test
	@DisplayName("Cap min Double value")
	public void testCapMin() {
		assertEquals(5, DoubleUtils.capMin(4f, 5f));
		assertEquals(5, DoubleUtils.capMin(5f, 5f));
		assertEquals(6, DoubleUtils.capMin(6f, 5f));

		assertEquals(-4, DoubleUtils.capMin(-4f, -5f));
		assertEquals(-5, DoubleUtils.capMin(-5f, -5f));
		assertEquals(-5, DoubleUtils.capMin(-6f, -5f));
	}

	@Test
	@DisplayName("Cap max Double value")
	public void testCapMax() {
		assertEquals(5, DoubleUtils.capMin(4f, 5f));
		assertEquals(5, DoubleUtils.capMin(5f, 5f));
		assertEquals(6, DoubleUtils.capMin(6f, 5f));

		assertEquals(-4, DoubleUtils.capMin(-4f, -5f));
		assertEquals(-5, DoubleUtils.capMin(-5f, -5f));
		assertEquals(-5, DoubleUtils.capMin(-6f, -5f));
	}

	@Test
	@DisplayName("Cap min and max Double value")
	public void testCap() {
		assertEquals(5f, DoubleUtils.cap(4f, 5f, 10f));
		assertEquals(5f, DoubleUtils.cap(5f, 5f, 10f));
		assertEquals(6f, DoubleUtils.cap(6f, 5f, 10f));
		assertEquals(9f, DoubleUtils.cap(9f, 5f, 10f));
		assertEquals(10f, DoubleUtils.cap(10f, 5f, 10f));
		assertEquals(10f, DoubleUtils.cap(11f, 5f, 10f));

		assertEquals(-10f, DoubleUtils.cap(-11f, -10f, -5f));
		assertEquals(-10f, DoubleUtils.cap(-10f, -10f, -5f));
		assertEquals(-9f, DoubleUtils.cap(-9f, -10f, -5f));
		assertEquals(-6f, DoubleUtils.cap(-6f, -10f, -5f));
		assertEquals(-5f, DoubleUtils.cap(-5f, -10f, -5f));
		assertEquals(-5f, DoubleUtils.cap(-4f, -10f, -5f));
	}

	@Test
	@DisplayName("Has min Double value")
	public void testHasMin() {
		assertFalse(DoubleUtils.hasMin(4f, 5f));
		assertTrue(DoubleUtils.hasMin(5f, 5f));
		assertTrue(DoubleUtils.hasMin(6f, 5f));

		assertTrue(DoubleUtils.hasMin(-4f, -5f));
		assertTrue(DoubleUtils.hasMin(-5f, -5f));
		assertFalse(DoubleUtils.hasMin(-6f, -5f));
	}

	@Test
	@DisplayName("Has max Double value")
	public void testHasMax() {
		assertTrue(DoubleUtils.hasMax(4f, 5f));
		assertTrue(DoubleUtils.hasMax(5f, 5f));
		assertFalse(DoubleUtils.hasMax(6f, 5f));

		assertFalse(DoubleUtils.hasMax(-4f, -5f));
		assertTrue(DoubleUtils.hasMax(-5f, -5f));
		assertTrue(DoubleUtils.hasMax(-6f, -5f));
	}

	@Test
	@DisplayName("Has range Double value")
	public void testHasBetween() {
		assertFalse(DoubleUtils.hasBetween(4f, 5, 10));
		assertTrue(DoubleUtils.hasBetween(5f, 5, 10));
		assertTrue(DoubleUtils.hasBetween(6f, 5, 10));
		assertTrue(DoubleUtils.hasBetween(9f, 5, 10));
		assertTrue(DoubleUtils.hasBetween(10f, 5, 10));
		assertFalse(DoubleUtils.hasBetween(11f, 5, 10));

		assertFalse(DoubleUtils.hasBetween(-11f, -10f, -5f));
		assertTrue(DoubleUtils.hasBetween(-10f, -10f, -5f));
		assertTrue(DoubleUtils.hasBetween(-9f, -10f, -5f));
		assertTrue(DoubleUtils.hasBetween(-6f, -10f, -5f));
		assertTrue(DoubleUtils.hasBetween(-5f, -10f, -5f));
		assertFalse(DoubleUtils.hasBetween(-4f, -10f, -5f));
	}
}
