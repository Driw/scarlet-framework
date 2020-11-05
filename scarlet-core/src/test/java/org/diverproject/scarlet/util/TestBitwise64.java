package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bitwise (64 bits)")
public class TestBitwise64 {

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		Bitwise64 bitwise = new Bitwise64();
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(Bitwise64.DEFAULT_PROPERTIES, bitwise.getProperties());

		bitwise = new Bitwise64((byte) 1);
		assertEquals((byte) 1, bitwise.getValue());
		assertEquals(Bitwise64.DEFAULT_PROPERTIES, bitwise.getProperties());

		String[] properties = new String[] { "FIRST", "SECOND", "THIRD" };
		bitwise = new Bitwise64(properties);
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(properties, bitwise.getProperties());
	}

	@Test
	@DisplayName("Has bit property")
	public void hasProperty() {
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;
		long all = first + second + third;

		Bitwise64 bitwise = new Bitwise64(all);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise64(all - first);
		assertFalse(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise64(all - second);
		assertTrue(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise64(all - third);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise = new Bitwise64();
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));
	}

	@Test
	@DisplayName("set property")
	public void setProperty() {
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;

		Bitwise64 bitwise = new Bitwise64();
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
		long first = 0x01;
		long second = 0x02;
		long third = 0x04;
		long all = first + second + third;

		Bitwise64 bitwise = new Bitwise64(all);
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
