package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ReflectionInterfaceException extends ScarletRuntimeException {

	private static final long serialVersionUID = -8250568674207752718L;

	public ReflectionInterfaceException(Language language) {
		super(language);
	}

	public ReflectionInterfaceException(Language language, Object... args) {
		super(language, args);
	}

	public ReflectionInterfaceException(Throwable e) {
		super(e);
	}

	public ReflectionInterfaceException(Throwable e, Language language) {
		super(e, language);
	}

	public ReflectionInterfaceException(Throwable e, Language language, Object... args) {
		super(e, language, args);
	}
}
