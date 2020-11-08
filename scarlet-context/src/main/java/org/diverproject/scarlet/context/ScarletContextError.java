package org.diverproject.scarlet.context;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import org.diverproject.scarlet.logger.LoggerLanguage;

public class ScarletContextError {

	private ScarletContextError() { }

	public static ScarletContextException alreadyInitialized() {
		return new ScarletContextException(ScarletContextLanguage.ALREADY_INITIALIZED);
	}

	public static ScarletContextException loggerInstanceOfAtGetLogger(LoggerLanguage loggerLanguage, Class<?> aClass) {
		return new ScarletContextException(ScarletContextLanguage.LOGGER_INSTANCE_OF_AT_GET_LOGGER, nameOf(loggerLanguage), nameOf(aClass));
	}

	public static ScarletContextException getInstanceCannotCast(String key, Object contextInstance, Class<?> classType) {
		return new ScarletContextException(ScarletContextLanguage.GET_INSTANCE_CANNOT_CAST, key, nameOf(contextInstance), nameOf(classType));
	}

}
