package org.diverproject.scarlet.context;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ScarletRuntimeContextException extends ScarletRuntimeException {

	public ScarletRuntimeContextException(Language language) {
		super(language);
	}

	public ScarletRuntimeContextException(Language language, Object... args) {
		super(language, args);
	}

	public ScarletRuntimeContextException(Throwable e) {
		super(e);
	}

	public ScarletRuntimeContextException(Throwable e, Language language) {
		super(e, language);
	}

	public ScarletRuntimeContextException(Throwable e, Language language, Object... args) {
		super(e, language, args);
	}
}
