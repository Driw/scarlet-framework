package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ScarletContextTest {

	@Before
	public void init() {
		TestUtils.setReflectionPackageByClass(ScarletContextTest.class);
	}

	@Test
	@DisplayName("Initialize")
	public void initialize() {
		ScarletContext scarletContext = ScarletContext.start(new String[0]);
		assertTrue(scarletContext instanceof DefaultScarletContext);
	}
}
