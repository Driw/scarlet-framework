package org.diverproject.scarlet.util.exceptions;

import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.UNKNOWN;

import org.diverproject.scarlet.language.Language;

public class HexadecimalUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public HexadecimalUtilsRuntimeException() {
		super(UNKNOWN);
	}

	public HexadecimalUtilsRuntimeException(Language language) {
		super(language);
	}

	public HexadecimalUtilsRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public HexadecimalUtilsRuntimeException(Exception e) {
		super(e);
	}

	public HexadecimalUtilsRuntimeException(Exception e, Language language) {
		super(e, language);
	}

	public HexadecimalUtilsRuntimeException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}
}
