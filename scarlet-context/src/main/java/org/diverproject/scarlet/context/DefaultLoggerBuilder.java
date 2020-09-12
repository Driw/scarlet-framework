package org.diverproject.scarlet.context;

public class DefaultLoggerBuilder implements LoggerBuilder {

	@Override
	public String generateKey(Class<?> aClass) {
		return aClass.getName();
	}

	@Override
	public Class<? extends Logger> generateLoggerClass(Class<?> aClass) {
		return DefaultLoggerContext.class;
	}
}
