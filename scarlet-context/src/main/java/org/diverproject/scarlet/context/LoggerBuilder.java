package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.LoggerLanguage;

public interface LoggerBuilder {
	String generateKey(Class<?> aClass);
	Class<? extends LoggerLanguage> generateLoggerClass(Class<?> aClass);
}
