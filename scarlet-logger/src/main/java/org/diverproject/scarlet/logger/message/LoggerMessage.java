package org.diverproject.scarlet.logger.message;

import org.apache.log4j.Level;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.slf4j.Marker;

import java.time.LocalDateTime;

public interface LoggerMessage {
	LocalDateTime getTime();
	Level getLevel();
	LoggerLevel getLoggerLevel();
	String getMessage();
	Marker getMarker();
	Throwable getThrowable();
	Object[] getArguments();
	StackTraceElement getSource();
}
