package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ReflectionException extends ScarletRuntimeException {

	private static final long serialVersionUID = 7413138144398893315L;

	public ReflectionException(Language language) {
		super(language);
	}

	public ReflectionException(Language language, Object... args) {
		super(language, args);
	}

	public ReflectionException(Throwable e) {
		super(e);
	}

	public ReflectionException(Throwable e, Language language) {
		super(e, language);
	}

	public ReflectionException(Throwable e, Language language, Object... args) {
		super(e, language, args);
	}
}
