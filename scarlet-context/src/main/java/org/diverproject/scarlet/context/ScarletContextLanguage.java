package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum ScarletContextLanguage implements Language {

	// -- Loggers

	REPLACING_CONTEXT_INSTANCE				("replacing a new context instance of '{}' (oldInstance: {}, newInstance: {})"),
	SINGLETON_INSTANCE_REGISTERED			("singleton instance created and registered with successfully (key: {}, singletonInstance: {})"),
	MANAGER_INSTANCE_REGISTERED				("manager instance created and registered with successfully (key: {}, managerInstance: {})"),
	INJECT_OBJECT							("starting injection of {}"),
	INJECT_FIELD							("injecting value of {}.{} as {} type"),

	// -- Exception

	ALREADY_INITIALIZED						("the context already initialized"),
	GET_INSTANCE_CANNOT_CAST				("cannot cast the get the context instance as informed (key: {}, contextInstance: {}, requestedType: {})"),
	INJECTABLE_FIELD_NOT_IMPLEMENTED		("injectable field is a interface not implemented (object: {}, field: {}, fieldType: {}"),

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
