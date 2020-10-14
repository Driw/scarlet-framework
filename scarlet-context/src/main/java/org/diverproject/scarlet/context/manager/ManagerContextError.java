package org.diverproject.scarlet.context.manager;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

public class ManagerContextError {

	private ManagerContextError() {
	}

	public static ManagerContextException managerAlreadyAdded(Manager manager) {
		return new ManagerContextException(ManagerContextLanguage.MANAGER_ALREADY_ADDED, nameOf(manager), manager.getOrder());
	}

	public static ManagerContextException managerOrderUsed(Manager managerAdding) {
		return new ManagerContextException(ManagerContextLanguage.MANAGER_ORDER_USED, managerAdding.getOrder(), nameOf(managerAdding));
	}

	public static <T> ManagerContextException invalidManagerInstanceOf(Class<T> managerClass, String key, Manager manager) {
		return new ManagerContextException(ManagerContextLanguage.INVALID_MANAGER_INSTANCE_OF, nameOf(managerClass), key, nameOf(manager));
	}
}
