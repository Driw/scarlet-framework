package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.simple.SimpleLogger;

public class DefaultLoggerBuilder implements LoggerBuilder {

	@Override
	public String generateKey(Class<?> aClass) {
		return aClass.getName();
	}

	@Override
	public Class<? extends Logger> generateLoggerClass(Class<?> aClass) {
		return SimpleLogger.class;
	}

}
