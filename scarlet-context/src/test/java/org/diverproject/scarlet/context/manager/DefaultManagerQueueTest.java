package org.diverproject.scarlet.context.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Optional;

public class DefaultManagerQueueTest {

	private DefaultManagerQueue managerQueue;

	@BeforeEach
	public void beforeEach() {
		this.managerQueue = new DefaultManagerQueue();
	}

	@Test
	public void testUniqueOrder() {
		this.managerQueue.setUniqueOrder(false);
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(1)));
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(1)));

		this.managerQueue.setUniqueOrder(true);
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(2)));
		assertThrows(ManagerContextException.class, () -> this.managerQueue.add(new DefaultManager().setOrder(1)));
		assertThrows(ManagerContextException.class, () -> this.managerQueue.add(new DefaultManager().setOrder(2)));
	}

	@Test
	public void testAdd() {
		Manager manager = new DefaultManager();
		assertTrue(this.managerQueue.add(manager));
		assertThrows(ManagerContextException.class, () -> this.managerQueue.add(manager));
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(1)));
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(1)));
	}

	@Test
	public void testFindOrderIndex() {
		Manager thirdManagerOrder = new DefaultManager().setOrder(3);
		Manager secondManagerOrder = new DefaultManager().setOrder(2);
		Manager firstManagerOrder = new DefaultManager().setOrder(1);
		Manager firstManagerNoOrder = new DefaultManager();
		Manager secondManagerNoOrder = new DefaultManager();

		Optional<Integer> nextThirdManagerOrder = this.managerQueue.findOrderIndex(thirdManagerOrder);
		assertTrue(nextThirdManagerOrder.isEmpty());
		assertTrue(this.managerQueue.add(thirdManagerOrder));

		Optional<Integer> nextFirstManagerOrder = this.managerQueue.findOrderIndex(firstManagerOrder);
		assertFalse(nextFirstManagerOrder.isEmpty());
		assertEquals(0, nextFirstManagerOrder.get());
		assertTrue(this.managerQueue.add(firstManagerOrder));

		Optional<Integer> nextSecondManagerOrder = this.managerQueue.findOrderIndex(secondManagerOrder);
		assertFalse(nextSecondManagerOrder.isEmpty());
		assertEquals(1, nextSecondManagerOrder.get());
		assertTrue(this.managerQueue.add(secondManagerOrder));

		Optional<Integer> nextFirstManagerNoOrder = this.managerQueue.findOrderIndex(firstManagerNoOrder);
		assertTrue(nextFirstManagerNoOrder.isEmpty());
		assertTrue(this.managerQueue.add(firstManagerNoOrder));

		Optional<Integer> nextSecondManagerNoOrder = this.managerQueue.findOrderIndex(firstManagerNoOrder);
		assertTrue(nextSecondManagerNoOrder.isEmpty());
		assertTrue(this.managerQueue.add(secondManagerNoOrder));
	}

	@Test
	public void testHasOrderBetween() {
		assertThrows(IllegalArgumentException.class, () -> this.managerQueue.hasOrderBetween(new DefaultManager(), null, newDefaultManager(1)));

		assertFalse(this.managerQueue.hasOrderBetween(null, new DefaultManager(), new DefaultManager()));
		assertFalse(this.managerQueue.hasOrderBetween(new DefaultManager(), new DefaultManager(), new DefaultManager()));

		assertFalse(this.managerQueue.hasOrderBetween(null, newDefaultManager(2), new DefaultManager()));
		assertFalse(this.managerQueue.hasOrderBetween(null, newDefaultManager(2), newDefaultManager(3)));
		assertFalse(this.managerQueue.hasOrderBetween(null, newDefaultManager(2), newDefaultManager(2)));
		assertTrue(this.managerQueue.hasOrderBetween(null, newDefaultManager(2), newDefaultManager(1)));

		assertFalse(this.managerQueue.hasOrderBetween(newDefaultManager(2), newDefaultManager(4), newDefaultManager(1)));
		assertFalse(this.managerQueue.hasOrderBetween(newDefaultManager(2), newDefaultManager(4), newDefaultManager(2)));
		assertTrue(this.managerQueue.hasOrderBetween(newDefaultManager(2), newDefaultManager(4), newDefaultManager(3)));
		assertFalse(this.managerQueue.hasOrderBetween(newDefaultManager(2), newDefaultManager(4), newDefaultManager(4)));
		assertFalse(this.managerQueue.hasOrderBetween(newDefaultManager(2), newDefaultManager(4), newDefaultManager(5)));
	}

	@Test
	public void testHasOrderUsed() {
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(DefaultManagerQueue.NO_ORDER)));
		assertFalse(this.managerQueue.hasOrderUsed(DefaultManagerQueue.NO_ORDER));

		assertFalse(this.managerQueue.hasOrderUsed(1));
		assertTrue(this.managerQueue.add(new DefaultManager().setOrder(1)));
		assertTrue(this.managerQueue.hasOrderUsed(1));
	}

	@Test
	public void testIterator() {
		Manager thirdManagerOrder = new DefaultManager().setOrder(3);
		Manager secondManagerOrder = new DefaultManager().setOrder(2);
		Manager firstManagerOrder = new DefaultManager().setOrder(1);
		Manager firstManagerNoOrder = new DefaultManager();
		Manager secondManagerNoOrder = new DefaultManager();

		Iterator<Manager> iterator = this.managerQueue.iterator();
		assertFalse(iterator.hasNext());

		assertTrue(this.managerQueue.add(thirdManagerOrder));
		assertTrue(this.managerQueue.add(firstManagerOrder));
		assertTrue(this.managerQueue.add(secondManagerOrder));
		assertTrue(this.managerQueue.add(firstManagerNoOrder));
		assertTrue(this.managerQueue.add(secondManagerNoOrder));

		iterator = this.managerQueue.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(firstManagerOrder, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(secondManagerOrder, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(thirdManagerOrder, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(firstManagerNoOrder, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(secondManagerNoOrder, iterator.next());
		assertFalse(iterator.hasNext());
	}

	private static DefaultManager newDefaultManager(int order) {
		return new DefaultManager()
			.setOrder(order);
	}

}
