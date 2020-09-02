package org.diverproject.scarlet.util.exceptions;

import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.UNKNOWN;

import org.diverproject.scarlet.util.language.BinaryUtilsLanguage;

public class BinaryUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public BinaryUtilsRuntimeException() {
		super(UNKNOWN);
	}

	public BinaryUtilsRuntimeException(BinaryUtilsLanguage language) {
		super(language);
	}

	public BinaryUtilsRuntimeException(BinaryUtilsLanguage language, Object... args) {
		super(language, args);
	}

	public BinaryUtilsRuntimeException(Exception e) {
		super(e);
	}

	public BinaryUtilsRuntimeException(Exception e, BinaryUtilsLanguage language) {
		super(e, language);
	}

	public BinaryUtilsRuntimeException(Exception e, BinaryUtilsLanguage language, Object... args) {
		super(e, language, args);
	}
}
