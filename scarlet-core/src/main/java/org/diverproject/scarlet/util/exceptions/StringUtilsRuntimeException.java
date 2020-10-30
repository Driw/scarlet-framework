package org.diverproject.scarlet.util.exceptions;

import org.diverproject.scarlet.language.Language;

public class StringUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public StringUtilsRuntimeException(Language language) {
		super(language);
	}

	public StringUtilsRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public StringUtilsRuntimeException(Exception e) {
		super(e);
	}

}
