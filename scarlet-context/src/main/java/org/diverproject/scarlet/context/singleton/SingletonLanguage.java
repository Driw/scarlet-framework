package org.diverproject.scarlet.context.singleton;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;

public enum SingletonLanguage implements Language {

	// -- Exceptions

	SINGLETON_NOT_FOUND						("singleton not found (key: %s, className: %s)"),
	CANNOT_CAST_SINGLETON					("cannot cast singleton instance into singleton class informed (singletonInstance: %s, singletonClass: %s)"),
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
