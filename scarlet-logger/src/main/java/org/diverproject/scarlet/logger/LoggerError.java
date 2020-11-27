package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.logger.language.LoggerLanguage;

import java.io.IOException;

public final class LoggerError {

	private LoggerError() { }

	public static LoggerException closeLogger(IOException e, Logger logger) {
		return new LoggerException(e, LoggerLanguage.SCARLET_LOGGER_CLOSE, logger.getName());
	}

}
