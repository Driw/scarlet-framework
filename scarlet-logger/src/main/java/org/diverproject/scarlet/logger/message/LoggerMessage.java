package org.diverproject.scarlet.logger.message;

import org.diverproject.scarlet.logger.LoggerLevel;

public interface LoggerMessage {
	LoggerLevel getLoggerLevel();
	String getMessage();
	Throwable getThrowable();
	Object[] getArguments();
}
