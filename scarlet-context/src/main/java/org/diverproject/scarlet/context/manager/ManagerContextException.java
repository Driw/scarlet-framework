package org.diverproject.scarlet.context.manager;

import org.diverproject.scarlet.context.ScarletRuntimeContextException;
import org.diverproject.scarlet.language.Language;

public class ManagerContextException extends ScarletRuntimeContextException {

	private static final long serialVersionUID = -7554601589990856213L;

	public ManagerContextException(Language language) {
		super(language);
	}

	public ManagerContextException(Language language, Object... args) {
		super(language, args);
	}

	public ManagerContextException(Throwable e) {
		super(e);
	}

	public ManagerContextException(Throwable e, Language language) {
		super(e, language);
	}

	public ManagerContextException(Throwable e, Language language, Object... args) {
		super(e, language, args);
	}
}
