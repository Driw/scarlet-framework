package org.diverproject.scarlet.context.singleton;

import org.diverproject.scarlet.context.ScarletContextException;
import org.diverproject.scarlet.language.Language;

@SuppressWarnings("java:S110")
public class SingletonException extends ScarletContextException {

	private static final long serialVersionUID = -8848432057551724376L;

	public SingletonException(Language language, Object... args) {
		super(language, args);
	}
}
