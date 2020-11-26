package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import org.diverproject.scarlet.logger.language.LoggerLanguage;

import java.io.IOException;

public final class LoggerError {

	private LoggerError() { }

	public static LoggerException closeLogger(IOException e, Logger logger) {
		return new LoggerException(e, LoggerLanguage.SCARLET_LOGGER_CLOSE, logger.getName());
	}

	public static LoggerException getLoggerInstanceOf(org.slf4j.Logger slf4jLogger) {
		return new LoggerException(LoggerLanguage.GET_LOGGER_INSTANCE_OF, nameOf(Logger.class), nameOf(slf4jLogger));
	}

}
