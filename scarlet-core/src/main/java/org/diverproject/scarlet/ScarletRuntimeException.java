package org.diverproject.scarlet;

import org.diverproject.scarlet.language.Language;

public class ScarletRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	private Language language;

	public ScarletRuntimeException(Language language) {
		super(language.getFormat());

		this.setLanguage(language);
	}

	public ScarletRuntimeException(Language language, Object... args) {
		super(String.format(language.getFormat(), args));

		this.setLanguage(language);
	}

	public ScarletRuntimeException(Throwable e) {
		super(e.getMessage());

		this.setStackTrace(e.getStackTrace());
	}

	public ScarletRuntimeException(Throwable e, Language language) {
		super(language.getFormat());

		this.setLanguage(language);
		this.setStackTrace(e.getStackTrace());
	}

	public ScarletRuntimeException(Throwable e, Language language, Object... args) {
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
