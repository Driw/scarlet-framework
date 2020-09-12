package org.diverproject.scarlet.context;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import org.diverproject.scarlet.logger.LoggerLanguage;

public class ScarletContextError {

	private ScarletContextError() { }

	public static ScarletRuntimeContextException loggerInstanceOfAtGetLogger(LoggerLanguage loggerLanguage, Class<?> aClass) {
		return new ScarletRuntimeContextException(ScarletContextLanguage.LOGGER_INSTANCE_OF_AT_GET_LOGGER, nameOf(loggerLanguage), nameOf(aClass));
	}

	public static ScarletRuntimeContextException getInstanceCannotCast(String key, Object contextInstance, Class<?> classType) {
		return new ScarletRuntimeContextException(ScarletContextLanguage.GET_INSTANCE_CANNOT_CAST, key, nameOf(contextInstance), nameOf(classType));
	}
}
