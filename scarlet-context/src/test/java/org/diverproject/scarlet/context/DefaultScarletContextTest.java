package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.Data;
import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.context.manager.DefaultManager;
import org.diverproject.scarlet.context.manager.ManagerContext;
import org.diverproject.scarlet.context.singleton.Singleton;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.TreeMap;

public class DefaultScarletContextTest {

	private DefaultScarletContext scarletContext;

	@Mock
	private ManagerContext managerContext;

	@BeforeAll
	public static void beforeAll() {
		TestUtils.initialize();
	}

	@BeforeEach
	void setUp() {
		this.managerContext = Mockito.mock(ManagerContext.class);
		this.scarletContext = new DefaultScarletContext();
		this.scarletContext.setManagerContext(this.managerContext);
	}

	@Test
	void testInitialize() {
		String[] args = TestUtils.contextArguments();
		this.scarletContext.initialize(args);
		assertTrue(this.scarletContext.isInitialized());
		assertThrows(ScarletRuntimeException.class, () -> this.scarletContext.initialize(args));
	}

	@Test
	void testRegisterSingleton() {
		String[] args = TestUtils.contextArguments();
		this.scarletContext.initialize(args);
		assertNull(this.scarletContext.getInstance("aKey"));

		Object object = new Object();
		this.scarletContext.registerSingleton("aKey", object);
		this.scarletContext.registerSingleton("aKey", object);
		this.scarletContext.registerSingleton("bKey", null);
		assertEquals(object, this.scarletContext.getInstance("aKey"));
		assertEquals(object, this.scarletContext.getInstance(Object.class, "aKey"));
		assertNull(this.scarletContext.getInstance(Object.class, "bKey"));

		assertThrows(ScarletContextException.class, () -> this.scarletContext.getInstance(List.class, "aKey"));
	}

	@Test
	void testInject() {
		InjectValue injectValue = new InjectValue();
		InjectableManager injectableManager = new InjectableManager();
		InjectableSingleton injectableSingleton = new InjectableSingleton();

		this.scarletContext.setInstances(new TreeMap<>());
		this.scarletContext.getInstances().put(ContextNameGenerator.generateKeyFor(InjectableManager.class), injectableManager);
		this.scarletContext.getInstances().put(ContextNameGenerator.generateKeyFor(InjectableSingleton.class), injectableSingleton);
		this.scarletContext.inject(injectValue);

		assertNotNull(injectValue.getInjectableManager());
		assertNotNull(injectValue.getInjectableSingleton());
		assertNotNull(injectValue.getInjectableInterface());
	}

	@Data
	private static class InjectValue {
		@Injectable
		private InjectableManager injectableManager;

		@Injectable
		private InjectableSingleton injectableSingleton;

		@Injectable
		private InjectableInterface injectableInterface;
	}

	private static class InjectableManager extends DefaultManager {}

	@Singleton
	private static class InjectableSingleton {}

	private interface InjectableInterface {}
	private static class InjectableInterfaceImplementation implements InjectableInterface {}

}
