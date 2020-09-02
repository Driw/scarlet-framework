package org.diverproject.scarlet.util.exceptions;

import org.diverproject.scarlet.language.Language;

public class ArrayUtilsRuntimeException extends ScarletUtilRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public ArrayUtilsRuntimeException(Language language) {
		super(language);
	}

	public ArrayUtilsRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public ArrayUtilsRuntimeException(Exception e) {
		super(e);
	}

	public ArrayUtilsRuntimeException(Exception e, Language language) {
		super(e, language);
	}

	public ArrayUtilsRuntimeException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}
}
