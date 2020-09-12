package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum ScarletContextLanguage implements Language {

	// -- Loggers

	SINGLETON_INSTANCE_REGISTERED			("singleton instance created and registered with successfully (singletonKey: %s, singletonInstance: %s)"),
	REPLACING_OLD_SINGLETON_INSTANCE		("replacing a new singleton instance for '%s' singleton key (oldInstance: %s, newInstance: %s)"),

	// -- Exception

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
