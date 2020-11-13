package org.diverproject.scarlet;

import lombok.Getter;
import org.diverproject.scarlet.language.Language;

public class ScarletRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	@Getter
	private final transient Language language;

	public ScarletRuntimeException(Language language) {
		super(language.getFormat());

		this.language = language;
	}

	public ScarletRuntimeException(Language language, Object... args) {
		super(String.format(language.getFormat(), args));

		this.language = language;
	}

	public ScarletRuntimeException(Throwable e) {
		super(e.getMessage());

		this.language = null;
		this.setStackTrace(e.getStackTrace());
	}

	public ScarletRuntimeException(Throwable e, Language language, Object... args) {
		super(String.format(language.getFormat(), args) + "; " + e.getClass().getSimpleName() + " - " + e.getMessage());

		this.language = language;
		this.setStackTrace(e.getStackTrace());
	}

}
