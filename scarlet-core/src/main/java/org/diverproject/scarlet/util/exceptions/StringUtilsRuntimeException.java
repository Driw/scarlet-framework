package org.diverproject.scarlet.util.exceptions;

import static org.diverproject.scarlet.util.language.StringUtilsLanguage.STRING_UTILS_MAX_ERROR;

import org.diverproject.scarlet.language.Language;

public class StringUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public StringUtilsRuntimeException() {
		super(STRING_UTILS_MAX_ERROR);
	}

	public StringUtilsRuntimeException(Language language) {
		super(language);
	}

	public StringUtilsRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public StringUtilsRuntimeException(Exception e) {
		super(e);
	}

	public StringUtilsRuntimeException(Exception e, Language language) {
		super(e, language);
	}

	public StringUtilsRuntimeException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}
}
