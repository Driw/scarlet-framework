package org.diverproject.scarlet.logger;

public final class LoggerFactory {

	private LoggerFactory() { }

	public static Logger getDefault() {
		return ScarletLoggerParser.getDefault();
	}

	public static Logger get(Class<?> className) {
		return ScarletLoggerParser.get(className);
	}

}
