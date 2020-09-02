package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.context.Priority;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Optional;

public class ReflectionUtils {

	public static Comparator<Class<?>> compareByPriorityAnnotation() {
		Comparator<Class<?>> comparator = Comparator.comparing(
			aClass -> Optional.ofNullable(aClass.getAnnotation(Priority.class))
				.map(Priority::value)
				.orElse(0)
		);

		return comparator.reversed();
	}

	public static Comparator<Class<?>> maxPriorityAnnotation() {
		return Comparator.comparing(
			aClass -> Optional.ofNullable(aClass.getAnnotation(Priority.class))
				.map(Priority::value)
				.orElse(0)
		);
	}

	public static <T> Constructor<T> getEmptyConstructorOf(Class<T> classType) {
		try {
			return classType.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			throw ReflectionExceptionFactory.emptyConstructorException(e, classType);
		}
	}

	public static <T> T createInstanceOfEmptyConstructor(Class<T> classType) {
		Constructor<T> constructor = getEmptyConstructorOf(classType);

		try {
			constructor.setAccessible(true);
			T instance = constructor.newInstance();
			constructor.setAccessible(false);

			return instance;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw ReflectionExceptionFactory.failureOnCreateAInstance(classType);
		}
	}
}
