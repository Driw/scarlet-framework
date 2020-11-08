package org.diverproject.scarlet.language;

import org.diverproject.scarlet.ScarletRuntimeException;

public class LanguageRuntimeException extends ScarletRuntimeException {

	public LanguageRuntimeException(Language language, Object... args) {
		super(language, args);
	}

}
