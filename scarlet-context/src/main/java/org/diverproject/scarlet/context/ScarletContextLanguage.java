package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum ScarletContextLanguage implements Language {

	// -- Loggers

	REPLACING_CONTEXT_INSTANCE					("replacing a new context instance of '%s' (oldInstance: %s, newInstance: %s)"),
	SINGLETON_INSTANCE_REGISTERED				("singleton instance created and registered with successfully (key: %s, singletonInstance: %s)"),
	MANAGER_INSTANCE_REGISTERED					("manager instance created and registered with successfully (key: %s, managerInstance: %s)"),

	// -- Exception

	ALREADY_INITIALIZED						("the context already initialized"),
	LOGGER_INSTANCE_OF_AT_GET_LOGGER		("cannot get manufacture a logger by Context Logger Factory (loggerType: %s, loggerClass: %s)"),
	GET_INSTANCE_CANNOT_CAST				("cannot cast the get the context instance as informed (key: %s, contextInstance: %s, requestedType: %s)"),

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
