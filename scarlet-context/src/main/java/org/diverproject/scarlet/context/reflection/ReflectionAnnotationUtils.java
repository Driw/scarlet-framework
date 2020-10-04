package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.context.reflection.ReflectionConfig.getReflections;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ReflectionAnnotationUtils {

	public static Set<Class<?>> getAllAnnotatedBy(Class<? extends Annotation> singletonClass) {
		return getReflections().getTypesAnnotatedWith(singletonClass);
	}

	public static <A extends Annotation> Optional<Class<?>> getClassWithAnnotation(Class<?> targetClass, Class<A> annotationClass) {
		if (Objects.nonNull(targetClass.getAnnotation(annotationClass))) {
			return Optional.of(targetClass);
		}

		return ReflectionUtils.getAllInheritances(targetClass)
			.stream()
			.filter(aClass -> Objects.nonNull(aClass.getAnnotation(annotationClass)))
			.findFirst();
	}
}
