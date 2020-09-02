package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bitwise Utils")
public class TestBitwiseUtils
{
	@Test
	@DisplayName("Has bit property (8 bits)")
	public void hasProperty8()
	{
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;
		byte bitwise = (byte) (first + second + third);

		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (byte) (second + third);
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (byte) (first + third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (byte) (first + second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = 0;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (8 bits)")
	public void setProperty8()
	{
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;
		byte bitwise = 0;

		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, first);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (8 bits)")
	public void removeProperty8()
	{
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;
		byte all = (byte) (first + second + third);

		assertTrue(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, first);
		assertFalse(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, second);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, third);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertFalse(BitwiseUtils.has(all, third));
	}

	@Test
	@DisplayName("Has bit property (16 bits)")
	public void hasProperty16()
	{
		short first = 0x01;
		short second = 0x02;
		short third = 0x04;
		short bitwise = (short) (first + second + third);

		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (short) (second + third);
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (short) (first + third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = (short) (first + second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = 0;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (16 bits)")
	public void setProperty16()
	{
		short first = 0x01;
		short second = 0x02;
		short third = 0x04;
		short bitwise = 0;

		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, first);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (16 bits)")
	public void removeProperty16()
	{
		short first = 0x01;
		short second = 0x02;
		short third = 0x04;
		short all = (short) (first + second + third);

		assertTrue(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, first);
		assertFalse(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, second);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, third);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertFalse(BitwiseUtils.has(all, third));
	}

	@Test
	@DisplayName("Has bit property (32 bits)")
	public void hasProperty()
	{
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;
		int bitwise = first + second + third;

		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = second + third;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = first + third;
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = first + second;
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = 0;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (32 bits)")
	public void setProperty()
	{
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;
		int bitwise = 0;

		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, first);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (32 bits)")
	public void removeProperty()
	{
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;
		int all = first + second + third;

		assertTrue(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, first);
		assertFalse(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, second);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, third);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertFalse(BitwiseUtils.has(all, third));
	}

	@Test
	@DisplayName("Has bit property (64 bits)")
	public void hasProperty64()
	{
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;
		long bitwise = first + second + third;

		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = second + third;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = first + third;
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));

		bitwise = first + second;
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = 0;
		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (64 bits)")
	public void setProperty64()
	{
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;
		long bitwise = 0;

		assertFalse(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, first);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertFalse(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, second);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertFalse(BitwiseUtils.has(bitwise, third));

		bitwise = BitwiseUtils.set(bitwise, third);
		assertTrue(BitwiseUtils.has(bitwise, first));
		assertTrue(BitwiseUtils.has(bitwise, second));
		assertTrue(BitwiseUtils.has(bitwise, third));
	}

	@Test
	@DisplayName("set property (64 bits)")
	public void removeProperty64()
	{
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;
		long all = first + second + third;

		assertTrue(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, first);
		assertFalse(BitwiseUtils.has(all, first));
		assertTrue(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, second);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertTrue(BitwiseUtils.has(all, third));

		all = BitwiseUtils.remove(all, third);
		assertFalse(BitwiseUtils.has(all, first));
		assertFalse(BitwiseUtils.has(all, second));
		assertFalse(BitwiseUtils.has(all, third));
	}

	@Test
	@DisplayName("To string properties")
	public void testToString()
	{
		String properties[] = new String[] { "A", "B", "C" };

		assertEquals("", BitwiseUtils.toString(0x00, properties));
		assertEquals("A", BitwiseUtils.toString(0x01, properties));
		assertEquals("B", BitwiseUtils.toString(0x02, properties));
		assertEquals("A|B", BitwiseUtils.toString(0x03, properties));
		assertEquals("C", BitwiseUtils.toString(0x04, properties));
		assertEquals("A|C", BitwiseUtils.toString(0x05, properties));
		assertEquals("B|C", BitwiseUtils.toString(0x06, properties));
		assertEquals("A|B|C", BitwiseUtils.toString(0x07, properties));
		assertEquals("", BitwiseUtils.toString(0x08, properties));
	}
}
