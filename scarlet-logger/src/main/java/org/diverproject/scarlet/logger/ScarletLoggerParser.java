package org.diverproject.scarlet.logger;

import lombok.Data;
import org.slf4j.LoggerFactory;

@Data
public final class ScarletLoggerParser {

	private static boolean initialized;
	private static final String DEFAULT_LOGGER_NAME = Logger.class.getName();
	private static Logger defaultLogger;

	static {
		ScarletLoggerParser.initialize();
	}

	private ScarletLoggerParser() { }

	private static void initialize() {
	}

	public static Logger get(Class<?> className) {
		org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(className);

		if (!(slf4jLogger instanceof Logger)) {
			throw LoggerError.getLoggerInstanceOf(slf4jLogger);
		}

		return (Logger) slf4jLogger;
	}

	public static Logger getDefault() {
		return defaultLogger;
	}

}
