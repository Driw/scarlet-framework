package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bitwise (32 bits)")
public class BitwiseTest {

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		Bitwise bitwise = new Bitwise();
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(32, bitwise.getProperties().length);

		bitwise = new Bitwise((byte) 1);
		assertEquals((byte) 1, bitwise.getValue());
		assertEquals(32, bitwise.getProperties().length);

		String[] properties = new String[] { "FIRST", "SECOND", "THIRD" };
		bitwise = new Bitwise(properties);
		assertEquals((byte) 0, bitwise.getValue());
		assertEquals(properties, bitwise.getProperties());
	}

	@Test
	@DisplayName("Has bit property")
	public void hasProperty() {
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;
		int all = first + second + third;

		Bitwise bitwise = new Bitwise(all);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise(all - first);
		assertFalse(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise(all - second);
		assertTrue(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertTrue(bitwise.has(third));

		bitwise = new Bitwise(all - third);
		assertTrue(bitwise.has(first));
		assertTrue(bitwise.has(second));
		assertFalse(bitwise.has(third));

		bitwise = new Bitwise();
		assertFalse(bitwise.has(first));
		assertFalse(bitwise.has(second));
		assertFalse(bitwise.has(third));
	}

	@Test
	@DisplayName("set property")
	public void setProperty() {
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;

		Bitwise bitwise = new Bitwise();
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
		int first = 0x01;
		int second = 0x02;
		int third = 0x04;
		int all = first + second + third;

		Bitwise bitwise = new Bitwise(all);
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
