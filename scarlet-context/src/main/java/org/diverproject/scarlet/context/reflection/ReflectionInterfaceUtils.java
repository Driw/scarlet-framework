package org.diverproject.scarlet.context.reflection;

import org.reflections.Reflections;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionInterfaceUtils {

	private static final Reflections REFLECTIONS = new Reflections("");

	public static <I> Set<Class<? extends I>> getAllImplementationsOf(Class<I> interfaceClass) {
		if (!interfaceClass.isInterface()) {
			throw ReflectionInterfaceExceptionFactory.classIsNotAInterface(interfaceClass);
		}

		return REFLECTIONS.getSubTypesOf(interfaceClass)
			.stream()
			.filter(aClass -> !aClass.isInterface())
			.collect(Collectors.toSet());
	}

	public static <I> Optional<Class<? extends I>> getImplementationOf(Class<I> interfaceClass) {
		return getAllImplementationsOf(interfaceClass)
			.stream()
			.max(ReflectionUtils.maxPriorityAnnotation());
	}

	public static <I> I getInstanceOf(Class<I> interfaceClass) {
		return getImplementationOf(interfaceClass)
			.map(ReflectionUtils::createInstanceOfEmptyConstructor)
			.orElseThrow(() -> ReflectionInterfaceExceptionFactory.getInstanceOfUnimplementedInterface(interfaceClass));
	}
}
