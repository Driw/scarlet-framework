package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.singleton.Singleton;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class DefaultScarletContextTest {

	@Before
	public void init() {
		TestUtils.setReflectionPackageByClass(DefaultScarletContextTest.class);
	}

	@Test
	@DisplayName("Initialize")
	public void initialize() {
		DefaultScarletContext scarletContext = new DefaultScarletContext();
		scarletContext.initialize(new String[0]);

		SingletonInterface singletonInterface = scarletContext.getSingletonContext().get(SingletonInterface.class);
		assertNotNull(singletonInterface);
		assertTrue(singletonInterface instanceof SingletonExtendedImplementation);

		SingletonExtendedInterface singletonExtendedInterface = scarletContext.getSingletonContext().get(SingletonExtendedInterface.class);
		assertNotNull(singletonExtendedInterface);
		assertTrue(singletonExtendedInterface instanceof SingletonExtendedImplementation);
	}

	@Named
	@Singleton
	public static interface SingletonInterface {
	}

	public static interface SingletonExtendedInterface extends SingletonInterface {
	}

	public static class SingletonImplementation implements SingletonInterface {
	}

	public static class SingletonExtendedImplementation implements SingletonExtendedInterface {
	}
}
