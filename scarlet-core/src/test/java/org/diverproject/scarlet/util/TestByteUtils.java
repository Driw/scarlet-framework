package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Byte Utils")
class TestByteUtils {

	@Test
	@DisplayName("String is byte number")
	public void testIsByte() {
		assertFalse(ByteUtils.isByte("-129"));
		assertTrue(ByteUtils.isByte("-128"));
		assertTrue(ByteUtils.isByte("-127"));

		assertTrue(ByteUtils.isByte("126"));
		assertTrue(ByteUtils.isByte("127"));
		assertFalse(ByteUtils.isByte("128"));

		assertFalse(ByteUtils.isByte("A"));
		assertFalse(ByteUtils.isByte("A1"));
		assertFalse(ByteUtils.isByte("1A"));
		assertFalse(ByteUtils.isByte("1."));
		assertFalse(ByteUtils.isByte("1.0"));
		assertFalse(ByteUtils.isByte(" 1"));
		assertFalse(ByteUtils.isByte("1 "));
	}

	@Test
	@DisplayName("String is all byte numbers")
	public void testIsAllByte() {
		assertTrue(ByteUtils.isAllByte(new String[]{"-128", "-127", "0", "126", "+127"}));

		assertFalse(ByteUtils.isAllByte(new String[]{"0", "-129"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "128"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "A"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "A1"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "1A"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "1."}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "1.0"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", " 1"}));
		assertFalse(ByteUtils.isAllByte(new String[]{"0", "1 "}));
	}

	@Test
	@DisplayName("Parse string to byte")
	public void testParseByte() {
		assertEquals(-128, ByteUtils.parseByte("-128"));
		assertEquals(-1L, ByteUtils.parseByte("-1"));
		assertEquals(0L, ByteUtils.parseByte("0"));
		assertEquals(1L, ByteUtils.parseByte("1"));
		assertEquals(127, ByteUtils.parseByte("+127"));

		assertEquals(ByteUtils.parseByte("-129", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByte("128", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByte(" 1", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByte("A", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByte("1A", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByte("A1", (byte) 0), (byte) 0);

		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte(null));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte(""));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte("-129"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte("128"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByte("A1"));
	}

	@Test
	@DisplayName("Parse string to byte object")
	public void testParseByteObject() {
		assertNull(ByteUtils.parseByteObject(null));
		assertNull(ByteUtils.parseByteObject(""));
		assertEquals(ByteUtils.parseByteObject("-128"), (byte) -128);
		assertEquals(ByteUtils.parseByteObject("-1"), (byte) -1L);
		assertEquals(ByteUtils.parseByteObject("0"), (byte) 0L);
		assertEquals(ByteUtils.parseByteObject("1"), (byte) 1L);
		assertEquals(ByteUtils.parseByteObject("+127"), (byte) 127);

		assertEquals(ByteUtils.parseByteObject("-129", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByteObject("128", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByteObject(" 1", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByteObject("A", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByteObject("1A", (byte) 0), (byte) 0);
		assertEquals(ByteUtils.parseByteObject("A1", (byte) 0), (byte) 0);

		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject("-129"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject("128"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject(" 1"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject("A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject("1A"));
		assertThrows(NumberUtilsRuntimeException.class, () -> ByteUtils.parseByteObject("A1"));
	}

	@Test
	@DisplayName("Cap range byte value")
	public void testCap() {
		assertEquals(5, ByteUtils.cap((byte) 4, (byte) 5, (byte) 10));
		assertEquals(5, ByteUtils.cap((byte) 5, (byte) 5, (byte) 10));
		assertEquals(6, ByteUtils.cap((byte) 6, (byte) 5, (byte) 10));
		assertEquals(9, ByteUtils.cap((byte) 9, (byte) 5, (byte) 10));
		assertEquals(10, ByteUtils.cap((byte) 10, (byte) 5, (byte) 10));
		assertEquals(10, ByteUtils.cap((byte) 11, (byte) 5, (byte) 10));

		assertEquals(-10, ByteUtils.cap((byte) -11, (byte) -10, (byte) -5));
		assertEquals(-10, ByteUtils.cap((byte) -10, (byte) -10, (byte) -5));
		assertEquals(-9, ByteUtils.cap((byte) -9, (byte) -10, (byte) -5));
		assertEquals(-6, ByteUtils.cap((byte) -6, (byte) -10, (byte) -5));
		assertEquals(-5, ByteUtils.cap((byte) -5, (byte) -10, (byte) -5));
		assertEquals(-5, ByteUtils.cap((byte) -4, (byte) -10, (byte) -5));
	}

	@Test
	@DisplayName("Cap min byte value")
	public void testCapMin() {
		assertEquals(5, ByteUtils.capMin((byte) 4, (byte) 5));
		assertEquals(5, ByteUtils.capMin((byte) 5, (byte) 5));
		assertEquals(6, ByteUtils.capMin((byte) 6, (byte) 5));

		assertEquals(-4, ByteUtils.capMin((byte) -4, (byte) -5));
		assertEquals(-5, ByteUtils.capMin((byte) -5, (byte) -5));
		assertEquals(-5, ByteUtils.capMin((byte) -6, (byte) -5));
	}

	@Test
	@DisplayName("Cap max byte value")
	public void testCapMax() {
		assertEquals(5, ByteUtils.capMin((byte) 4, (byte) 5));
		assertEquals(5, ByteUtils.capMin((byte) 5, (byte) 5));
		assertEquals(6, ByteUtils.capMin((byte) 6, (byte) 5));

		assertEquals(-4, ByteUtils.capMin((byte) -4, (byte) -5));
		assertEquals(-5, ByteUtils.capMin((byte) -5, (byte) -5));
		assertEquals(-5, ByteUtils.capMin((byte) -6, (byte) -5));
	}

	@Test
	@DisplayName("Has min byte value")
	public void testHasMin() {
		assertFalse(ByteUtils.hasMin((byte) 4, (byte) 5));
		assertTrue(ByteUtils.hasMin((byte) 5, (byte) 5));
		assertTrue(ByteUtils.hasMin((byte) 6, (byte) 5));

		assertTrue(ByteUtils.hasMin((byte) -4, (byte) -5));
		assertTrue(ByteUtils.hasMin((byte) -5, (byte) -5));
		assertFalse(ByteUtils.hasMin((byte) -6, (byte) -5));
	}

	@Test
	@DisplayName("Has max byte value")
	public void testHasMax() {
		assertTrue(ByteUtils.hasMax((byte) 4, (byte) 5));
		assertTrue(ByteUtils.hasMax((byte) 5, (byte) 5));
		assertFalse(ByteUtils.hasMax((byte) 6, (byte) 5));

		assertFalse(ByteUtils.hasMax((byte) -4, (byte) -5));
		assertTrue(ByteUtils.hasMax((byte) -5, (byte) -5));
		assertTrue(ByteUtils.hasMax((byte) -6, (byte) -5));
	}

	@Test
	@DisplayName("Has range byte value")
	public void testHasBetween() {
		assertFalse(ByteUtils.hasBetween((byte) 4, (byte) 5, (byte) 10));
		assertTrue(ByteUtils.hasBetween((byte) 5, (byte) 5, (byte) 10));
		assertTrue(ByteUtils.hasBetween((byte) 6, (byte) 5, (byte) 10));
		assertTrue(ByteUtils.hasBetween((byte) 9, (byte) 5, (byte) 10));
		assertTrue(ByteUtils.hasBetween((byte) 10, (byte) 5, (byte) 10));
		assertFalse(ByteUtils.hasBetween((byte) 11, (byte) 5, (byte) 10));

		assertFalse(ByteUtils.hasBetween((byte) -11, (byte) -10, (byte) -5));
		assertTrue(ByteUtils.hasBetween((byte) -10, (byte) -10, (byte) -5));
		assertTrue(ByteUtils.hasBetween((byte) -9, (byte) -10, (byte) -5));
		assertTrue(ByteUtils.hasBetween((byte) -6, (byte) -10, (byte) -5));
		assertTrue(ByteUtils.hasBetween((byte) -5, (byte) -10, (byte) -5));
		assertFalse(ByteUtils.hasBetween((byte) -4, (byte) -10, (byte) -5));
	}
}
