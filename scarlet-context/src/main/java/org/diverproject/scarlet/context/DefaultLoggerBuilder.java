package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.LoggerLanguage;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerLanguage;

public class DefaultLoggerBuilder implements LoggerBuilder {

	@Override
	public String generateKey(Class<?> aClass) {
		return aClass.getName();
	}

	@Override
	public Class<? extends LoggerLanguage> generateLoggerClass(Class<?> aClass) {
		return DefaultLoggerLanguage.class;
	}
}
