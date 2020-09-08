package org.diverproject.scarlet.context.singleton;

import org.diverproject.scarlet.context.ScarletRuntimeContextException;
import org.diverproject.scarlet.language.Language;

public class SingletonException extends ScarletRuntimeContextException {

	private static final long serialVersionUID = -8848432057551724376L;

	public SingletonException(Language language) {
		super(language);
	}

	public SingletonException(Language language, Object... args) {
		super(language, args);
	}

	public SingletonException(Throwable e) {
		super(e);
	}

	public SingletonException(Throwable e, Language language) {
		super(e, language);
	}

	public SingletonException(Throwable e, Language language, Object... args) {
		super(e, language, args);
	}
}
