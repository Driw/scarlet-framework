package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

public class DefaultScarletContextTest {

	@BeforeAll
	public static void beforeAll() {
		TestUtils.initialize();
	}

	@Test
	void Test01_Initialize() {
		String[] args = TestUtils.contextArguments();
		DefaultScarletContext scarletContext = new DefaultScarletContext();
		scarletContext.initialize(args);
		assertTrue(scarletContext.isInitialized());
		assertThrows(ScarletRuntimeException.class, () -> scarletContext.initialize(args));
	}

	@Test
	void Test02_RegisterSingleton() {
		String[] args = TestUtils.contextArguments();
		DefaultScarletContext scarletContext = new DefaultScarletContext();
		scarletContext.initialize(args);
		assertNull(scarletContext.getInstance("aKey"));

		Object object = new Object();
		scarletContext.registerSingleton("aKey", object);
		scarletContext.registerSingleton("aKey", object);
		scarletContext.registerSingleton("bKey", null);
		assertEquals(object, scarletContext.getInstance("aKey"));
		assertEquals(object, scarletContext.getInstance(Object.class, "aKey"));
		assertNull(scarletContext.getInstance(Object.class, "bKey"));

		assertThrows(ScarletContextException.class, () -> scarletContext.getInstance(List.class, "aKey"));
	}

}
