package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.ArrayUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("Array Utils")
public class TestArrayUtils
{
	@Test
	@DisplayName("Join")
	public void testJoin()
	{
		String[] items = new String[] { "a", "b", "c", "d", "e" };

		assertEquals(ArrayUtils.join(items), "a, b, c, d, e");
		assertEquals(ArrayUtils.join(";", items), "a;b;c;d;e");
		assertEquals(ArrayUtils.join(Arrays.asList(items).iterator()), "a, b, c, d, e");
		assertEquals(ArrayUtils.join(Arrays.asList(items).iterator(), ";"), "a;b;c;d;e");
	}

	@Test
	@DisplayName("In array")
	public void testIn()
	{
		String[] items = new String[] { "a", "b", "c", "d", "e" };

		assertTrue(ArrayUtils.in("a", items));
		assertTrue(ArrayUtils.in("b", items));
		assertTrue(ArrayUtils.in("c", items));
		assertTrue(ArrayUtils.in("d", items));
		assertTrue(ArrayUtils.in("e", items));
		assertFalse(ArrayUtils.in("f", items));
	}

	@Test
	@DisplayName("Has index")
	public void testHasIndex()
	{
		String[] items = new String[] { "a", "b" };

		assertFalse(ArrayUtils.hasArrayIndex(items, -1));
		assertTrue(ArrayUtils.hasArrayIndex(items, 0));
		assertTrue(ArrayUtils.hasArrayIndex(items, 1));
		assertFalse(ArrayUtils.hasArrayIndex(items, 2));
	}

	@Test
	@DisplayName("Sub array (generic)")
	public void testSubArrayGeneric()
	{
		String[] array = new String[] { "a", "b", "c", "d", "e" };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new String[] { "a" });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new String[] { "a", "b", "c" });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new String[] { "c" });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new String[] { "c", "d", "e" });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive char)")
	public void testSubArrayChar()
	{
		char[] array = new char[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new char[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new char[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new char[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new char[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive byte)")
	public void testSubArrayByte()
	{
		byte[] array = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new byte[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new byte[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new byte[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new byte[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive short)")
	public void testSubArrayShort()
	{
		short[] array = new short[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new short[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new short[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new short[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new short[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive int)")
	public void testSubArrayInt()
	{
		int[] array = new int[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new int[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new int[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new int[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new int[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive long)")
	public void testSubArrayLong()
	{
		long[] array = new long[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new long[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new long[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new long[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new long[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive float)")
	public void testSubArrayFloat()
	{
		float[] array = new float[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new float[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new float[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new float[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new float[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Sub array (primitive double)")
	public void testSubArrayDouble()
	{
		double[] array = new double[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

		assertArrayEquals(ArrayUtils.subArray(array, 0, 1), new double[] { 0x01 });
		assertArrayEquals(ArrayUtils.subArray(array, 0, 3), new double[] { 0x01, 0x02, 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 1), new double[] { 0x03 });
		assertArrayEquals(ArrayUtils.subArray(array, 2, 3), new double[] { 0x03, 0x04, 0x05 });

		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, -1, 1); });
		assertThrows(ArrayUtilsRuntimeException.class, () -> { ArrayUtils.subArray(array, 0, 0); });
	}

	@Test
	@DisplayName("Reallocate data")
	public void testReallocate()
	{
		assertArrayEquals(array("0"), ArrayUtils.reallocate(array("0", "1", "2", "3"), 1));
		assertArrayEquals(array("0", "1"), ArrayUtils.reallocate(array("0", "1", "2", "3"), 2));
		assertArrayEquals(array("0", "1", "2"), ArrayUtils.reallocate(array("0", "1", "2", "3"), 3));
		assertArrayEquals(array("0", "1", "2", "3"), ArrayUtils.reallocate(array("0", "1", "2", "3"), 4));
		assertArrayEquals(array("0"), ArrayUtils.reallocate(array("0", "1"), 1));
		assertArrayEquals(array("0", "1"), ArrayUtils.reallocate(array("0", "1"), 2));
		assertArrayEquals(array("0", "1", null), ArrayUtils.reallocate(array("0", "1"), 3));
		assertArrayEquals(array("0", "1", null, null), ArrayUtils.reallocate(array("0", "1"), 4));

		byte byte0 = 0;
		byte byte1 = 1;
		byte byte2 = 2;
		byte byte3 = 3;

		assertArrayEquals(bytes(byte0), ArrayUtils.reallocate(bytes(byte0, byte1, byte2, byte3), 1));
		assertArrayEquals(bytes(byte0, byte1), ArrayUtils.reallocate(bytes(byte0, byte1, byte2, byte3), 2));
		assertArrayEquals(bytes(byte0, byte1, byte2), ArrayUtils.reallocate(bytes(byte0, byte1, byte2, byte3), 3));
		assertArrayEquals(bytes(byte0, byte1, byte2, byte3), ArrayUtils.reallocate(bytes(byte0, byte1, byte2, byte3), 4));
		assertArrayEquals(bytes(byte0), ArrayUtils.reallocate(bytes(byte0, byte1), 1));
		assertArrayEquals(bytes(byte0, byte1), ArrayUtils.reallocate(bytes(byte0, byte1), 2));
		assertArrayEquals(bytes(byte0, byte1, byte0), ArrayUtils.reallocate(bytes(byte0, byte1), 3));

		short short0 = 0;
		short short1 = 1;
		short short2 = 2;
		short short3 = 3;

		assertArrayEquals(shorts(short0), ArrayUtils.reallocate(shorts(short0, short1, short2, short3), 1));
		assertArrayEquals(shorts(short0, short1), ArrayUtils.reallocate(shorts(short0, short1, short2, short3), 2));
		assertArrayEquals(shorts(short0, short1, short2), ArrayUtils.reallocate(shorts(short0, short1, short2, short3), 3));
		assertArrayEquals(shorts(short0, short1, short2, short3), ArrayUtils.reallocate(shorts(short0, short1, short2, short3), 4));
		assertArrayEquals(shorts(short0), ArrayUtils.reallocate(shorts(short0, short1), 1));
		assertArrayEquals(shorts(short0, short1), ArrayUtils.reallocate(shorts(short0, short1), 2));
		assertArrayEquals(shorts(short0, short1, short0), ArrayUtils.reallocate(shorts(short0, short1), 3));

		int int0 = 0;
		int int1 = 1;
		int int2 = 2;
		int int3 = 3;

		assertArrayEquals(ints(int0), ArrayUtils.reallocate(ints(int0, int1, int2, int3), 1));
		assertArrayEquals(ints(int0, int1), ArrayUtils.reallocate(ints(int0, int1, int2, int3), 2));
		assertArrayEquals(ints(int0, int1, int2), ArrayUtils.reallocate(ints(int0, int1, int2, int3), 3));
		assertArrayEquals(ints(int0, int1, int2, int3), ArrayUtils.reallocate(ints(int0, int1, int2, int3), 4));
		assertArrayEquals(ints(int0), ArrayUtils.reallocate(ints(int0, int1), 1));
		assertArrayEquals(ints(int0, int1), ArrayUtils.reallocate(ints(int0, int1), 2));
		assertArrayEquals(ints(int0, int1, int0), ArrayUtils.reallocate(ints(int0, int1), 3));

		long long0 = 0L;
		long long1 = 1L;
		long long2 = 2L;
		long long3 = 3L;

		assertArrayEquals(longs(long0), ArrayUtils.reallocate(longs(long0, long1, long2, long3), 1));
		assertArrayEquals(longs(long0, long1), ArrayUtils.reallocate(longs(long0, long1, long2, long3), 2));
		assertArrayEquals(longs(long0, long1, long2), ArrayUtils.reallocate(longs(long0, long1, long2, long3), 3));
		assertArrayEquals(longs(long0, long1, long2, long3), ArrayUtils.reallocate(longs(long0, long1, long2, long3), 4));
		assertArrayEquals(longs(long0), ArrayUtils.reallocate(longs(long0, long1), 1));
		assertArrayEquals(longs(long0, long1), ArrayUtils.reallocate(longs(long0, long1), 2));
		assertArrayEquals(longs(long0, long1, long0), ArrayUtils.reallocate(longs(long0, long1), 3));

		float float0 = 0F;
		float float1 = 1F;
		float float2 = 2F;
		float float3 = 3F;

		assertArrayEquals(floats(float0), ArrayUtils.reallocate(floats(float0, float1, float2, float3), 1));
		assertArrayEquals(floats(float0, float1), ArrayUtils.reallocate(floats(float0, float1, float2, float3), 2));
		assertArrayEquals(floats(float0, float1, float2), ArrayUtils.reallocate(floats(float0, float1, float2, float3), 3));
		assertArrayEquals(floats(float0, float1, float2, float3), ArrayUtils.reallocate(floats(float0, float1, float2, float3), 4));
		assertArrayEquals(floats(float0), ArrayUtils.reallocate(floats(float0, float1), 1));
		assertArrayEquals(floats(float0, float1), ArrayUtils.reallocate(floats(float0, float1), 2));
		assertArrayEquals(floats(float0, float1, float0), ArrayUtils.reallocate(floats(float0, float1), 3));

		double double0 = 0D;
		double double1 = 1D;
		double double2 = 2D;
		double double3 = 3D;

		assertArrayEquals(doubles(double0), ArrayUtils.reallocate(doubles(double0, double1, double2, double3), 1));
		assertArrayEquals(doubles(double0, double1), ArrayUtils.reallocate(doubles(double0, double1, double2, double3), 2));
		assertArrayEquals(doubles(double0, double1, double2), ArrayUtils.reallocate(doubles(double0, double1, double2, double3), 3));
		assertArrayEquals(doubles(double0, double1, double2, double3), ArrayUtils.reallocate(doubles(double0, double1, double2, double3), 4));
		assertArrayEquals(doubles(double0), ArrayUtils.reallocate(doubles(double0, double1), 1));
		assertArrayEquals(doubles(double0, double1), ArrayUtils.reallocate(doubles(double0, double1), 2));
		assertArrayEquals(doubles(double0, double1, double0), ArrayUtils.reallocate(doubles(double0, double1), 3));

		char char0 = 0;
		char char1 = 1;
		char char2 = 2;
		char char3 = 3;

		assertArrayEquals(chars(char0), ArrayUtils.reallocate(chars(char0, char1, char2, char3), 1));
		assertArrayEquals(chars(char0, char1), ArrayUtils.reallocate(chars(char0, char1, char2, char3), 2));
		assertArrayEquals(chars(char0, char1, char2), ArrayUtils.reallocate(chars(char0, char1, char2, char3), 3));
		assertArrayEquals(chars(char0, char1, char2, char3), ArrayUtils.reallocate(chars(char0, char1, char2, char3), 4));
		assertArrayEquals(chars(char0), ArrayUtils.reallocate(chars(char0, char1), 1));
		assertArrayEquals(chars(char0, char1), ArrayUtils.reallocate(chars(char0, char1), 2));
		assertArrayEquals(chars(char0, char1, char0), ArrayUtils.reallocate(chars(char0, char1), 3));

		assertArrayEquals(booleans(false), ArrayUtils.reallocate(booleans(false, true, false, true), 1));
		assertArrayEquals(booleans(false, true), ArrayUtils.reallocate(booleans(false, true, false, true), 2));
		assertArrayEquals(booleans(false, true, false), ArrayUtils.reallocate(booleans(false, true, false, true), 3));
		assertArrayEquals(booleans(false, true, false, true), ArrayUtils.reallocate(booleans(false, true, false, true), 4));
		assertArrayEquals(booleans(false), ArrayUtils.reallocate(booleans(false, true), 1));
		assertArrayEquals(booleans(false, true), ArrayUtils.reallocate(booleans(false, true), 2));
		assertArrayEquals(booleans(false, true, false), ArrayUtils.reallocate(booleans(false, true), 3));
	}

	@Test
	@DisplayName("Copy")
	public void testCopy()
	{
		String[] stringSource = new String[] { "ABC", "DEF", "GHI", "JKL", "MNO" };
		String[] stringDestination = new String[stringSource.length];
		{
			ArrayUtils.copy(stringSource, stringDestination);
			assertArrayEquals(stringSource, stringDestination);

			assertThrows(IndexOutOfBoundsException.class, () -> { ArrayUtils.copy(stringSource, new String[stringSource.length], -1); });

			ArrayUtils.copy(stringSource, stringDestination, 0);
			assertArrayEquals(stringSource, stringDestination);

			ArrayUtils.copy(stringSource, (stringDestination = new String[stringSource.length]), 1);
			assertArrayEquals(array(null, "DEF", "GHI", "JKL", "MNO"), stringDestination);

			ArrayUtils.copy(stringSource, (stringDestination = new String[stringSource.length]), 1, 3);
			assertArrayEquals(array(null, "DEF", "GHI", "JKL", null), stringDestination);

			ArrayUtils.copy(stringSource, (stringDestination = new String[stringSource.length]), 1, 4);
			assertArrayEquals(array(null, "DEF", "GHI", "JKL", "MNO"), stringDestination);

			ArrayUtils.copy(stringSource, (stringDestination = new String[stringSource.length]), 1, 5);
			assertArrayEquals(array(null, "DEF", "GHI", "JKL", "MNO"), stringDestination);

			ArrayUtils.copy(stringSource, (stringDestination = new String[stringSource.length + 1]), 1, 5);
			assertArrayEquals(array(null, "DEF", "GHI", "JKL", "MNO", null), stringDestination);
		}

		byte[] byteSource = new byte[] { 0, 1, 2, 3, 4 };
		byte[] byteDestination = new byte[byteSource.length];
		{
			byte zero = 0;
			byte one = 1;
			byte two = 2;
			byte three = 3;
			byte four = 4;

			ArrayUtils.copy(byteSource, byteDestination);
			assertArrayEquals(byteSource, byteDestination);

			assertThrows(IndexOutOfBoundsException.class, () -> { ArrayUtils.copy(byteSource, new byte[byteSource.length], -1); });

			ArrayUtils.copy(byteSource, byteDestination, 0);
			assertArrayEquals(byteSource, byteDestination);

			ArrayUtils.copy(byteSource, (byteDestination = new byte[byteSource.length]), 1);
			assertArrayEquals(bytes(zero, one, two, three, four), byteDestination);

			ArrayUtils.copy(byteSource, (byteDestination = new byte[byteSource.length]), 1, 3);
			assertArrayEquals(bytes(zero, one, two, three, zero), byteDestination);

			ArrayUtils.copy(byteSource, (byteDestination = new byte[byteSource.length]), 1, 4);
			assertArrayEquals(bytes(zero, one, two, three, four), byteDestination);

			ArrayUtils.copy(byteSource, (byteDestination = new byte[byteSource.length]), 1, 5);
			assertArrayEquals(bytes(zero, one, two, three, four), byteDestination);

			ArrayUtils.copy(byteSource, (byteDestination = new byte[byteSource.length + 1]), 1, 5);
			assertArrayEquals(bytes(zero, one, two, three, four, zero), byteDestination);
		}

		short[] shortSource = new short[] { 0, 1, 2, 3, 4 };
		short[] shortDestination = new short[shortSource.length];
		{
			short zero = 0;
			short one = 1;
			short two = 2;
			short three = 3;
			short four = 4;

			ArrayUtils.copy(shortSource, shortDestination);
			assertArrayEquals(shortSource, shortDestination);

			ArrayUtils.copy(shortSource, (shortDestination = new short[shortSource.length]), 1);
			assertArrayEquals(shorts(zero, one, two, three, four), shortDestination);

			ArrayUtils.copy(shortSource, (shortDestination = new short[shortSource.length]), 1, 3);
			assertArrayEquals(shorts(zero, one, two, three, zero), shortDestination);

			ArrayUtils.copy(shortSource, (shortDestination = new short[shortSource.length]), 1, 4);
			assertArrayEquals(shorts(zero, one, two, three, four), shortDestination);

			ArrayUtils.copy(shortSource, (shortDestination = new short[shortSource.length]), 1, 5);
			assertArrayEquals(shorts(zero, one, two, three, four), shortDestination);

			ArrayUtils.copy(shortSource, (shortDestination = new short[shortSource.length + 1]), 1, 5);
			assertArrayEquals(shorts(zero, one, two, three, four, zero), shortDestination);
		}

		int[] intSource = new int[] { 0, 1, 2, 3, 4 };
		int[] intDestination = new int[intSource.length];
		{
			ArrayUtils.copy(intSource, intDestination);
			assertArrayEquals(intSource, intDestination);

			ArrayUtils.copy(intSource, intDestination);
			assertArrayEquals(intSource, intDestination);

			ArrayUtils.copy(intSource, (intDestination = new int[intSource.length]), 1);
			assertArrayEquals(ints(0, 1, 2, 3, 4), intDestination);

			ArrayUtils.copy(intSource, (intDestination = new int[intSource.length]), 1, 3);
			assertArrayEquals(ints(0, 1, 2, 3, 0), intDestination);

			ArrayUtils.copy(intSource, (intDestination = new int[intSource.length]), 1, 4);
			assertArrayEquals(ints(0, 1, 2, 3, 4), intDestination);

			ArrayUtils.copy(intSource, (intDestination = new int[intSource.length]), 1, 5);
			assertArrayEquals(ints(0, 1, 2, 3, 4), intDestination);

			ArrayUtils.copy(intSource, (intDestination = new int[intSource.length + 1]), 1, 5);
			assertArrayEquals(ints(0, 1, 2, 3, 4, 0), intDestination);
		}

		long[] longSource = new long[] { 0L, 1L, 2L, 3L, 4L };
		long[] longDestination = new long[longSource.length];
		{
			ArrayUtils.copy(longSource, longDestination);
			assertArrayEquals(longSource, longDestination);

			ArrayUtils.copy(longSource, longDestination);
			assertArrayEquals(longSource, longDestination);

			ArrayUtils.copy(longSource, (longDestination = new long[longSource.length]), 1);
			assertArrayEquals(longs(0, 1, 2, 3, 4), longDestination);

			ArrayUtils.copy(longSource, (longDestination = new long[longSource.length]), 1, 3);
			assertArrayEquals(longs(0, 1, 2, 3, 0), longDestination);

			ArrayUtils.copy(longSource, (longDestination = new long[longSource.length]), 1, 4);
			assertArrayEquals(longs(0, 1, 2, 3, 4), longDestination);

			ArrayUtils.copy(longSource, (longDestination = new long[longSource.length]), 1, 5);
			assertArrayEquals(longs(0, 1, 2, 3, 4), longDestination);

			ArrayUtils.copy(longSource, (longDestination = new long[longSource.length + 1]), 1, 5);
			assertArrayEquals(longs(0, 1, 2, 3, 4, 0), longDestination);
		}

		float[] floatSource = new float[] { 0F, 1F, 2F, 3F, 4F };
		float[] floatDestination = new float[floatSource.length];
		{
			ArrayUtils.copy(floatSource, floatDestination);
			assertArrayEquals(floatSource, floatDestination);

			ArrayUtils.copy(floatSource, floatDestination);
			assertArrayEquals(floatSource, floatDestination);

			ArrayUtils.copy(floatSource, (floatDestination = new float[floatSource.length]), 1);
			assertArrayEquals(floats(0, 1, 2, 3, 4), floatDestination);

			ArrayUtils.copy(floatSource, (floatDestination = new float[floatSource.length]), 1, 3);
			assertArrayEquals(floats(0, 1, 2, 3, 0), floatDestination);

			ArrayUtils.copy(floatSource, (floatDestination = new float[floatSource.length]), 1, 4);
			assertArrayEquals(floats(0, 1, 2, 3, 4), floatDestination);

			ArrayUtils.copy(floatSource, (floatDestination = new float[floatSource.length]), 1, 5);
			assertArrayEquals(floats(0, 1, 2, 3, 4), floatDestination);

			ArrayUtils.copy(floatSource, (floatDestination = new float[floatSource.length + 1]), 1, 5);
			assertArrayEquals(floats(0, 1, 2, 3, 4, 0), floatDestination);
		}

		double[] doubleSource = new double[] { 0D, 1D, 2D, 3D, 4D };
		double[] doubleDestination = new double[doubleSource.length];
		{
			ArrayUtils.copy(doubleSource, doubleDestination);
			assertArrayEquals(doubleSource, doubleDestination);

			ArrayUtils.copy(doubleSource, doubleDestination);
			assertArrayEquals(doubleSource, doubleDestination);

			ArrayUtils.copy(doubleSource, (doubleDestination = new double[doubleSource.length]), 1);
			assertArrayEquals(doubles(0, 1, 2, 3, 4), doubleDestination);

			ArrayUtils.copy(doubleSource, (doubleDestination = new double[doubleSource.length]), 1, 3);
			assertArrayEquals(doubles(0, 1, 2, 3, 0), doubleDestination);

			ArrayUtils.copy(doubleSource, (doubleDestination = new double[doubleSource.length]), 1, 4);
			assertArrayEquals(doubles(0, 1, 2, 3, 4), doubleDestination);

			ArrayUtils.copy(doubleSource, (doubleDestination = new double[doubleSource.length]), 1, 5);
			assertArrayEquals(doubles(0, 1, 2, 3, 4), doubleDestination);

			ArrayUtils.copy(doubleSource, (doubleDestination = new double[doubleSource.length + 1]), 1, 5);
			assertArrayEquals(doubles(0, 1, 2, 3, 4, 0), doubleDestination);
		}

		char[] charSource = new char[] { 0, 1, 2, 3, 4 };
		char[] charDestination = new char[charSource.length];
		{
			char zero = 0;
			char one = 1;
			char two = 2;
			char three = 3;
			char four = 4;

			ArrayUtils.copy(charSource, charDestination);
			assertArrayEquals(charSource, charDestination);

			ArrayUtils.copy(charSource, (charDestination = new char[charSource.length]), 1);
			assertArrayEquals(chars(zero, one, two, three, four), charDestination);

			ArrayUtils.copy(charSource, (charDestination = new char[charSource.length]), 1, 3);
			assertArrayEquals(chars(zero, one, two, three, zero), charDestination);

			ArrayUtils.copy(charSource, (charDestination = new char[charSource.length]), 1, 4);
			assertArrayEquals(chars(zero, one, two, three, four), charDestination);

			ArrayUtils.copy(charSource, (charDestination = new char[charSource.length]), 1, 5);
			assertArrayEquals(chars(zero, one, two, three, four), charDestination);

			ArrayUtils.copy(charSource, (charDestination = new char[charSource.length + 1]), 1, 5);
			assertArrayEquals(chars(zero, one, two, three, four, zero), charDestination);
		}

		boolean[] booleanSource = new boolean[] { true, false, true, false, true };
		boolean[] booleanDestination = new boolean[booleanSource.length];
		{
			ArrayUtils.copy(booleanSource, booleanDestination);
			assertArrayEquals(booleanSource, booleanDestination);

			ArrayUtils.copy(booleanSource, (booleanDestination = new boolean[booleanSource.length]), 1);
			assertArrayEquals(booleans(false, false, true, false, true), booleanDestination);

			ArrayUtils.copy(booleanSource, (booleanDestination = new boolean[booleanSource.length]), 1, 3);
			assertArrayEquals(booleans(false, false, true, false, false), booleanDestination);

			ArrayUtils.copy(booleanSource, (booleanDestination = new boolean[booleanSource.length]), 1, 4);
			assertArrayEquals(booleans(false, false, true, false, true), booleanDestination);

			ArrayUtils.copy(booleanSource, (booleanDestination = new boolean[booleanSource.length]), 1, 5);
			assertArrayEquals(booleans(false, false, true, false, true), booleanDestination);

			ArrayUtils.copy(booleanSource, (booleanDestination = new boolean[booleanSource.length + 1]), 1, 5);
			assertArrayEquals(booleans(false, false, true, false, true, false), booleanDestination);
		}
	}

	@SafeVarargs
	private <D> D[] array(D... values)
	{
		return values;
	}

	private byte[] bytes(byte... values)
	{
		return values;
	}

	private short[] shorts(short... values)
	{
		return values;
	}

	private int[] ints(int... values)
	{
		return values;
	}

	private long[] longs(long... values)
	{
		return values;
	}

	private float[] floats(float... values)
	{
		return values;
	}

	private double[] doubles(double... values)
	{
		return values;
	}

	private char[] chars(char... values)
	{
		return values;
	}

	private boolean[] booleans(boolean... values)
	{
		return values;
	}
}
