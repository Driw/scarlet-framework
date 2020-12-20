package org.diverproject.scarlet.context.manager;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;

public enum ManagerContextLanguage implements Language {

	// -- Logger

	MANAGER_REGISTERED_SUCCESSFULLY	("a new manager was registered with successfully (key: %s, manager: %s)"),
	MANAGER_REPLACED_BY_ANOTHER		("the manager key is already used, manager replaced (keY: %s, newManager: %s, oldManager: %s)"),

	// -- Exception

	MANAGER_ALREADY_ADDED			("manager already added to manager queue (manager: %s, order: %d)"),
	MANAGER_ORDER_USED				("manager order used by another manager (order: %d, managerAdding: %s)"),
	INVALID_MANAGER_INSTANCE_OF		("the manager found cannot be casted (managerClass: %s, key: %s, managerFound: %s)"),
	INJECT_MANAGER_INSTANCE_OF		("invalid injection of manager instance created (object: %s, field: %s, valueType: %s)"),

	;

	@Getter
	@Setter
	private String format;

	ManagerContextLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
