package org.diverproject.scarlet.context.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.ContextNameGenerator;
import org.diverproject.scarlet.context.DefaultInstanceEntry;
import org.diverproject.scarlet.context.Named;
import org.diverproject.scarlet.context.Priority;
import org.diverproject.scarlet.context.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

public class DefaultManagerContextTest {

	private DefaultManagerContext managerContext;

	@BeforeAll
	public static void beforeAll() {
	}

	@BeforeEach
	public void beforeEach() {
		this.managerContext = new DefaultManagerContext();
	}

	@Test
	public void findAllManagerImplementations() {
		Map<String, Class<Manager>> managerImplementations = this.managerContext.findAllManagerImplementations();
		assertEquals(managerImplementations.size(), 3);
		assertTrue(managerImplementations.containsValue(FirstManager.class));
		assertTrue(managerImplementations.containsValue(SecondManager.class));
		assertTrue(managerImplementations.containsValue(ThirdManagerExtended.class));
		assertTrue(managerImplementations.containsKey(ContextNameGenerator.generateKeyFor(FirstManager.class)));
		assertTrue(managerImplementations.containsKey(ContextNameGenerator.generateKeyFor(SecondManager.class)));
		assertTrue(managerImplementations.containsKey(ContextNameGenerator.generateKeyFor(ThirdManagerExtended.class)));
	}

	@Test
	public void initialize() {
		this.managerContext.initialize();
		assertTrue(this.managerContext.isInitialized());
	}

	@Test
	public void initializeManagers() {
		Map<String, Manager> managers = this.managerContext.initializeManagers();
		assertNotNull(this.managerContext.getManagerInstances());
		assertNotNull(this.managerContext.getManagerQueue());
		assertNotNull(managers);
		assertEquals(managers, this.managerContext.getManagerInstances());
		assertEquals(3, managers.size());
		assertTrue(TestUtils.containsAll(managers.values(), Manager::getClass, FirstManager.class, SecondManager.class, ThirdManagerExtended.class));
	}

	@Test
	public void get() {
		assertNull(this.managerContext.get(FirstManager.class));

		Manager manager = new FirstManager();
		String key = ContextNameGenerator.generateKeyFor(manager.getClass());
		this.managerContext.register(new DefaultInstanceEntry<>(key, manager));

		Manager getManager = this.managerContext.get(FirstManager.class);
		assertNotNull(getManager);
		assertEquals(manager, getManager);

		assertThrows(ManagerContextException.class, () -> this.managerContext.get(DuplicatedFirstManager.class));
	}

	private static class FirstManager extends DefaultManager {
		public FirstManager() {
			this.setOrder(1);
		}
	}

	private static class SecondManager extends DefaultManager {
		public SecondManager() {
			this.setOrder(2);
		}
	}

	private static class ThirdManager extends DefaultManager {
		public ThirdManager() {
			this.setOrder(3);
		}
	}

	private static class ThirdManagerExtended extends ThirdManager {
		public ThirdManagerExtended() {
			this.setOrder(4);
		}
	}

	@Priority(-1)
	@Named("org.diverproject.scarlet.context.manager.DefaultManagerContextTest$FirstManager")
	private static class DuplicatedFirstManager extends DefaultManager {}

}
