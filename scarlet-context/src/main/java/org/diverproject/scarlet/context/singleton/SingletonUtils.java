package org.diverproject.scarlet.context.singleton;

import org.diverproject.scarlet.context.reflection.ReflectionAnnotationUtils;

import java.lang.reflect.Field;

public final class SingletonUtils {

	private SingletonUtils() { }

	public static boolean isSingleton(Field field) {
		return ReflectionAnnotationUtils.getClassWithAnnotation(field.getType(), Singleton.class).isPresent();
	}

}
