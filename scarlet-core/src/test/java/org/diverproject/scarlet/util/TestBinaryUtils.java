package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diverproject.scarlet.util.exceptions.BinaryUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Binary Utils")
public class TestBinaryUtils
{
	@Test
	@DisplayName("Calculate shift bitwise")
	public void testShift()
	{
		assertEquals(BinaryUtils.shift(false, 0, 4, 4), 24);
		assertEquals(BinaryUtils.shift(false, 1, 4, 4), 16);
		assertEquals(BinaryUtils.shift(false, 2, 4, 4), 8);
		assertEquals(BinaryUtils.shift(false, 3, 4, 4), 0);
		assertEquals(BinaryUtils.shift(false, 0, 4, 3), 16);
		assertEquals(BinaryUtils.shift(false, 1, 4, 3), 8);
		assertEquals(BinaryUtils.shift(false, 2, 4, 3), 0);
		assertEquals(BinaryUtils.shift(false, 0, 4, 2), 8);
		assertEquals(BinaryUtils.shift(false, 1, 4, 2), 0);
		assertEquals(BinaryUtils.shift(false, 0, 4, 1), 0);

		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 3, 4, 3); });
		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 2, 4, 2); });
		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 3, 4, 2); });
		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 1, 4, 1); });
		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 2, 4, 1); });
		assertThrows(BinaryUtilsRuntimeException.class, () -> { BinaryUtils.shift(false, 3, 4, 1); });
	}

	@Test
	@DisplayName("New short from bytes")
	public void testNewShort()
	{
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(BinaryUtils.newShort(zero),				(short) 0x0000);
		assertEquals(BinaryUtils.newShort(one),					(short) 0x0001);
		assertEquals(BinaryUtils.newShort(max),					(short) 0x00FF);
		assertEquals(BinaryUtils.newShort(false, zero),			(short) 0x0000);
		assertEquals(BinaryUtils.newShort(false, one),			(short) 0x0001);
		assertEquals(BinaryUtils.newShort(false, max),			(short) 0x00FF);
		assertEquals(BinaryUtils.newShort(true, zero),			(short) 0x0000);
		assertEquals(BinaryUtils.newShort(true, one),			(short) 0x0100);
		assertEquals(BinaryUtils.newShort(true, max),			(short) 0xFF00);

		assertEquals(BinaryUtils.newShort(zero, zero),			(short) 0x0000);
		assertEquals(BinaryUtils.newShort(zero, one),			(short) 0x0001);
		assertEquals(BinaryUtils.newShort(zero, max),			(short) 0x00FF);
		assertEquals(BinaryUtils.newShort(one, zero),			(short) 0x0100);
		assertEquals(BinaryUtils.newShort(one, one),			(short) 0x0101);
		assertEquals(BinaryUtils.newShort(one, max),			(short) 0x01FF);
		assertEquals(BinaryUtils.newShort(max, zero),			(short) 0xFF00);
		assertEquals(BinaryUtils.newShort(max, one),			(short) 0xFF01);
		assertEquals(BinaryUtils.newShort(max, max),			(short) 0xFFFF);

		assertEquals(BinaryUtils.newShort(false, zero, zero),	(short) 0x0000);
		assertEquals(BinaryUtils.newShort(false, zero, one),	(short) 0x0001);
		assertEquals(BinaryUtils.newShort(false, zero, max),	(short) 0x00FF);
		assertEquals(BinaryUtils.newShort(false, one, zero),	(short) 0x0100);
		assertEquals(BinaryUtils.newShort(false, one, one),		(short) 0x0101);
		assertEquals(BinaryUtils.newShort(false, one, max),		(short) 0x01FF);
		assertEquals(BinaryUtils.newShort(false, max, zero),	(short) 0xFF00);
		assertEquals(BinaryUtils.newShort(false, max, one),		(short) 0xFF01);
		assertEquals(BinaryUtils.newShort(false, max, max),		(short) 0xFFFF);

		assertEquals(BinaryUtils.newShort(true, zero, zero),	(short) 0x0000);
		assertEquals(BinaryUtils.newShort(true, zero, one),		(short) 0x0100);
		assertEquals(BinaryUtils.newShort(true, zero, max),		(short) 0xFF00);
		assertEquals(BinaryUtils.newShort(true, one, zero),		(short) 0x0001);
		assertEquals(BinaryUtils.newShort(true, one, one),		(short) 0x0101);
		assertEquals(BinaryUtils.newShort(true, one, max),		(short) 0xFF01);
		assertEquals(BinaryUtils.newShort(true, max, zero),		(short) 0x00FF);
		assertEquals(BinaryUtils.newShort(true, max, one),		(short) 0x01FF);
		assertEquals(BinaryUtils.newShort(true, max, max),		(short) 0xFFFF);
	}

	@Test
	@DisplayName("New int from bytes")
	public void testNewInt()
	{
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(BinaryUtils.newInt(zero, zero),		0x00000000);
 		assertEquals(BinaryUtils.newInt(zero, one),			0x00000001);
		assertEquals(BinaryUtils.newInt(zero, max),			0x000000FF);
		assertEquals(BinaryUtils.newInt(one, zero),			0x00000100);
		assertEquals(BinaryUtils.newInt(one, one),			0x00000101);
		assertEquals(BinaryUtils.newInt(one, max),			0x000001FF);
		assertEquals(BinaryUtils.newInt(max, zero),			0x0000FF00);
		assertEquals(BinaryUtils.newInt(max, one),			0x0000FF01);
		assertEquals(BinaryUtils.newInt(max, max),			0x0000FFFF);

		assertEquals(BinaryUtils.newInt(false, zero, zero),	0x00000000);
		assertEquals(BinaryUtils.newInt(false, zero, one),	0x00000001);
		assertEquals(BinaryUtils.newInt(false, zero, max),	0x000000FF);
		assertEquals(BinaryUtils.newInt(false, one, zero),	0x00000100);
		assertEquals(BinaryUtils.newInt(false, one, one),	0x00000101);
		assertEquals(BinaryUtils.newInt(false, one, max),	0x000001FF);
		assertEquals(BinaryUtils.newInt(false, max, zero),	0x0000FF00);
		assertEquals(BinaryUtils.newInt(false, max, one),	0x0000FF01);
		assertEquals(BinaryUtils.newInt(false, max, max),	0x0000FFFF);

		assertEquals(BinaryUtils.newInt(true, zero, zero),	0x00000000);
		assertEquals(BinaryUtils.newInt(true, zero, one),	0x01000000);
		assertEquals(BinaryUtils.newInt(true, zero, max),	0xFF000000);
		assertEquals(BinaryUtils.newInt(true, one, zero),	0x00010000);
		assertEquals(BinaryUtils.newInt(true, one, one),	0x01010000);
		assertEquals(BinaryUtils.newInt(true, one, max),	0xFF010000);
		assertEquals(BinaryUtils.newInt(true, max, zero),	0x00FF0000);
		assertEquals(BinaryUtils.newInt(true, max, one),	0x01FF0000);
		assertEquals(BinaryUtils.newInt(true, max, max),	0xFFFF0000);
	}

	@Test
	@DisplayName("New long from bytes")
	public void testNewLong()
	{
		byte zero = 0x00;
		byte one = 0x01;
		byte max = -1;

		assertEquals(BinaryUtils.newLong(zero, zero),		0x0000000000000000L);
 		assertEquals(BinaryUtils.newLong(zero, one),		0x0000000000000001L);
		assertEquals(BinaryUtils.newLong(zero, max),		0x00000000000000FFL);
		assertEquals(BinaryUtils.newLong(one, zero),		0x0000000000000100L);
		assertEquals(BinaryUtils.newLong(one, one),			0x0000000000000101L);
		assertEquals(BinaryUtils.newLong(one, max),			0x00000000000001FFL);
		assertEquals(BinaryUtils.newLong(max, zero),		0x000000000000FF00L);
		assertEquals(BinaryUtils.newLong(max, one),			0x000000000000FF01L);
		assertEquals(BinaryUtils.newLong(max, max),			0x000000000000FFFFL);

		assertEquals(BinaryUtils.newLong(false, zero, zero),0x0000000000000000L);
		assertEquals(BinaryUtils.newLong(false, zero, one),	0x0000000000000001L);
		assertEquals(BinaryUtils.newLong(false, zero, max),	0x00000000000000FFL);
		assertEquals(BinaryUtils.newLong(false, one, zero),	0x0000000000000100L);
		assertEquals(BinaryUtils.newLong(false, one, one),	0x0000000000000101L);
		assertEquals(BinaryUtils.newLong(false, one, max),	0x00000000000001FFL);
		assertEquals(BinaryUtils.newLong(false, max, zero),	0x000000000000FF00L);
		assertEquals(BinaryUtils.newLong(false, max, one),	0x000000000000FF01L);
		assertEquals(BinaryUtils.newLong(false, max, max),	0x000000000000FFFFL);

		assertEquals(BinaryUtils.newLong(true, zero, zero),	0x0000000000000000L);
		assertEquals(BinaryUtils.newLong(true, zero, one),	0x0100000000000000L);
		assertEquals(BinaryUtils.newLong(true, zero, max),	0xFF00000000000000L);
		assertEquals(BinaryUtils.newLong(true, one, zero),	0x0001000000000000L);
		assertEquals(BinaryUtils.newLong(true, one, one),	0x0101000000000000L);
		assertEquals(BinaryUtils.newLong(true, one, max),	0xFF01000000000000L);
		assertEquals(BinaryUtils.newLong(true, max, zero),	0x00FF000000000000L);
		assertEquals(BinaryUtils.newLong(true, max, one),	0x01FF000000000000L);
		assertEquals(BinaryUtils.newLong(true, max, max),	0xFFFF000000000000L);
	}
}
