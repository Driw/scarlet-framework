package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class DefaultScarletContextTest {

	@BeforeAll
	public static void beforeAll() {
		TestUtils.initialize();
	}

	@Test
	@DisplayName("Initialize")
	public void Test01_Initialize() {
		String[] args = TestUtils.contextArguments();
		DefaultScarletContext scarletContext = new DefaultScarletContext();
		scarletContext.initialize(args);
		assertTrue(scarletContext.isInitialized());
		assertThrows(ScarletRuntimeException.class, () -> scarletContext.initialize(args));
	}

	@Test
	@DisplayName("Register and Get Singleton")
	public void Test02_RegisterSingleton() {
		String[] args = TestUtils.contextArguments();
		DefaultScarletContext scarletContext = new DefaultScarletContext();
		scarletContext.initialize(args);
		assertNull(scarletContext.getInstance("aKey"));

		Object object = new Object();
		scarletContext.registerSingleton("aKey", object);
		assertEquals(object, scarletContext.getInstance("aKey"));
		assertEquals(object, scarletContext.getInstance(Object.class, "aKey"));
	}

}
