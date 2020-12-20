package org.diverproject.scarlet.context.manager;

import static org.diverproject.scarlet.util.ScarletUtils.waitUntil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import org.diverproject.scarlet.context.ContextNameGenerator;
import org.diverproject.scarlet.context.DefaultInstanceEntry;
import org.diverproject.scarlet.context.Named;
import org.diverproject.scarlet.context.Priority;
import org.diverproject.scarlet.context.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

public class DefaultManagerContextTest {

	private DefaultManagerContext managerContext;

	@BeforeAll
	public static void beforeAll() {
		TestUtils.initialize();
		TestUtils.setHereAsPackageReflection();
	}

	@BeforeEach
	public void beforeEach() {
		this.managerContext = new DefaultManagerContext();
	}

	@Test
	public void testFindAllManagerImplementations() {
		Map<String, Class<Manager>> managerImplementations = this.managerContext.findAllManagerImplementations();
		assertEquals(3, managerImplementations.size());
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
	public void testInitializeManagers() {
		Map<String, Manager> managers = this.managerContext.initializeManagers();
		assertNotNull(this.managerContext.getManagerInstances());
		assertNotNull(this.managerContext.getManagerQueue());
		assertNotNull(managers);
		assertEquals(managers, this.managerContext.getManagerInstances());
		assertEquals(3, managers.size());
		assertTrue(TestUtils.containsAll(managers.values(), Manager::getClass, FirstManager.class, SecondManager.class, ThirdManagerExtended.class));
	}

	@Test
	public void testGet() {
		assertNull(this.managerContext.get(FirstManager.class));

		Manager manager = new FirstManager();
		String key = ContextNameGenerator.generateKeyFor(manager.getClass());
		this.managerContext.register(new DefaultInstanceEntry<>(key, manager));
		assertThrows(ManagerContextException.class, () -> this.managerContext.register(new DefaultInstanceEntry<>(key, manager)));

		Manager getManager = this.managerContext.get(FirstManager.class);
		assertNotNull(getManager);
		assertEquals(manager, getManager);

		assertThrows(ManagerContextException.class, () -> this.managerContext.get(DuplicatedFirstManager.class));
	}

	@Test
	public void testStart() {
		this.managerContext.start();
		this.managerContext.register(new DefaultInstanceEntry<>("managerKey", new FirstManager()));
		waitUntil(() -> this.managerContext.isAlive());
		assertNotNull(this.managerContext.getThread());
		this.managerContext.setRunning(false);
		assertFalse(this.managerContext.isAlive());

		this.managerContext = Mockito.mock(DefaultManagerContext.class);
		when(this.managerContext.getThread()).thenCallRealMethod();
		doCallRealMethod().when(this.managerContext).setThread(Thread.currentThread());
		doCallRealMethod().when(this.managerContext).start();

		this.managerContext.setThread(Thread.currentThread());
		this.managerContext.start();
	}

	@Test
	public void testStop() {
		Manager manager = new FirstManager();
		String key = ContextNameGenerator.generateKeyFor(manager);

		this.managerContext.register(new DefaultInstanceEntry<>(key, manager));
		this.managerContext.stop();
		assertNotNull(manager = this.managerContext.get(FirstManager.class));
		assertEquals(ManagerStatus.STOPPING, manager.getStatus());

		this.managerContext.touchManager(manager);
		assertEquals(ManagerStatus.STOPPED, manager.getStatus());
	}

	@Test
	public void testRestart() {
		Manager manager = new FirstManager();
		String key = ContextNameGenerator.generateKeyFor(manager);

		this.managerContext.register(new DefaultInstanceEntry<>(key, manager));
		this.managerContext.restart();
		assertNotNull(manager = this.managerContext.get(FirstManager.class));
		assertEquals(ManagerStatus.RESTARTING, manager.getStatus());

		this.managerContext.touchManager(manager);
		assertEquals(ManagerStatus.STARTING, manager.getStatus());

		this.managerContext.touchManager(manager);
		assertEquals(ManagerStatus.RUNNING, manager.getStatus());
	}

	@Test
	public void testFinish() {
		Manager manager = new FirstManager();
		String key = ContextNameGenerator.generateKeyFor(manager);

		this.managerContext.register(new DefaultInstanceEntry<>(key, manager));
		this.managerContext.finish();
		assertNotNull(manager = this.managerContext.get(FirstManager.class));
		assertEquals(ManagerStatus.FINISHING, manager.getStatus());

		this.managerContext.touchManager(manager);
		assertEquals(ManagerStatus.FINISHED, manager.getStatus());
	}

	@Test
	public void testThread() {
		this.managerContext.start();
		waitUntil(() -> this.managerContext.isAlive());
		assertNotNull(this.managerContext.thread());
		assertTrue(this.managerContext.thread().isAlive());
		this.managerContext.setRunning(false);
	}

	private static class FirstManager extends DefaultManager { public FirstManager() { this.setOrder(1); } }
	private static class SecondManager extends DefaultManager { public SecondManager() { this.setOrder(2); } }
	private static class ThirdManager extends DefaultManager { public ThirdManager() { this.setOrder(3); } }
	private static class ThirdManagerExtended extends ThirdManager { public ThirdManagerExtended() { this.setOrder(4); } }

	@Priority(-1)
	@Named("org.diverproject.scarlet.context.manager.DefaultManagerContextTest$FirstManager")
	private static class DuplicatedFirstManager extends DefaultManager {}

}
