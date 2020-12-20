package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum ScarletContextLanguage implements Language {

	// -- Loggers

	REPLACING_CONTEXT_INSTANCE				("replacing a new context instance of '%s' (oldInstance: %s, newInstance: %s)"),
	SINGLETON_INSTANCE_REGISTERED			("singleton instance created and registered with successfully (key: %s, singletonInstance: %s)"),
	MANAGER_INSTANCE_REGISTERED				("manager instance created and registered with successfully (key: %s, managerInstance: %s)"),
	INJECT_OBJECT							("starting injection of {}"),
	INJECT_FIELD							("injecting value of {}.{} as {} type"),

	// -- Exception

	ALREADY_INITIALIZED						("the context already initialized"),
	GET_INSTANCE_CANNOT_CAST				("cannot cast the get the context instance as informed (key: %s, contextInstance: %s, requestedType: %s)"),
	INJECTABLE_FIELD_NOT_IMPLEMENTED		("injectable field is a interface not implemented (object: %s, field: %s, fieldType: %s"),

	;

	@Getter
	@Setter
	private String format;

	ScarletContextLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
