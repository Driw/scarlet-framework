package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ScarletContextTest {

	@BeforeAll
	public static void beforeAll() {
		TestUtils.initialize();
	}

	@Test
	public void testStart() {
		ScarletContext scarletContext = ScarletContext.start(new String[0]);
		assertNotNull(scarletContext);
		assertTrue(scarletContext.isInitialized());
	}

}
