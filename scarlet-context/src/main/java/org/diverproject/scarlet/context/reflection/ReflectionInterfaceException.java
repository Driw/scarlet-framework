package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ReflectionInterfaceException extends ScarletRuntimeException {

	private static final long serialVersionUID = -8250568674207752718L;

	public ReflectionInterfaceException(Language language, Object... args) {
		super(language, args);
	}

}
