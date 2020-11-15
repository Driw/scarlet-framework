package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.DEFAULT_CLASS_LOGGER_NOT_SET;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NOT_FOUND;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_INSTANCE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_LANGUAGE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.SCARLET_LOGGER_CLOSE;

import java.io.IOException;

public final class LoggerError {

	private LoggerError() { }

	public static LoggerException defaultClassLoggerNotSet() {
		return new LoggerException(DEFAULT_CLASS_LOGGER_NOT_SET);
	}

	public static LoggerException newLoggerInstance(Exception e, String name, Class<? extends Logger> defaultClassLogger) {
		return new LoggerException(e, NEW_LOGGER_INSTANCE, name, defaultClassLogger.getName());
	}

	public static LoggerException loggerNotFound(String name) {
		return new LoggerException(LOGGER_NOT_FOUND, name);
	}

	public static LoggerException closeLogger(IOException e, Logger logger) {
		return new LoggerException(e, SCARLET_LOGGER_CLOSE, logger.getName());
	}

	public static LoggerException newLoggerInstanceAtAdd(Exception e, Class<? extends Logger> loggerClass) {
		return new LoggerException(e, NEW_LOGGER_LANGUAGE, loggerClass.getName());
	}

}
