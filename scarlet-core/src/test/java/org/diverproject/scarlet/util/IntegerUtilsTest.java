package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Integer Utils")
class IntegerUtilsTest {

	@Test
	@DisplayName("String is int number")
	public void testIsInteger() {
		assertFalse(IntegerUtils.isInteger("-2147483649"));
		assertTrue(IntegerUtils.isInteger("-2147483648"));
		assertTrue(IntegerUtils.isInteger("-2147483647"));

		assertTrue(IntegerUtils.isInteger("2147483646"));
		assertTrue(IntegerUtils.isInteger("2147483647"));
		assertFalse(IntegerUtils.isInteger("2147483648"));

		assertFalse(IntegerUtils.isInteger("A"));
		assertFalse(IntegerUtils.isInteger("A1"));
		assertFalse(IntegerUtils.isInteger("1A"));
		assertFalse(IntegerUtils.isInteger("1."));
		assertFalse(IntegerUtils.isInteger("1.0"));
		assertFalse(IntegerUtils.isInteger(" 1"));
		assertFalse(IntegerUtils.isInteger("1 "));
	}

	@Test
	@DisplayName("String is all int numbers")
	public void testIsAllInteger() {
		assertTrue(IntegerUtils.isAllInteger(new String[]{"-2147483648", "-2147483647", "0", "2147483646", "+2147483647"}));

		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "-2147483649"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "2147483648"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "A"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "A1"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "1A"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "1."}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "1.0"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", " 1"}));
		assertFalse(IntegerUtils.isAllInteger(new String[]{"0", "1 "}));
	}

	@Test
	@DisplayName("Parse string to int")
	public void testParseInteger() {
		assertEquals(-2147483648, IntegerUtils.parseInt("-2147483648"));
		assertEquals(-1, IntegerUtils.parseInt("-1"));
		assertEquals(0, IntegerUtils.parseInt("0"));
		assertEquals(1, IntegerUtils.parseInt("1"));
		assertEquals(2147483647, IntegerUtils.parseInt("+2147483647"));

		assertEquals(0, IntegerUtils.parseInt("-2147483649", 0));
		assertEquals(0, IntegerUtils.parseInt("2147483648", 0));
		assertEquals(0, IntegerUtils.parseInt(" 1", 0));
		assertEquals(0, IntegerUtils.parseInt("A", 0));
		assertEquals(0, IntegerUtils.parseInt("1A", 0));
		assertEquals(0, IntegerUtils.parseInt("A1", 0));

		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt(null));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt(""));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt("-2147483649"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt("2147483648"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseInt("A1"));
	}

	@Test
	@DisplayName("Parse string to int object")
	public void testParseIntegerObject() {
		assertNull(IntegerUtils.parseIntObject(null));
		assertNull(IntegerUtils.parseIntObject(""));
		assertEquals(-2147483648, IntegerUtils.parseIntObject("-2147483648"));
		assertEquals(-1, IntegerUtils.parseIntObject("-1"));
		assertEquals(0, IntegerUtils.parseIntObject("0"));
		assertEquals(1, IntegerUtils.parseIntObject("1"));
		assertEquals(2147483647, IntegerUtils.parseIntObject("+2147483647"));

		assertEquals(0, IntegerUtils.parseIntObject("-2147483649", 0));
		assertEquals(0, IntegerUtils.parseIntObject("2147483648", 0));
		assertEquals(0, IntegerUtils.parseIntObject(" 1", 0));
		assertEquals(0, IntegerUtils.parseIntObject("A", 0));
		assertEquals(0, IntegerUtils.parseIntObject("1A", 0));
		assertEquals(0, IntegerUtils.parseIntObject("A1", 0));

		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject("-2147483649"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject("2147483648"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> IntegerUtils.parseIntObject("A1"));
	}

	@Test
	@DisplayName("Parse unsigned byte")
	public void testParseUnsignedByte() {
		assertEquals(0x00L, IntegerUtils.parseUnsignedByte((byte) 0x00));
		assertEquals(0x7FL, IntegerUtils.parseUnsignedByte((byte) 0x7F));
		assertEquals(0x80L, IntegerUtils.parseUnsignedByte((byte) 0x80));
		assertEquals(0xFFL, IntegerUtils.parseUnsignedByte((byte) 0xFF));
	}

	@Test
	@DisplayName("Parse unsigned short")
	public void testParseUnsignedShort() {
		assertEquals(0x0000L, IntegerUtils.parseUnsignedShort((short) 0x0000));
		assertEquals(0x7FFFL, IntegerUtils.parseUnsignedShort((short) 0x7FFF));
		assertEquals(0x8000L, IntegerUtils.parseUnsignedShort((short) 0x8000));
		assertEquals(0xFFFFL, IntegerUtils.parseUnsignedShort((short) 0xFFFF));
	}

	@Test
	@DisplayName("Cap min int value")
	public void testCapMin() {
		assertEquals(5, IntegerUtils.capMin(4, 5));
		assertEquals(5, IntegerUtils.capMin(5, 5));
		assertEquals(6, IntegerUtils.capMin(6, 5));

		assertEquals(-4, IntegerUtils.capMin(-4, -5));
		assertEquals(-5, IntegerUtils.capMin(-5, -5));
		assertEquals(-5, IntegerUtils.capMin(-6, -5));
	}

	@Test
	@DisplayName("Cap max int value")
	public void testCapMax() {
		assertEquals(5, IntegerUtils.capMin(4, 5));
		assertEquals(5, IntegerUtils.capMin(5, 5));
		assertEquals(6, IntegerUtils.capMin(6, 5));

		assertEquals(-4, IntegerUtils.capMin(-4, -5));
		assertEquals(-5, IntegerUtils.capMin(-5, -5));
		assertEquals(-5, IntegerUtils.capMin(-6, -5));
	}

	@Test
	@DisplayName("Cap range int value")
	public void testCap() {
		assertEquals(5, IntegerUtils.cap(4, 5, 10));
		assertEquals(5, IntegerUtils.cap(5, 5, 10));
		assertEquals(6, IntegerUtils.cap(6, 5, 10));
		assertEquals(9, IntegerUtils.cap(9, 5, 10));
		assertEquals(10, IntegerUtils.cap(10, 5, 10));
		assertEquals(10, IntegerUtils.cap(11, 5, 10));

		assertEquals(-10, IntegerUtils.cap(-11, -10, -5));
		assertEquals(-10, IntegerUtils.cap(-10, -10, -5));
		assertEquals(-9, IntegerUtils.cap(-9, -10, -5));
		assertEquals(-6, IntegerUtils.cap(-6, -10, -5));
		assertEquals(-5, IntegerUtils.cap(-5, -10, -5));
		assertEquals(-5, IntegerUtils.cap(-4, -10, -5));
	}

	@Test
	@DisplayName("Has min int value")
	public void testHasMin() {
		assertFalse(IntegerUtils.hasMin(4, 5));
		assertTrue(IntegerUtils.hasMin(5, 5));
		assertTrue(IntegerUtils.hasMin(6, 5));

		assertTrue(IntegerUtils.hasMin(-4, -5));
		assertTrue(IntegerUtils.hasMin(-5, -5));
		assertFalse(IntegerUtils.hasMin(-6, -5));
	}

	@Test
	@DisplayName("Has max int value")
	public void testHasMax() {
		assertTrue(IntegerUtils.hasMax(4, 5));
		assertTrue(IntegerUtils.hasMax(5, 5));
		assertFalse(IntegerUtils.hasMax(6, 5));

		assertFalse(IntegerUtils.hasMax(-4, -5));
		assertTrue(IntegerUtils.hasMax(-5, -5));
		assertTrue(IntegerUtils.hasMax(-6, -5));
	}

	@Test
	@DisplayName("Has range int value")
	public void testHasBetween() {
		assertFalse(IntegerUtils.hasBetween(4, 5, 10));
		assertTrue(IntegerUtils.hasBetween(5, 5, 10));
		assertTrue(IntegerUtils.hasBetween(6, 5, 10));
		assertTrue(IntegerUtils.hasBetween(9, 5, 10));
		assertTrue(IntegerUtils.hasBetween(10, 5, 10));
		assertFalse(IntegerUtils.hasBetween(11, 5, 10));

		assertFalse(IntegerUtils.hasBetween(-11, -10, -5));
		assertTrue(IntegerUtils.hasBetween(-10, -10, -5));
		assertTrue(IntegerUtils.hasBetween(-9, -10, -5));
		assertTrue(IntegerUtils.hasBetween(-6, -10, -5));
		assertTrue(IntegerUtils.hasBetween(-5, -10, -5));
		assertFalse(IntegerUtils.hasBetween(-4, -10, -5));
	}
}
