package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diverproject.scarlet.util.exceptions.BinaryUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Binary Utils")
public class TestBinaryUtils {

	@Test
	@DisplayName("Calculate shift bitwise")
	public void testShift() {
		assertEquals(24, BinaryUtils.shift(false, 0, 4, 4));
		assertEquals(16, BinaryUtils.shift(false, 1, 4, 4));
		assertEquals(8, BinaryUtils.shift(false, 2, 4, 4));
		assertEquals(0, BinaryUtils.shift(false, 3, 4, 4));
		assertEquals(16, BinaryUtils.shift(false, 0, 4, 3));
		assertEquals(8, BinaryUtils.shift(false, 1, 4, 3));
		assertEquals(0, BinaryUtils.shift(false, 2, 4, 3));
		assertEquals(8, BinaryUtils.shift(false, 0, 4, 2));
		assertEquals(0, BinaryUtils.shift(false, 1, 4, 2));
		assertEquals(0, BinaryUtils.shift(false, 0, 4, 1));

		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 3, 4, 3));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 2, 4, 2));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 3, 4, 2));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 1, 4, 1));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 2, 4, 1));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.shift(false, 3, 4, 1));
	}

	@Test
	@DisplayName("New short from bytes")
	public void testNewShort() {
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(BinaryUtils.newShort(zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(one), (short) 0x0001);
		assertEquals(BinaryUtils.newShort(max), (short) 0x00FF);
		assertEquals(BinaryUtils.newShort(false, zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(false, one), (short) 0x0001);
		assertEquals(BinaryUtils.newShort(false, max), (short) 0x00FF);
		assertEquals(BinaryUtils.newShort(true, zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(true, one), (short) 0x0100);
		assertEquals(BinaryUtils.newShort(true, max), (short) 0xFF00);

		assertEquals(BinaryUtils.newShort(zero, zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(zero, one), (short) 0x0001);
		assertEquals(BinaryUtils.newShort(zero, max), (short) 0x00FF);
		assertEquals(BinaryUtils.newShort(one, zero), (short) 0x0100);
		assertEquals(BinaryUtils.newShort(one, one), (short) 0x0101);
		assertEquals(BinaryUtils.newShort(one, max), (short) 0x01FF);
		assertEquals(BinaryUtils.newShort(max, zero), (short) 0xFF00);
		assertEquals(BinaryUtils.newShort(max, one), (short) 0xFF01);
		assertEquals(BinaryUtils.newShort(max, max), (short) 0xFFFF);

		assertEquals(BinaryUtils.newShort(false, zero, zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(false, zero, one), (short) 0x0001);
		assertEquals(BinaryUtils.newShort(false, zero, max), (short) 0x00FF);
		assertEquals(BinaryUtils.newShort(false, one, zero), (short) 0x0100);
		assertEquals(BinaryUtils.newShort(false, one, one), (short) 0x0101);
		assertEquals(BinaryUtils.newShort(false, one, max), (short) 0x01FF);
		assertEquals(BinaryUtils.newShort(false, max, zero), (short) 0xFF00);
		assertEquals(BinaryUtils.newShort(false, max, one), (short) 0xFF01);
		assertEquals(BinaryUtils.newShort(false, max, max), (short) 0xFFFF);

		assertEquals(BinaryUtils.newShort(true, zero, zero), (short) 0x0000);
		assertEquals(BinaryUtils.newShort(true, zero, one), (short) 0x0100);
		assertEquals(BinaryUtils.newShort(true, zero, max), (short) 0xFF00);
		assertEquals(BinaryUtils.newShort(true, one, zero), (short) 0x0001);
		assertEquals(BinaryUtils.newShort(true, one, one), (short) 0x0101);
		assertEquals(BinaryUtils.newShort(true, one, max), (short) 0xFF01);
		assertEquals(BinaryUtils.newShort(true, max, zero), (short) 0x00FF);
		assertEquals(BinaryUtils.newShort(true, max, one), (short) 0x01FF);
		assertEquals(BinaryUtils.newShort(true, max, max), (short) 0xFFFF);

		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.newShort(false, (byte[]) null));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.newShort(false, new byte[0]));
		assertThrows(BinaryUtilsRuntimeException.class, () -> BinaryUtils.newShort(false, new byte[3]));
	}

	@Test
	@DisplayName("New int from bytes")
	public void testNewInt() {
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(0x00000000, BinaryUtils.newInt(zero, zero));
		assertEquals(0x00000001, BinaryUtils.newInt(zero, one));
		assertEquals(0x000000FF, BinaryUtils.newInt(zero, max));
		assertEquals(0x00000100, BinaryUtils.newInt(one, zero));
		assertEquals(0x00000101, BinaryUtils.newInt(one, one));
		assertEquals(0x000001FF, BinaryUtils.newInt(one, max));
		assertEquals(0x0000FF00, BinaryUtils.newInt(max, zero));
		assertEquals(0x0000FF01, BinaryUtils.newInt(max, one));
		assertEquals(0x0000FFFF, BinaryUtils.newInt(max, max));

		assertEquals(0x00000000, BinaryUtils.newInt(false, zero, zero));
		assertEquals(0x00000001, BinaryUtils.newInt(false, zero, one));
		assertEquals(0x000000FF, BinaryUtils.newInt(false, zero, max));
		assertEquals(0x00000100, BinaryUtils.newInt(false, one, zero));
		assertEquals(0x00000101, BinaryUtils.newInt(false, one, one));
		assertEquals(0x000001FF, BinaryUtils.newInt(false, one, max));
		assertEquals(0x0000FF00, BinaryUtils.newInt(false, max, zero));
		assertEquals(0x0000FF01, BinaryUtils.newInt(false, max, one));
		assertEquals(0x0000FFFF, BinaryUtils.newInt(false, max, max));

		assertEquals(0x00000000, BinaryUtils.newInt(true, zero, zero));
		assertEquals(0x01000000, BinaryUtils.newInt(true, zero, one));
		assertEquals(0xFF000000, BinaryUtils.newInt(true, zero, max));
		assertEquals(0x00010000, BinaryUtils.newInt(true, one, zero));
		assertEquals(0x01010000, BinaryUtils.newInt(true, one, one));
		assertEquals(0xFF010000, BinaryUtils.newInt(true, one, max));
		assertEquals(0x00FF0000, BinaryUtils.newInt(true, max, zero));
		assertEquals(0x01FF0000, BinaryUtils.newInt(true, max, one));
		assertEquals(0xFFFF0000, BinaryUtils.newInt(true, max, max));
	}

	@Test
	@DisplayName("New long from bytes")
	public void testNewLong() {
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(0x0000000000000000L, BinaryUtils.newLong(zero, zero));
		assertEquals(0x0000000000000001L, BinaryUtils.newLong(zero, one));
		assertEquals(0x00000000000000FFL, BinaryUtils.newLong(zero, max));
		assertEquals(0x0000000000000100L, BinaryUtils.newLong(one, zero));
		assertEquals(0x0000000000000101L, BinaryUtils.newLong(one, one));
		assertEquals(0x00000000000001FFL, BinaryUtils.newLong(one, max));
		assertEquals(0x000000000000FF00L, BinaryUtils.newLong(max, zero));
		assertEquals(0x000000000000FF01L, BinaryUtils.newLong(max, one));
		assertEquals(0x000000000000FFFFL, BinaryUtils.newLong(max, max));

		assertEquals(0x0000000000000000L, BinaryUtils.newLong(false, zero, zero));
		assertEquals(0x0000000000000001L, BinaryUtils.newLong(false, zero, one));
		assertEquals(0x00000000000000FFL, BinaryUtils.newLong(false, zero, max));
		assertEquals(0x0000000000000100L, BinaryUtils.newLong(false, one, zero));
		assertEquals(0x0000000000000101L, BinaryUtils.newLong(false, one, one));
		assertEquals(0x00000000000001FFL, BinaryUtils.newLong(false, one, max));
		assertEquals(0x000000000000FF00L, BinaryUtils.newLong(false, max, zero));
		assertEquals(0x000000000000FF01L, BinaryUtils.newLong(false, max, one));
		assertEquals(0x000000000000FFFFL, BinaryUtils.newLong(false, max, max));

		assertEquals(0x0000000000000000L, BinaryUtils.newLong(true, zero, zero));
		assertEquals(0x0100000000000000L, BinaryUtils.newLong(true, zero, one));
		assertEquals(0xFF00000000000000L, BinaryUtils.newLong(true, zero, max));
		assertEquals(0x0001000000000000L, BinaryUtils.newLong(true, one, zero));
		assertEquals(0x0101000000000000L, BinaryUtils.newLong(true, one, one));
		assertEquals(0xFF01000000000000L, BinaryUtils.newLong(true, one, max));
		assertEquals(0x00FF000000000000L, BinaryUtils.newLong(true, max, zero));
		assertEquals(0x01FF000000000000L, BinaryUtils.newLong(true, max, one));
		assertEquals(0xFFFF000000000000L, BinaryUtils.newLong(true, max, max));
	}
}
