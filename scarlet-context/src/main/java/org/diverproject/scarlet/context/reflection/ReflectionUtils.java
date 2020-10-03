package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.context.Priority;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

	public static Set<Class<?>> getAllInheritancesClasses(Class<?> targetClass) {
		Set<Class<?>> inheritances = new HashSet<>();
		Class<?> currentClass = targetClass.getSuperclass();

		while (!Objects.isNull(currentClass) && !Objects.equals(currentClass, Object.class)) {
			inheritances.add(currentClass);
			currentClass = currentClass.getSuperclass();
		}

		return inheritances;
	}

	public static Set<Class<?>> getAllInheritancesInterfaces(Class<?> targetClass) {
		Set<Class<?>> inheritances = new HashSet<>();
		Arrays.stream(targetClass.getInterfaces())
			.filter(Class::isInterface)
			.peek(inheritances::add)
			.forEach(inheritanceClass -> Arrays.stream(inheritanceClass.getInterfaces())
				.peek(inheritances::add)
				.filter(Class::isInterface)
				.forEach(superInterfaceClass -> inheritances.addAll(getAllInheritancesInterfaces(superInterfaceClass))));

		return inheritances;
	}

	public static Set<Class<?>> getAllInheritances(Class<?> targetClass) {
		Set<Class<?>> inheritancesClasses = getAllInheritancesClasses(targetClass);
		inheritancesClasses.addAll(getAllInheritancesInterfaces(targetClass));

		return inheritancesClasses;
	}

	public static <T> boolean isInstanceOf(Object object, Class<T> targetClass) {
		return object.getClass().equals(targetClass) || getAllInheritances(object.getClass())
			.contains(targetClass);
	}
}
