package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.HexadecimalUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hexadecimal Utils")
public class TestHexadecimalUtils {

	@Test
	@DisplayName("Has hexadecimal format")
	public void testHasHexFormat() {
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
		assertEquals(HexadecimalUtils.clearHex("FF"), "FF");
		assertEquals(HexadecimalUtils.clearHex("0xFF"), "FF");
	}

	@Test
	@DisplayName("Remove prefix '0x' if setted")
	public void testToHex() {
		assertEquals(HexadecimalUtils.toHex((byte) 0), "0");
		assertEquals(HexadecimalUtils.toHex(Byte.MAX_VALUE), "7F");
		assertEquals(HexadecimalUtils.toHex((short) 0), "0");
		assertEquals(HexadecimalUtils.toHex(Short.MAX_VALUE), "7FFF");
		assertEquals(HexadecimalUtils.toHex(0), "0");
		assertEquals(HexadecimalUtils.toHex(Integer.MAX_VALUE), "7FFFFFFF");
		assertEquals(HexadecimalUtils.toHex(0L), "0");
		assertEquals(HexadecimalUtils.toHex(Long.MAX_VALUE), "7FFFFFFFFFFFFFFF");
		assertEquals(HexadecimalUtils.toHex((char) 0), "0");
		assertEquals(HexadecimalUtils.toHex((char) -1), "FFFF");
		assertEquals(HexadecimalUtils.toHex(Character.MAX_VALUE), "FFFF");

		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			assertEquals(HexadecimalUtils.toHex((byte) -1), "FF");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			assertEquals(HexadecimalUtils.toHex((short) -1), "FFFF");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			assertEquals(HexadecimalUtils.toHex(-1), "FFFFFFFF");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			assertEquals(HexadecimalUtils.toHex(-1L), "FFFFFFFFFFFFFFFF");
		});

		int formatter1 = HexadecimalUtils.TO_HEX_ZEROS;
		int formatter2 = HexadecimalUtils.TO_HEX_X;
		int formatter3 = HexadecimalUtils.TO_HEX_X | HexadecimalUtils.TO_HEX_ZEROS;

		assertEquals(HexadecimalUtils.toHex((byte) 0, formatter1), "00");
		assertEquals(HexadecimalUtils.toHex((byte) 0, formatter2), "0x0");
		assertEquals(HexadecimalUtils.toHex((byte) 0, formatter3), "0x00");
		assertEquals(HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter1), "7F");
		assertEquals(HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter2), "0x7F");
		assertEquals(HexadecimalUtils.toHex(Byte.MAX_VALUE, formatter3), "0x7F");
		assertEquals(HexadecimalUtils.toHex((short) 0, formatter1), "0000");
		assertEquals(HexadecimalUtils.toHex((short) 0, formatter2), "0x0");
		assertEquals(HexadecimalUtils.toHex((short) 0, formatter3), "0x0000");
		assertEquals(HexadecimalUtils.toHex(Short.MAX_VALUE, formatter1), "7FFF");
		assertEquals(HexadecimalUtils.toHex(Short.MAX_VALUE, formatter2), "0x7FFF");
		assertEquals(HexadecimalUtils.toHex(Short.MAX_VALUE, formatter3), "0x7FFF");
		assertEquals(HexadecimalUtils.toHex(0, formatter1), "00000000");
		assertEquals(HexadecimalUtils.toHex(0, formatter2), "0x0");
		assertEquals(HexadecimalUtils.toHex(0, formatter3), "0x00000000");
		assertEquals(HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter1), "7FFFFFFF");
		assertEquals(HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter2), "0x7FFFFFFF");
		assertEquals(HexadecimalUtils.toHex(Integer.MAX_VALUE, formatter3), "0x7FFFFFFF");
		assertEquals(HexadecimalUtils.toHex(0L, formatter1), "0000000000000000");
		assertEquals(HexadecimalUtils.toHex(0L, formatter2), "0x0");
		assertEquals(HexadecimalUtils.toHex(0L, formatter3), "0x0000000000000000");
		assertEquals(HexadecimalUtils.toHex(Long.MAX_VALUE, formatter1), "7FFFFFFFFFFFFFFF");
		assertEquals(HexadecimalUtils.toHex(Long.MAX_VALUE, formatter2), "0x7FFFFFFFFFFFFFFF");
		assertEquals(HexadecimalUtils.toHex(Long.MAX_VALUE, formatter3), "0x7FFFFFFFFFFFFFFF");
	}

	@Test
	@DisplayName("Remove prefix '0x' if setted")
	public void test() {
		assertEquals(HexadecimalUtils.parseHexByte("0"), (byte) 0);
		assertEquals(HexadecimalUtils.parseHexByte("0x0"), (byte) 0);
		assertEquals(HexadecimalUtils.parseHexByte("7F"), Byte.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexByte("0x7F"), Byte.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexShort("0"), (short) 0);
		assertEquals(HexadecimalUtils.parseHexShort("0x0"), (short) 0);
		assertEquals(HexadecimalUtils.parseHexShort("7FFF"), Short.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexShort("0x7FFF"), Short.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexInt("0"), 0);
		assertEquals(HexadecimalUtils.parseHexInt("0x0"), 0);
		assertEquals(HexadecimalUtils.parseHexInt("7FFFFFFF"), Integer.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexInt("0x7FFFFFFF"), Integer.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexLong("0"), 0L);
		assertEquals(HexadecimalUtils.parseHexLong("0x0"), 0L);
		assertEquals(HexadecimalUtils.parseHexLong("7FFFFFFFFFFFFFFF"), Long.MAX_VALUE);
		assertEquals(HexadecimalUtils.parseHexLong("0x7FFFFFFFFFFFFFFF"), Long.MAX_VALUE);

		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexByte("80");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexByte("0x80");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexShort("8000");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexShort("0x8000");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexInt("80000000");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexInt("0x80000000");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexLong("8000000000000000");
		});
		assertThrows(HexadecimalUtilsRuntimeException.class, () -> {
			HexadecimalUtils.parseHexLong("0x8000000000000000");
		});
	}
}
