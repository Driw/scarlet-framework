package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Long Utils")
class LongUtilsTest {

	@Test
	@DisplayName("String is long number")
	public void testIsLong() {
		assertFalse(LongUtils.isLong("-9223372036854775809"));
		assertTrue(LongUtils.isLong("-9223372036854775808"));
		assertTrue(LongUtils.isLong("-9223372036854775807"));

		assertTrue(LongUtils.isLong("9223372036854775806"));
		assertTrue(LongUtils.isLong("9223372036854775807"));
		assertFalse(LongUtils.isLong("9223372036854775808"));

		assertFalse(LongUtils.isLong("A"));
		assertFalse(LongUtils.isLong("A1"));
		assertFalse(LongUtils.isLong("1A"));
		assertFalse(LongUtils.isLong("1."));
		assertFalse(LongUtils.isLong("1.0"));
		assertFalse(LongUtils.isLong(" 1"));
		assertFalse(LongUtils.isLong("1 "));
	}

	@Test
	@DisplayName("String is all long numbers")
	public void testIsAllLong() {
		assertTrue(LongUtils.isAllLong(new String[]{"-9223372036854775808", "-9223372036854775807", "0", "9223372036854775806", "+9223372036854775807"}));

		assertFalse(LongUtils.isAllLong(new String[]{"0", "-9223372036854775809"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "9223372036854775808"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "A"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "A1"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "1A"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "1."}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "1.0"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", " 1"}));
		assertFalse(LongUtils.isAllLong(new String[]{"0", "1 "}));
	}

	@Test
	@DisplayName("Parse string to long")
	public void testParseLong() {
		assertEquals(-9223372036854775808L, LongUtils.parseLong("-9223372036854775808"));
		assertEquals(-1L, LongUtils.parseLong("-1"));
		assertEquals(0L, LongUtils.parseLong("0"));
		assertEquals(1L, LongUtils.parseLong("1"));
		assertEquals(9223372036854775807L, LongUtils.parseLong("+9223372036854775807"));

		assertEquals(0L, LongUtils.parseLong("-9223372036854775809", 0L));
		assertEquals(0L, LongUtils.parseLong("9223372036854775808", 0L));
		assertEquals(0L, LongUtils.parseLong(" 1", 0L));
		assertEquals(0L, LongUtils.parseLong("A", 0L));
		assertEquals(0L, LongUtils.parseLong("1A", 0L));
		assertEquals(0L, LongUtils.parseLong("A1", 0L));

		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong(null));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong(""));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong("-9223372036854775809"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong("9223372036854775808"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLong("A1"));
	}

	@Test
	@DisplayName("Parse string to long object")
	public void testParseLongObject() {
		assertNull(LongUtils.parseLongObject(null));
		assertNull(LongUtils.parseLongObject(""));
		assertEquals(-9223372036854775808L, LongUtils.parseLongObject("-9223372036854775808"));
		assertEquals(-1L, LongUtils.parseLongObject("-1"));
		assertEquals(0L, LongUtils.parseLongObject("0"));
		assertEquals(1L, LongUtils.parseLongObject("1"));
		assertEquals(9223372036854775807L, LongUtils.parseLongObject("+9223372036854775807"));

		assertEquals(0L, LongUtils.parseLongObject("-9223372036854775809", 0L));
		assertEquals(0L, LongUtils.parseLongObject("9223372036854775808", 0L));
		assertEquals(0L, LongUtils.parseLongObject(" 1", 0L));
		assertEquals(0L, LongUtils.parseLongObject("A", 0L));
		assertEquals(0L, LongUtils.parseLongObject("1A", 0L));
		assertEquals(0L, LongUtils.parseLongObject("A1", 0L));

		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject("-9223372036854775809"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject("9223372036854775808"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> LongUtils.parseLongObject("A1"));
	}

	@Test
	@DisplayName("Parse unsigned byte")
	public void testParseUnsignedByte() {
		assertEquals(0x00L, LongUtils.parseUnsignedByte((byte) 0x00));
		assertEquals(0x7FL, LongUtils.parseUnsignedByte((byte) 0x7F));
		assertEquals(0x80L, LongUtils.parseUnsignedByte((byte) 0x80));
		assertEquals(0xFFL, LongUtils.parseUnsignedByte((byte) 0xFF));
	}

	@Test
	@DisplayName("Parse unsigned short")
	public void testParseUnsignedShort() {
		assertEquals(0x0000L, LongUtils.parseUnsignedShort((short) 0x0000));
		assertEquals(0x7FFFL, LongUtils.parseUnsignedShort((short) 0x7FFF));
		assertEquals(0x8000L, LongUtils.parseUnsignedShort((short) 0x8000));
		assertEquals(0xFFFFL, LongUtils.parseUnsignedShort((short) 0xFFFF));
	}

	@Test
	@DisplayName("Parse unsigned int")
	public void testParseUnsignedInt() {
		assertEquals(0x00000000L, LongUtils.parseUnsignedInteger(0x00000000));
		assertEquals(0x7FFFFFFFL, LongUtils.parseUnsignedInteger(0x7FFFFFFF));
		assertEquals(0x80000000L, LongUtils.parseUnsignedInteger(0x80000000));
		assertEquals(0xFFFFFFFFL, LongUtils.parseUnsignedInteger(0xFFFFFFFF));
	}

	@Test
	@DisplayName("Cap range long value")
	public void testCap() {
		assertEquals(5, LongUtils.cap(4, 5, 10));
		assertEquals(5, LongUtils.cap(5, 5, 10));
		assertEquals(6, LongUtils.cap(6, 5, 10));
		assertEquals(9, LongUtils.cap(9, 5, 10));
		assertEquals(10, LongUtils.cap(10, 5, 10));
		assertEquals(10, LongUtils.cap(11, 5, 10));

		assertEquals(-10, LongUtils.cap(-11, -10, -5));
		assertEquals(-10, LongUtils.cap(-10, -10, -5));
		assertEquals(-9, LongUtils.cap(-9, -10, -5));
		assertEquals(-6, LongUtils.cap(-6, -10, -5));
		assertEquals(-5, LongUtils.cap(-5, -10, -5));
		assertEquals(-5, LongUtils.cap(-4, -10, -5));
	}

	@Test
	@DisplayName("Cap min long value")
	public void testCapMin() {
		assertEquals(5, LongUtils.capMin(4, 5));
		assertEquals(5, LongUtils.capMin(5, 5));
		assertEquals(6, LongUtils.capMin(6, 5));

		assertEquals(-4, LongUtils.capMin(-4, -5));
		assertEquals(-5, LongUtils.capMin(-5, -5));
		assertEquals(-5, LongUtils.capMin(-6, -5));
	}

	@Test
	@DisplayName("Cap max long value")
	public void testCapMax() {
		assertEquals(5, LongUtils.capMin(4, 5));
		assertEquals(5, LongUtils.capMin(5, 5));
		assertEquals(6, LongUtils.capMin(6, 5));

		assertEquals(-4, LongUtils.capMin(-4, -5));
		assertEquals(-5, LongUtils.capMin(-5, -5));
		assertEquals(-5, LongUtils.capMin(-6, -5));
	}

	@Test
	@DisplayName("Has min long value")
	public void testHasMin() {
		assertFalse(LongUtils.hasMin(4, 5));
		assertTrue(LongUtils.hasMin(5, 5));
		assertTrue(LongUtils.hasMin(6, 5));

		assertTrue(LongUtils.hasMin(-4, -5));
		assertTrue(LongUtils.hasMin(-5, -5));
		assertFalse(LongUtils.hasMin(-6, -5));
	}

	@Test
	@DisplayName("Has max long value")
	public void testHasMax() {
		assertTrue(LongUtils.hasMax(4, 5));
		assertTrue(LongUtils.hasMax(5, 5));
		assertFalse(LongUtils.hasMax(6, 5));

		assertFalse(LongUtils.hasMax(-4, -5));
		assertTrue(LongUtils.hasMax(-5, -5));
		assertTrue(LongUtils.hasMax(-6, -5));
	}

	@Test
	@DisplayName("Has range long value")
	public void testHasBetween() {
		assertFalse(LongUtils.hasBetween(4, 5, 10));
		assertTrue(LongUtils.hasBetween(5, 5, 10));
		assertTrue(LongUtils.hasBetween(6, 5, 10));
		assertTrue(LongUtils.hasBetween(9, 5, 10));
		assertTrue(LongUtils.hasBetween(10, 5, 10));
		assertFalse(LongUtils.hasBetween(11, 5, 10));

		assertFalse(LongUtils.hasBetween(-11, -10, -5));
		assertTrue(LongUtils.hasBetween(-10, -10, -5));
		assertTrue(LongUtils.hasBetween(-9, -10, -5));
		assertTrue(LongUtils.hasBetween(-6, -10, -5));
		assertTrue(LongUtils.hasBetween(-5, -10, -5));
		assertFalse(LongUtils.hasBetween(-4, -10, -5));
	}
}
