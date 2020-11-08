package org.diverproject.scarlet.context;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ScarletContextException extends ScarletRuntimeException {

	public ScarletContextException(Exception e) {
		super(e);
	}

	public ScarletContextException(Language language) {
		super(language);
	}

	public ScarletContextException(Language language, Object... args) {
		super(language, args);
	}

}
