package org.diverproject.scarlet.util.exceptions;

import static org.diverproject.scarlet.util.language.StringUtilsLanguage.STRING_UTILS_MAX_ERROR;

import org.diverproject.scarlet.language.Language;

public class NumberUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public NumberUtilsRuntimeException() {
		super(STRING_UTILS_MAX_ERROR);
	}

	public NumberUtilsRuntimeException(Language language) {
		super(language);
	}

	public NumberUtilsRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public NumberUtilsRuntimeException(Exception e) {
		super(e);
	}

	public NumberUtilsRuntimeException(Exception e, Language language) {
		super(e, language);
	}

	public NumberUtilsRuntimeException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}
}
