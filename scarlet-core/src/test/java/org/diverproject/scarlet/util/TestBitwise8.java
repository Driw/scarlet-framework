package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bitwise8 (8 bits)")
public class TestBitwise8 {

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		Bitwise8 bitwise = new Bitwise8();
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(8, bitwise.getProperties().length);

		bitwise = new Bitwise8((byte) 1);
		assertEquals((byte) 1, bitwise.getValue());
		assertEquals(8, bitwise.getProperties().length);

		String[] properties = new String[] { "FIRST", "SECOND", "THIRD" };
		bitwise = new Bitwise8(properties);
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(properties, bitwise.getProperties());
	}

	@Test
	@DisplayName("Has bit property")
	public void hasProperty() {
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;
		byte all = (byte) (first + second + third);

		Bitwise8 bitwise = new Bitwise8(all);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise8((byte) (all - first));
		assertFalse(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise8((byte) (all - second));
		assertTrue(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise8((byte) (all - third));
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise = new Bitwise8();
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));
	}

	@Test
	@DisplayName("set property")
	public void setProperty() {
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;

		Bitwise8 bitwise = new Bitwise8();
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise.set(first);
		assertTrue(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise.set(second);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise.set(third);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));
	}

	@Test
	@DisplayName("set property")
	public void removeProperty() {
		byte first = 0x01;
		byte second = 0x02;
		byte third = 0x04;
		byte all = (byte) (first + second + third);

		Bitwise8 bitwise = new Bitwise8(all);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise.remove(first);
		assertFalse(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise.remove(second);
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise.remove(third);
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));
	}

}
