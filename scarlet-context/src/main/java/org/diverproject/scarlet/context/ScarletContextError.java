package org.diverproject.scarlet.context;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import java.lang.reflect.Field;

public class ScarletContextError {

	private ScarletContextError() { }

	public static ScarletContextException alreadyInitialized() {
		return new ScarletContextException(ScarletContextLanguage.ALREADY_INITIALIZED);
	}

	public static ScarletContextException getInstanceCannotCast(String key, Object contextInstance, Class<?> classType) {
		return new ScarletContextException(ScarletContextLanguage.GET_INSTANCE_CANNOT_CAST, key, nameOf(contextInstance), nameOf(classType));
	}

	public static ScarletContextException injectableFieldNotImplemented(Object object, Field field) {
		return new ScarletContextException(ScarletContextLanguage.INJECTABLE_FIELD_NOT_IMPLEMENTED, nameOf(object), field.getName(), nameOf(field.getType()));
	}

}
