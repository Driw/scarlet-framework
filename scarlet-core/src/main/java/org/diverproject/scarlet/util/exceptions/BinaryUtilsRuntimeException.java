package org.diverproject.scarlet.util.exceptions;

import org.diverproject.scarlet.util.language.BinaryUtilsLanguage;

public class BinaryUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public BinaryUtilsRuntimeException(BinaryUtilsLanguage language) {
		super(language);
	}

	public BinaryUtilsRuntimeException(BinaryUtilsLanguage language, Object... args) {
		super(language, args);
	}

}
