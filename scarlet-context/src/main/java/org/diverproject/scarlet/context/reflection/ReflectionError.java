package org.diverproject.scarlet.context.reflection;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;

public enum ReflectionError implements Language {

	EMPTY_CONSTRUCTOR_EXCEPTION						("failure on get the empty constructor (className: %s)"),
	FAILURE_ON_CREATE_A_INSTANCE					("failure on create a instance (className: %s)");

	@Getter
	@Setter
	private String format;

	ReflectionError(String format) {
		this.format = format;
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
