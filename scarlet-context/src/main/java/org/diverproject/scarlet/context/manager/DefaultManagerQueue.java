package org.diverproject.scarlet.context.manager;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.utils.ContextCollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class DefaultManagerQueue implements ManagerQueue {

	public static final int NO_ORDER = 0;

	private List<Manager> managers;
	private boolean uniqueOrder;

	public DefaultManagerQueue() {
		this.setManagers(new ArrayList<>());
		this.setUniqueOrder(false);
	}

	@Override
	public boolean add(Manager manager) {
		if (this.getManagers().contains(manager)) {
			throw ManagerContextError.managerAlreadyAdded(manager);
		}

		if (this.isUniqueOrder()) {
			if (this.hasOrderUsed(manager.getOrder())) {
				throw ManagerContextError.managerOrderUsed(manager);
			}
		}

		return this.findOrderIndex(manager)
			.map(index -> this.addWithPrevious(index, manager))
			.orElseGet(() -> this.addWithoutPrevious(manager));
	}

	private boolean addWithPrevious(int index, Manager manager) {
		this.getManagers().add(index, manager);
		return this.getManagers().contains(manager);
	}

	private boolean addWithoutPrevious(Manager manager) {
		if (manager.getOrder() == NO_ORDER) {
			this.getManagers().add(manager);
			return this.getManagers().get(this.getManagers().size() - 1).equals(manager);
		} else {
			this.getManagers().add(0, manager);
			return this.getManagers().get(0).equals(manager);
		}
	}

	public Optional<Integer> findOrderIndex(Manager manager) {
		return ContextCollectionUtils.searchOrderIndex(this.getManagers(), (previous, next) -> this.hasOrderBetween(previous, next, manager));
	}

	public boolean hasOrderBetween(Manager previousManager, Manager nextManager, Manager manager) {
		if (Objects.isNull(previousManager) && Objects.isNull(nextManager)) {
			return false;
		}

		if (Objects.isNull(previousManager)) {
			return manager.compareTo(nextManager) < 0;
		}

		if (Objects.isNull(nextManager)) {
			throw new IllegalArgumentException();
		}

		return manager.compareTo(previousManager) > 0 && manager.compareTo(nextManager) < 0;
	}

	public boolean hasOrderUsed(int order) {
		if (order == NO_ORDER) {
			return false;
		}

		return this.getManagers().stream()
			.anyMatch(manager -> manager.getOrder() == order);
	}

	@Override
	public Iterator<Manager> iterator() {
		return this.getManagers().iterator();
	}
}
