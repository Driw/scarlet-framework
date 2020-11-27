package org.diverproject.scarlet.logger;

import org.slf4j.LoggerFactory;

public final class ScarletLoggerParser {

	public static final String DEFAULT_LOGGER_NAME = Logger.class.getName();

	private static final Logger defaultLogger;

	static {
		defaultLogger = get(DEFAULT_LOGGER_NAME);
	}

	private ScarletLoggerParser() { }

	public static Logger get(Class<?> className) {
		return (Logger) LoggerFactory.getLogger(className);
	}

	public static Logger get(String name) {
		return (Logger) LoggerFactory.getLogger(name);
	}

	public static Logger getDefault() {
		return defaultLogger;
	}

}
