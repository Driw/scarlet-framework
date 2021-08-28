package org.diverproject.scarlet.context.manager;

import org.diverproject.scarlet.context.ScarletContextException;
import org.diverproject.scarlet.language.Language;

@SuppressWarnings("java:S110")
public class ManagerContextException extends ScarletContextException {

	private static final long serialVersionUID = -7554601589990856213L;

	public ManagerContextException(Language language, Object... args) {
		super(language, args);
	}
}
