package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.context.reflection.ReflectionConfig.getReflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class ReflectionAnnotationUtils {

	private ReflectionAnnotationUtils() { }

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

	public static <T extends Annotation> List<Field> getFieldsAnnotatedBy(Class<?> aClass, Class<T> annotation) {
		return Arrays.stream(aClass.getDeclaredFields())
			.filter(field -> Objects.nonNull(field.getAnnotation(annotation)))
			.filter(field -> !Modifier.isStatic(field.getModifiers()))
			.collect(Collectors.toList());
	}

	public static <T extends Annotation> List<Field> getAllFieldsAnnotatedBy(Class<?> aClass, Class<T> annotation) {
		List<Field> fields = ReflectionUtils.getAllInheritancesClasses(aClass)
			.stream()
			.filter(inheritance -> !inheritance.isInterface())
			.flatMap(inheritance -> getFieldsAnnotatedBy(inheritance, annotation).stream())
			.collect(Collectors.toList());
		fields.addAll(getFieldsAnnotatedBy(aClass, annotation));

		return fields;
	}

}
