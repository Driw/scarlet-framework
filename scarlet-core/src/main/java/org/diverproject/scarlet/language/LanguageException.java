package org.diverproject.scarlet.language;

import org.diverproject.scarlet.ScarletException;

public class LanguageException extends ScarletException {

	private static final long serialVersionUID = -6658438626365742664L;

	public LanguageException(Language language, Object... args) {
		super(language, args);
	}

	public LanguageException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}

}
