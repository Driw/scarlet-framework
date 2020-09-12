package org.diverproject.scarlet.context;

public interface LoggerBuilder {
	String generateKey(Class<?> aClass);
	Class<? extends Logger> generateLoggerClass(Class<?> aClass);
}
