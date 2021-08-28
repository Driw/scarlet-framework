package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import java.lang.reflect.Field;

public final class ReflectionExceptionFactory {

	private ReflectionExceptionFactory() { }

	public static ReflectionException emptyConstructorException(NoSuchMethodException e, Class<?> classType) {
		return new ReflectionException(e, ReflectionError.EMPTY_CONSTRUCTOR_EXCEPTION, nameOf(classType));
	}

	public static ReflectionException failureOnCreateAInstance(Class<?> classType) {
		return new ReflectionException(ReflectionError.FAILURE_ON_CREATE_A_INSTANCE, nameOf(classType));
	}

	public static ReflectionException fieldAccessSet(IllegalAccessException e, Object object, Field field, Object value) {
		return new ReflectionException(e, ReflectionError.FIELD_ACCESS_SET, nameOf(object), field.getName(), nameOf(value));
	}

}
