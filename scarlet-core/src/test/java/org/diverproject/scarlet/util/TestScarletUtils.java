package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Scarlet Utils")
public class TestScarletUtils {

	@Test
	@DisplayName("NVL")
	public void testNvl() {
		assertEquals(ScarletUtils.nvl(1, 1), 1);
		assertEquals(ScarletUtils.nvl(1, null), 1);
		assertEquals(ScarletUtils.nvl(null, 1), 1);
		assertNull(ScarletUtils.nvl(null, null));
	}
}
