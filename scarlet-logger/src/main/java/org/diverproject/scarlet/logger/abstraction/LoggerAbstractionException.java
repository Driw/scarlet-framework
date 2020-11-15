package org.diverproject.scarlet.logger.abstraction;

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.LoggerException;

public class LoggerAbstractionException extends LoggerException {

	private static final long serialVersionUID = 331117194091235954L;

	public LoggerAbstractionException(Language language, Object... args) {
		super(language, args);
	}

	public LoggerAbstractionException(Exception e, Language language, Object... args) {
		super(e, language, args);
	}

}
