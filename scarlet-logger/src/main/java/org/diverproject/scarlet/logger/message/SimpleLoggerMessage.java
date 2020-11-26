package org.diverproject.scarlet.logger.message;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.log4j.Level;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.slf4j.Marker;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SimpleLoggerMessage implements LoggerMessage {
	private LocalDateTime time;
	private Level level;
	private LoggerLevel loggerLevel;
	private String message;
	private Marker marker;
	private Throwable throwable;
	private Object[] arguments;
	private StackTraceElement source;
}
