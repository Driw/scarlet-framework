package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Boolean Utils")
public class BooleanUtilsTest {

	@Test
	public void test() {
		assertFalse(BooleanUtils.parseBoolean("false"));
		assertFalse(BooleanUtils.parseBoolean("no"));
		assertFalse(BooleanUtils.parseBoolean("0"));
		assertTrue(BooleanUtils.parseBoolean("true"));
		assertTrue(BooleanUtils.parseBoolean("yes"));
		assertTrue(BooleanUtils.parseBoolean("1"));

		assertFalse(BooleanUtils.parseBoolean("", false));
		assertTrue(BooleanUtils.parseBoolean("", true));
		assertFalse(BooleanUtils.parseBoolean("false", null));
		assertFalse(BooleanUtils.parseBoolean("no", null));
		assertFalse(BooleanUtils.parseBoolean("0", null));
		assertTrue(BooleanUtils.parseBoolean("true", null));
		assertTrue(BooleanUtils.parseBoolean("yes", null));
		assertTrue(BooleanUtils.parseBoolean("1", null));
		assertTrue(BooleanUtils.parseBoolean("1", null));
		assertFalse(BooleanUtils.parseBoolean("2", false));
		assertTrue(BooleanUtils.parseBoolean("2", true));
		assertNull(BooleanUtils.parseBoolean("2", null));
	}
}
