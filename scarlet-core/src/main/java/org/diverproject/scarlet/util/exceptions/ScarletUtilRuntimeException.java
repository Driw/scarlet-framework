package org.diverproject.scarlet.util.exceptions;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class ScarletUtilRuntimeException extends ScarletRuntimeException {

	private static final long serialVersionUID = 4439419903823149948L;

	public ScarletUtilRuntimeException(Language language) {
		super(language);
	}

	public ScarletUtilRuntimeException(Language language, Object... args) {
		super(language, args);
	}

	public ScarletUtilRuntimeException(Exception e) {
		super(e);
	}

	public ScarletUtilRuntimeException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}

}
