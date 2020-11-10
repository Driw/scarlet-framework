package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.HexadecimalUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hexadecimal Utils")
public class HexadecimalUtilsTest {

	@Test
	@DisplayName("Has hexadecimal format")
	public void testHasHexFormat() {
		assertFalse(HexadecimalUtils.hasHexFormat(null));
		assertFalse(HexadecimalUtils.hasHexFormat(""));
		assertTrue(HexadecimalUtils.hasHexFormat("0x0"));
		assertTrue(HexadecimalUtils.hasHexFormat("0"));
		assertTrue(HexadecimalUtils.hasHexFormat("1"));
		assertTrue(HexadecimalUtils.hasHexFormat("2"));
		assertTrue(HexadecimalUtils.hasHexFormat("3"));
		assertTrue(HexadecimalUtils.hasHexFormat("4"));
		assertTrue(HexadecimalUtils.hasHexFormat("5"));
		assertTrue(HexadecimalUtils.hasHexFormat("6"));
		assertTrue(HexadecimalUtils.hasHexFormat("7"));
		assertTrue(HexadecimalUtils.hasHexFormat("8"));
		assertTrue(HexadecimalUtils.hasHexFormat("9"));
		assertTrue(HexadecimalUtils.hasHexFormat("0"));
		assertTrue(HexadecimalUtils.hasHexFormat("A"));
		assertTrue(HexadecimalUtils.hasHexFormat("B"));
		assertTrue(HexadecimalUtils.hasHexFormat("C"));
		assertTrue(HexadecimalUtils.hasHexFormat("D"));
		assertTrue(HexadecimalUtils.hasHexFormat("E"));
		assertTrue(HexadecimalUtils.hasHexFormat("F"));
		assertTrue(HexadecimalUtils.hasHexFormat("a"));
		assertTrue(HexadecimalUtils.hasHexFormat("b"));
		assertTrue(HexadecimalUtils.hasHexFormat("c"));
		assertTrue(HexadecimalUtils.hasHexFormat("d"));
		assertTrue(HexadecimalUtils.hasHexFormat("e"));
		assertTrue(HexadecimalUtils.hasHexFormat("f"));

		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('0' - 1))));
		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('9' + 1))));
		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('A' - 1))));
		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('F' + 1))));
		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('a' - 1))));
		assertFalse(HexadecimalUtils.hasHexFormat(Character.toString((char) ('f' + 1))));
	}

	@Test
	@DisplayName("Is a hexadecimal valid value")
	public void testIsHex() {
		assertFalse(HexadecimalUtils.isHex(null, 1));
		assertFalse(HexadecimalUtils.isHex("", 1));
		assertTrue(HexadecimalUtils.isHex("0", 1));
		assertTrue(HexadecimalUtils.isHex("00", 1));
		assertTrue(HexadecimalUtils.isHex("000", 1));
		assertTrue(HexadecimalUtils.isHex("F", 1));
		assertTrue(HexadecimalUtils.isHex("FF", 1));
		assertTrue(HexadecimalUtils.isHex("0FF", 1));
		assertFalse(HexadecimalUtils.isHex("1FF", 1));
		assertFalse(HexadecimalUtils.isHex("FFF", 1));
		assertTrue(HexadecimalUtils.isHex("0x0", 1));
		assertTrue(HexadecimalUtils.isHex("0x00", 1));
		assertTrue(HexadecimalUtils.isHex("0x000", 1));
		assertTrue(HexadecimalUtils.isHex("0xF", 1));
		assertTrue(HexadecimalUtils.isHex("0xFF", 1));
		assertTrue(HexadecimalUtils.isHex("0x0FF", 1));
		assertFalse(HexadecimalUtils.isHex("0x1FF", 1));
		assertFalse(HexadecimalUtils.isHex("0xFFF", 1));

		assertTrue(HexadecimalUtils.isHex("0", 2));
		assertTrue(HexadecimalUtils.isHex("00", 2));
		assertTrue(HexadecimalUtils.isHex("000", 2));
		assertTrue(HexadecimalUtils.isHex("0000", 2));
		assertTrue(HexadecimalUtils.isHex("00000", 2));
		assertTrue(HexadecimalUtils.isHex("F", 2));
		assertTrue(HexadecimalUtils.isHex("FF", 2));
		assertTrue(HexadecimalUtils.isHex("FFF", 2));
		assertTrue(HexadecimalUtils.isHex("FFFF", 2));
		assertTrue(HexadecimalUtils.isHex("0FFFF", 2));
		assertFalse(HexadecimalUtils.isHex("1FFFF", 2));
		assertTrue(HexadecimalUtils.isHex("0x0", 2));
		assertTrue(HexadecimalUtils.isHex("0x00", 2));
		assertTrue(HexadecimalUtils.isHex("0x000", 2));
		assertTrue(HexadecimalUtils.isHex("0x0000", 2));
		assertTrue(HexadecimalUtils.isHex("0x00000", 2));
		assertTrue(HexadecimalUtils.isHex("0xF", 2));
		assertTrue(HexadecimalUtils.isHex("0xFF", 2));
		assertTrue(HexadecimalUtils.isHex("0xFFF", 2));
		assertTrue(HexadecimalUtils.isHex("0xFFFF", 2));
		assertTrue(HexadecimalUtils.isHex("0x0FFFF", 2));
		assertFalse(HexadecimalUtils.isHex("0x1FFFF", 2));
	}

	@Test
	@DisplayName("Is a hexadecimal valid byte value")
	public void testIsHexByte() {
		assertTrue(HexadecimalUtils.isHexByte("0"));
		assertTrue(HexadecimalUtils.isHexByte("00"));
		assertTrue(HexadecimalUtils.isHexByte("000"));
		assertTrue(HexadecimalUtils.isHexByte("F"));
		assertTrue(HexadecimalUtils.isHexByte("FF"));
		assertTrue(HexadecimalUtils.isHexByte("0FF"));
		assertFalse(HexadecimalUtils.isHexByte("1FF"));
		assertFalse(HexadecimalUtils.isHexByte("FFF"));
		assertTrue(HexadecimalUtils.isHexByte("0x0"));
		assertTrue(HexadecimalUtils.isHexByte("0x00"));
		assertTrue(HexadecimalUtils.isHexByte("0x000"));
		assertTrue(HexadecimalUtils.isHexByte("0xF"));
		assertTrue(HexadecimalUtils.isHexByte("0xFF"));
		assertTrue(HexadecimalUtils.isHexByte("0x0FF"));
		assertFalse(HexadecimalUtils.isHexByte("0x1FF"));
		assertFalse(HexadecimalUtils.isHexByte("0xFFF"));
	}

	@Test
	@DisplayName("Is a hexadecimal valid short value")
	public void testIsHexShort() {
		assertTrue(HexadecimalUtils.isHexShort("0"));
		assertTrue(HexadecimalUtils.isHexShort("0000"));
		assertTrue(HexadecimalUtils.isHexShort("00000"));
		assertTrue(HexadecimalUtils.isHexShort("F"));
		assertTrue(HexadecimalUtils.isHexShort("FFFF"));
		assertTrue(HexadecimalUtils.isHexShort("0FFFF"));
		assertFalse(HexadecimalUtils.isHexShort("1FFFF"));
		assertFalse(HexadecimalUtils.isHexShort("FFFFF"));
		assertTrue(HexadecimalUtils.isHexShort("0x000"));
		assertTrue(HexadecimalUtils.isHexShort("0x0000"));
		assertTrue(HexadecimalUtils.isHexShort("0x00000"));
		assertTrue(HexadecimalUtils.isHexShort("0xFFF"));
		assertTrue(HexadecimalUtils.isHexShort("0xFFFF"));
		assertTrue(HexadecimalUtils.isHexShort("0x0FFFF"));
		assertFalse(HexadecimalUtils.isHexShort("0x1FFFF"));
		assertFalse(HexadecimalUtils.isHexShort("0xFFFFF"));
	}

	@Test
	@DisplayName("Is a hexadecimal valid int value")
	public void testIsHexInt() {
		assertTrue(HexadecimalUtils.isHexInt("0"));
		assertTrue(HexadecimalUtils.isHexInt("00000000"));
		assertTrue(HexadecimalUtils.isHexInt("000000000"));
		assertTrue(HexadecimalUtils.isHexInt("FFFFF"));
		assertTrue(HexadecimalUtils.isHexInt("FFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexInt("0FFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexInt("1FFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexInt("FFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexInt("0x0000000"));
		assertTrue(HexadecimalUtils.isHexInt("0x00000000"));
		assertTrue(HexadecimalUtils.isHexInt("0x000000000"));
		assertTrue(HexadecimalUtils.isHexInt("0xFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexInt("0xFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexInt("0x0FFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexInt("0x1FFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexInt("0xFFFFFFFFF"));
	}

	@Test
	@DisplayName("Is a hexadecimal valid long value")
	public void testIsHexLong() {
		assertTrue(HexadecimalUtils.isHexLong("0"));
		assertTrue(HexadecimalUtils.isHexLong("0000000000000000"));
		assertTrue(HexadecimalUtils.isHexLong("00000000000000000"));
		assertTrue(HexadecimalUtils.isHexLong("FFFFFFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexLong("FFFFFFFFFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexLong("0FFFFFFFFFFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexLong("1FFFFFFFFFFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexLong("FFFFFFFFFFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexLong("0x000000000000000"));
		assertTrue(HexadecimalUtils.isHexLong("0x0000000000000000"));
		assertTrue(HexadecimalUtils.isHexLong("0x00000000000000000"));
		assertTrue(HexadecimalUtils.isHexLong("0xFFFFFFFFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexLong("0xFFFFFFFFFFFFFFFF"));
		assertTrue(HexadecimalUtils.isHexLong("0x0FFFFFFFFFFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexLong("0x1FFFFFFFFFFFFFFFF"));
		assertFalse(HexadecimalUtils.isHexLong("0xFFFFFFFFFFFFFFFFF"));
	}

	@Test
	@DisplayName("Remove prefix '0x' if setted")
	public void testClearHex() {
		assertEquals("FF", HexadecimalUtils.clearHex("FF"));
		assertEquals("FF", HexadecimalUtils.clearHex("0xFF"));
	}

	@Test
	@DisplayName("Remove prefix '0x' if setted")
	public void testToHex() {
		assertEquals("0", HexadecimalUtils.toHex((byte) 0));
		assertEquals("7F", HexadecimalUtils.toHex(Byte.MAX_VALUE));
		assertEquals("0", HexadecimalUtils.toHex((short) 0));
		assertEquals("7FFF", HexadecimalUtils.toHex(Short.MAX_VALUE));
		assertEquals("0", HexadecimalUtils.toHex(0));
		assertEquals("7FFFFFFF", HexadecimalUtils.toHex(Integer.MAX_VALUE));
		assertEquals("0", HexadecimalUtils.toHex(0L));
		assertEquals("7FFFFFFFFFFFFFFF", HexadecimalUtils.toHex(Long.MAX_VALUE));
		assertEquals("0", HexadecimalUtils.toHex((char) 0));
		assertEquals("FFFF", HexadecimalUtils.toHex((char) -1));
		assertEquals("FFFF", HexadecimalUtils.toHex(Character.MAX_VALUE));

		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.toHex((byte) -1));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.toHex((short) -1));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.toHex(-1));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.toHex(-1L));

		int formatter1 = HexadecimalUtils.TO_HEX_ZEROS;
		int formatter2 = HexadecimalUtils.TO_HEX_X;
		int formatter3 = HexadecimalUtils.TO_HEX_X | HexadecimalUtils.TO_HEX_ZEROS;

		assertEquals("00", HexadecimalUtils.toHex((byte) 0, formatter1));
		assertEquals("0x0", HexadecimalUtils.toHex((byte) 0, formatter2));
		assertEquals("0x00", HexadecimalUtils.toHex((byte) 0, formatter3));
		assertEquals("7F", HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter1));
		assertEquals("0x7F", HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter2));
		assertEquals("0x7F", HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter3));
		assertEquals("0000", HexadecimalUtils.toHex((short) 0, formatter1));
		assertEquals("0x0", HexadecimalUtils.toHex((short) 0, formatter2));
		assertEquals("0x0000", HexadecimalUtils.toHex((short) 0, formatter3));
		assertEquals("7FFF", HexadecimalUtils.toHex(Short.MAX_VALUE, formatter1));
		assertEquals("0x7FFF", HexadecimalUtils.toHex(Short.MAX_VALUE, formatter2));
		assertEquals("0x7FFF", HexadecimalUtils.toHex(Short.MAX_VALUE, formatter3));
		assertEquals("00000000", HexadecimalUtils.toHex(0, formatter1));
		assertEquals("0x0", HexadecimalUtils.toHex(0, formatter2));
		assertEquals("0x00000000", HexadecimalUtils.toHex(0, formatter3));
		assertEquals("7FFFFFFF", HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter1));
		assertEquals("0x7FFFFFFF", HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter2));
		assertEquals("0x7FFFFFFF", HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter3));
		assertEquals("0000000000000000", HexadecimalUtils.toHex(0L, formatter1));
		assertEquals("0x0", HexadecimalUtils.toHex(0L, formatter2));
		assertEquals("0x0000000000000000", HexadecimalUtils.toHex(0L, formatter3));
		assertEquals("7FFFFFFFFFFFFFFF", HexadecimalUtils.toHex(Long.MAX_VALUE, formatter1));
		assertEquals("0x7FFFFFFFFFFFFFFF", HexadecimalUtils.toHex(Long.MAX_VALUE, formatter2));
		assertEquals("0x7FFFFFFFFFFFFFFF", HexadecimalUtils.toHex(Long.MAX_VALUE, formatter3));
	}

	@Test
	@DisplayName("Remove prefix '0x' if setted")
	public void test() {
		assertEquals((byte) 0, HexadecimalUtils.parseHexByte("0"));
		assertEquals((byte) 0, HexadecimalUtils.parseHexByte("0x0"));
		assertEquals(Byte.MAX_VALUE, HexadecimalUtils.parseHexByte("7F"));
		assertEquals(Byte.MAX_VALUE, HexadecimalUtils.parseHexByte("0x7F"));
		assertEquals((short) 0, HexadecimalUtils.parseHexShort("0"));
		assertEquals((short) 0, HexadecimalUtils.parseHexShort("0x0"));
		assertEquals(Short.MAX_VALUE, HexadecimalUtils.parseHexShort("7FFF"));
		assertEquals(Short.MAX_VALUE, HexadecimalUtils.parseHexShort("0x7FFF"));
		assertEquals(0, HexadecimalUtils.parseHexInt("0"));
		assertEquals(0, HexadecimalUtils.parseHexInt("0x0"));
		assertEquals(Integer.MAX_VALUE, HexadecimalUtils.parseHexInt("7FFFFFFF"));
		assertEquals(Integer.MAX_VALUE, HexadecimalUtils.parseHexInt("0x7FFFFFFF"));
		assertEquals(0L, HexadecimalUtils.parseHexLong("0"));
		assertEquals(0L, HexadecimalUtils.parseHexLong("0x0"));
		assertEquals(Long.MAX_VALUE, HexadecimalUtils.parseHexLong("7FFFFFFFFFFFFFFF"));
		assertEquals(Long.MAX_VALUE, HexadecimalUtils.parseHexLong("0x7FFFFFFFFFFFFFFF"));

		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexByte(null));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexByte(""));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexByte("80"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexByte("0x80"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexShort(null));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexShort(""));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexShort("8000"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexShort("0x8000"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexInt(null));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexInt(""));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexInt("80000000"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexInt("0x80000000"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexLong(null));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexLong(""));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexLong("8000000000000000"));
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> HexadecimalUtils.parseHexLong("0x8000000000000000"));
	}
}
