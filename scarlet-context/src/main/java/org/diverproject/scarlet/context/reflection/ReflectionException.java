package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ReflectionException extends ScarletRuntimeException {

	private static final long serialVersionUID = 7413138144398893315L;

	public ReflectionException(Language language, Object... args) {
		super(language, args);
	}

}
