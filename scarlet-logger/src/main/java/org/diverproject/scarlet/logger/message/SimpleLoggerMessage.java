package org.diverproject.scarlet.logger.message;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.slf4j.Marker;

@Data
@Accessors(chain = true)
public class SimpleLoggerMessage implements LoggerMessage {
	private LoggerLevel loggerLevel;
	private String message;
	private Marker marker;
	private Throwable throwable;
	private Object[] arguments;
}
