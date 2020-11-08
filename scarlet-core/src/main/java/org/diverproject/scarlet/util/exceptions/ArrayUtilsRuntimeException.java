package org.diverproject.scarlet.util.exceptions;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ArrayUtilsRuntimeException extends ScarletRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public ArrayUtilsRuntimeException(Language language) {
		super(language);
	}

}
