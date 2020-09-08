package org.diverproject.scarlet.context.singleton;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;

public enum SingletonLanguage implements Language {

	// -- Loggers

	REPLACING_OLD_SINGLETON_INSTANCE		("replacing a new singleton instance for '%s' singleton key (oldInstance: %s, newInstance: %s)"),
	SINGLETON_INSTANCE_REGISTERED			("singleton instance created and registered with successfully (singletonKey: %s, singletonInstance: %s)"),

	// -- Exceptions

	SINGLETON_NOT_FOUND						("singleton not found (key: %s, className: %s)"),
	CANNOT_CAST_SINGLETON					("cannot cast singleton instance into singleton class informed (singletonInstance: %s, singletonClass: %s)"),
	NOT_A_SINGLETON_CLASS					("class informed not implement @Singleton annotation (className: %s)"),
	SINGLETON_CLASS_NOT_ANNOTATED			("cannot create a singleton instance of a class without @Singleton annotation (className: %s)"),

	;

	@Getter
	@Setter
	private String format;

	SingletonLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
