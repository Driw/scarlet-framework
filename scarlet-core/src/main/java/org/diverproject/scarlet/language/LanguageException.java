package org.diverproject.scarlet.language;

import org.diverproject.scarlet.ScarletException;

public class LanguageException extends ScarletException {

	private static final long serialVersionUID = -6658438626365742664L;

	private Language language;

	public LanguageException(Language language) {
		super(language);
	}

	public LanguageException(Language language, Object... args) {
		super(language, args);
	}

	public LanguageException(Exception e) {
		super(e);
	}

	public LanguageException(Exception e, Language language) {
		super(e, language);
	}

	public LanguageException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}

	@Override
	public int getCode() {
		return this.language.getCode();
	}
}
