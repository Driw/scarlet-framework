package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Short Utils")
class TestShortUtils {

	@Test
	@DisplayName("String is short number")
	public void testIsShort() {
		assertFalse(ShortUtils.isShort("-32769"));
		assertTrue(ShortUtils.isShort("-32768"));
		assertTrue(ShortUtils.isShort("-32767"));

		assertTrue(ShortUtils.isShort("32766"));
		assertTrue(ShortUtils.isShort("32767"));
		assertFalse(ShortUtils.isShort("32768"));

		assertFalse(ShortUtils.isShort("A"));
		assertFalse(ShortUtils.isShort("A1"));
		assertFalse(ShortUtils.isShort("1A"));
		assertFalse(ShortUtils.isShort("1."));
		assertFalse(ShortUtils.isShort("1.0"));
		assertFalse(ShortUtils.isShort(" 1"));
		assertFalse(ShortUtils.isShort("1 "));
	}

	@Test
	@DisplayName("String is all short numbers")
	public void testIsAllShort() {
		assertTrue(ShortUtils.isAllShort(new String[]{"-32768", "-32767", "0", "32766", "+32767"}));

		assertFalse(ShortUtils.isAllShort(new String[]{"0", "-32769"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "32768"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "A"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "A1"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "1A"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "1."}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "1.0"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", " 1"}));
		assertFalse(ShortUtils.isAllShort(new String[]{"0", "1 "}));
	}

	@Test
	@DisplayName("Parse string to short")
	public void testParseShort() {
		assertEquals(-32768, ShortUtils.parseShort("-32768"));
		assertEquals(-1L, ShortUtils.parseShort("-1"));
		assertEquals(0L, ShortUtils.parseShort("0"));
		assertEquals(1L, ShortUtils.parseShort("1"));
		assertEquals(32767, ShortUtils.parseShort("+32767"));

		assertEquals((short) 0, ShortUtils.parseShort("-32769", (short) 0));
		assertEquals((short) 0, ShortUtils.parseShort("32768", (short) 0));
		assertEquals((short) 0, ShortUtils.parseShort(" 1", (short) 0));
		assertEquals((short) 0, ShortUtils.parseShort("A", (short) 0));
		assertEquals((short) 0, ShortUtils.parseShort("1A", (short) 0));
		assertEquals((short) 0, ShortUtils.parseShort("A1", (short) 0));

		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort(null));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort(""));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort("-32769"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort("32768"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShort("A1"));
	}

	@Test
	@DisplayName("Parse string to short object")
	public void testParseShortObject() {
		assertNull(ShortUtils.parseShortObject(null));
		assertNull(ShortUtils.parseShortObject(""));
		assertEquals(ShortUtils.parseShortObject("-32768"), (short) -32768);
		assertEquals(ShortUtils.parseShortObject("-1"), (short) -1L);
		assertEquals(ShortUtils.parseShortObject("-32768"), (short) -32768);
		assertEquals(ShortUtils.parseShortObject("-1"), (short) -1L);
		assertEquals(ShortUtils.parseShortObject("0"), (short) 0L);
		assertEquals(ShortUtils.parseShortObject("1"), (short) 1L);
		assertEquals(ShortUtils.parseShortObject("+32767"), (short) 32767);

		assertEquals(ShortUtils.parseShortObject("-32769", (short) 0), (short) 0);
		assertEquals(ShortUtils.parseShortObject("32768", (short) 0), (short) 0);
		assertEquals(ShortUtils.parseShortObject(" 1", (short) 0), (short) 0);
		assertEquals(ShortUtils.parseShortObject("A", (short) 0), (short) 0);
		assertEquals(ShortUtils.parseShortObject("1A", (short) 0), (short) 0);
		assertEquals(ShortUtils.parseShortObject("A1", (short) 0), (short) 0);

		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject("-32769"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject("32768"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ShortUtils.parseShortObject("A1"));
	}

	@Test
	@DisplayName("Parse unsigned byte")
	public void testParseUnsignedByte() {
		assertEquals(0x00L, ShortUtils.parseUnsignedByte((byte) 0x00));
		assertEquals(0x7FL, ShortUtils.parseUnsignedByte((byte) 0x7F));
		assertEquals(0x80L, ShortUtils.parseUnsignedByte((byte) 0x80));
		assertEquals(0xFFL, ShortUtils.parseUnsignedByte((byte) 0xFF));
	}

	@Test
	@DisplayName("Cap range short value")
	public void testCap() {
		assertEquals(5, ShortUtils.cap((short) 4, (short) 5, (short) 10));
		assertEquals(5, ShortUtils.cap((short) 5, (short) 5, (short) 10));
		assertEquals(6, ShortUtils.cap((short) 6, (short) 5, (short) 10));
		assertEquals(9, ShortUtils.cap((short) 9, (short) 5, (short) 10));
		assertEquals(10, ShortUtils.cap((short) 10, (short) 5, (short) 10));
		assertEquals(10, ShortUtils.cap((short) 11, (short) 5, (short) 10));

		assertEquals(-10, ShortUtils.cap((short) -11, (short) -10, (short) -5));
		assertEquals(-10, ShortUtils.cap((short) -10, (short) -10, (short) -5));
		assertEquals(-9, ShortUtils.cap((short) -9, (short) -10, (short) -5));
		assertEquals(-6, ShortUtils.cap((short) -6, (short) -10, (short) -5));
		assertEquals(-5, ShortUtils.cap((short) -5, (short) -10, (short) -5));
		assertEquals(-5, ShortUtils.cap((short) -4, (short) -10, (short) -5));
	}

	@Test
	@DisplayName("Cap min short value")
	public void testCapMin() {
		assertEquals(5, ShortUtils.capMin((short) 4, (short) 5));
		assertEquals(5, ShortUtils.capMin((short) 5, (short) 5));
		assertEquals(6, ShortUtils.capMin((short) 6, (short) 5));

		assertEquals(-4, ShortUtils.capMin((short) -4, (short) -5));
		assertEquals(-5, ShortUtils.capMin((short) -5, (short) -5));
		assertEquals(-5, ShortUtils.capMin((short) -6, (short) -5));
	}

	@Test
	@DisplayName("Cap max short value")
	public void testCapMax() {
		assertEquals(5, ShortUtils.capMin((short) 4, (short) 5));
		assertEquals(5, ShortUtils.capMin((short) 5, (short) 5));
		assertEquals(6, ShortUtils.capMin((short) 6, (short) 5));

		assertEquals(-4, ShortUtils.capMin((short) -4, (short) -5));
		assertEquals(-5, ShortUtils.capMin((short) -5, (short) -5));
		assertEquals(-5, ShortUtils.capMin((short) -6, (short) -5));
	}

	@Test
	@DisplayName("Has min short value")
	public void testHasMin() {
		assertFalse(ShortUtils.hasMin((short) 4, (short) 5));
		assertTrue(ShortUtils.hasMin((short) 5, (short) 5));
		assertTrue(ShortUtils.hasMin((short) 6, (short) 5));

		assertTrue(ShortUtils.hasMin((short) -4, (short) -5));
		assertTrue(ShortUtils.hasMin((short) -5, (short) -5));
		assertFalse(ShortUtils.hasMin((short) -6, (short) -5));
	}

	@Test
	@DisplayName("Has max short value")
	public void testHasMax() {
		assertTrue(ShortUtils.hasMax((short) 4, (short) 5));
		assertTrue(ShortUtils.hasMax((short) 5, (short) 5));
		assertFalse(ShortUtils.hasMax((short) 6, (short) 5));

		assertFalse(ShortUtils.hasMax((short) -4, (short) -5));
		assertTrue(ShortUtils.hasMax((short) -5, (short) -5));
		assertTrue(ShortUtils.hasMax((short) -6, (short) -5));
	}

	@Test
	@DisplayName("Has range short value")
	public void testHasBetween() {
		assertFalse(ShortUtils.hasBetween((short) 4, (short) 5, (short) 10));
		assertTrue(ShortUtils.hasBetween((short) 5, (short) 5, (short) 10));
		assertTrue(ShortUtils.hasBetween((short) 6, (short) 5, (short) 10));
		assertTrue(ShortUtils.hasBetween((short) 9, (short) 5, (short) 10));
		assertTrue(ShortUtils.hasBetween((short) 10, (short) 5, (short) 10));
		assertFalse(ShortUtils.hasBetween((short) 11, (short) 5, (short) 10));

		assertFalse(ShortUtils.hasBetween((short) -11, (short) -10, (short) -5));
		assertTrue(ShortUtils.hasBetween((short) -10, (short) -10, (short) -5));
		assertTrue(ShortUtils.hasBetween((short) -9, (short) -10, (short) -5));
		assertTrue(ShortUtils.hasBetween((short) -6, (short) -10, (short) -5));
		assertTrue(ShortUtils.hasBetween((short) -5, (short) -10, (short) -5));
		assertFalse(ShortUtils.hasBetween((short) -4, (short) -10, (short) -5));
	}
}
