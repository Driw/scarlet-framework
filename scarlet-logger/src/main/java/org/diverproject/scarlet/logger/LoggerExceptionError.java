package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.DEFAULT_CLASS_LOGGER_NOT_SET;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NOT_FOUND;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_INSTANCE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_LANGUAGE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.SCARLET_LOGGER_CLOSE;

import java.io.IOException;

public class LoggerExceptionError {

	public static LoggerRuntimeException defaultClassLoggerNotSet() {
		return new LoggerRuntimeException(DEFAULT_CLASS_LOGGER_NOT_SET);
	}

	public static LoggerRuntimeException newLoggerInstance(Exception e, String name, Class<? extends Logger> defaultClassLogger) {
		return new LoggerRuntimeException(e, NEW_LOGGER_INSTANCE, name, defaultClassLogger.getName());
	}

	public static LoggerRuntimeException loggerNotFound(String name) {
		return new LoggerRuntimeException(LOGGER_NOT_FOUND, name);
	}

	public static LoggerRuntimeException closeLogger(IOException e, Logger logger) {
		return new LoggerRuntimeException(e, SCARLET_LOGGER_CLOSE, logger.getName());
	}

	public static LoggerRuntimeException newLoggerInstanceAtAdd(Exception e, Class<? extends Logger> loggerClass) {
		return new LoggerRuntimeException(e, NEW_LOGGER_LANGUAGE, loggerClass.getName());
	}

}
