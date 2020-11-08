package org.diverproject.scarlet.context.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultManagerTest {

	private DefaultManager manager;

	@BeforeEach
	public void beforeEach() {
		this.manager = new DefaultManager();
	}

	@Test
	public void testTick() {
		assertEquals(ManagerStatus.IDLE, this.manager.getStatus());

		this.manager.tick();
		assertEquals(ManagerStatus.IDLE, this.manager.getStatus());

		this.manager.start();
		this.manager.tick();
		assertEquals(ManagerStatus.RUNNING, this.manager.getStatus());

		this.manager.restart();
		this.manager.tick();
		assertEquals(ManagerStatus.STARTING, this.manager.getStatus());

		this.manager.stop();
		this.manager.tick();
		assertEquals(ManagerStatus.STOPPED, this.manager.getStatus());

		this.manager.finish();
		this.manager.tick();
		assertEquals(ManagerStatus.FINISHED, this.manager.getStatus());
	}

	@Test
	public void testStart() {
		this.manager.start();
		assertEquals(ManagerStatus.STARTING, this.manager.getStatus());
		this.manager.tick();
		assertEquals(ManagerStatus.RUNNING, this.manager.getStatus());
		this.manager.tick();
	}

	@Test
	public void testRestart() {
		this.manager.restart();
		assertEquals(ManagerStatus.RESTARTING, this.manager.getStatus());
	}

	@Test
	public void testStop() {
		this.manager.stop();
		assertEquals(ManagerStatus.STOPPING, this.manager.getStatus());
	}

	@Test
	public void testFinish() {
		this.manager.finish();
		assertEquals(ManagerStatus.FINISHING, this.manager.getStatus());
	}

}
