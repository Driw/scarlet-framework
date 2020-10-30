package org.diverproject.scarlet;

import org.diverproject.scarlet.language.Language;

public class ScarletException extends Exception {

	private static final long serialVersionUID = -6658438626365742664L;

	private transient Language language;

	public ScarletException(Language language) {
		super(language.getFormat());

		this.setLanguage(language);
	}

	public ScarletException(Language language, Object... args) {
		super(String.format(language.getFormat(), args));

		this.setLanguage(language);
	}

	public ScarletException(Throwable e) {
		super(e.getMessage());

		this.setStackTrace(e.getStackTrace());
	}

	public ScarletException(Throwable e, Language language) {
		super(language.getFormat());

		this.setLanguage(language);
		this.setStackTrace(e.getStackTrace());
	}

	public ScarletException(Throwable e, Language language, Object... args) {
		super(String.format(language.getFormat(), args) + "; " + e.getClass().getSimpleName() + " - " + e.getMessage());

		this.setLanguage(language);
		this.setStackTrace(e.getStackTrace());
	}

	private void setLanguage(Language language) {
		this.language = language;
	}

	public int getCode() {
		return language.getCode();
	}
}
