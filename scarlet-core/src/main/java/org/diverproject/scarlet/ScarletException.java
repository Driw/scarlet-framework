package org.diverproject.scarlet;

import lombok.Getter;
import org.diverproject.scarlet.language.Language;

public class ScarletException extends Exception {

	private static final long serialVersionUID = -6658438626365742664L;

	@Getter
	private final transient Language language;

	public ScarletException(Language language, Object... args) {
		super(String.format(language.getFormat(), args));

		this.language = language;
	}

	public ScarletException(Throwable e, Language language, Object... args) {
		super(String.format(language.getFormat(), args) + "; " + e.getClass().getSimpleName() + " - " + e.getMessage());

		this.language = language;
		this.setStackTrace(e.getStackTrace());
	}

}
