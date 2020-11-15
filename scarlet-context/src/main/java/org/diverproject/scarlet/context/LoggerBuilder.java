package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.Logger;

public interface LoggerBuilder {
	String generateKey(Class<?> aClass);
	Class<? extends Logger> generateLoggerClass(Class<?> aClass);
}
