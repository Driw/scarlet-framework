package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.LoggerLanguage;
import org.diverproject.scarlet.logger.ScarletLoggers;

public class LoggerFactory {

	private static LoggerBuilder loggerBuilder = new DefaultLoggerBuilder();

	public static LoggerLanguage get(Class<?> aClass) {
		if (ScarletLoggers.getInstance().contains(aClass.getName())) {
			return ScarletLoggers.getInstance().get(aClass.getName());
		}

		return ScarletLoggers.getInstance().add(loggerBuilder.generateKey(aClass), loggerBuilder.generateLoggerClass(aClass));
	}

	public static LoggerBuilder getLoggerBuilder() {
		return loggerBuilder;
	}

	public static void setLoggerBuilder(LoggerBuilder loggerBuilder) {
		LoggerFactory.loggerBuilder = loggerBuilder;
	}
}
