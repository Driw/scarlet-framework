package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;

import java.util.Objects;
import java.util.Optional;

public class ContextNameGenerator {

	private ContextNameGenerator() { }

	@Getter
	@Setter
	private static ContextNameGeneratorInterceptor contextNameGeneratorInterceptor;

	public static String generateKeyFor(Object object) {
		Objects.requireNonNull(object);

		return generateKeyFor(object.getClass());
	}

	public static String generateKeyFor(Class<?> targetClass) {
		if (Objects.nonNull(getContextNameGeneratorInterceptor())) {
			return getContextNameGeneratorInterceptor().generateKeyFor(targetClass);
		}

		Optional<Class<?>> optional = getClassWithNamedAnnotation(targetClass); // TODO - use configuration value as singleton name if the config exist

		return optional.map(namedClassAnnotation -> namedClassAnnotation.getAnnotation(Named.class))
			.map(named -> named.value().isEmpty() ? optional.get().getName() : named.value())
			.orElse(targetClass.getName());
	}

	public static Optional<Class<?>> getClassWithNamedAnnotation(Class<?> singletonClass) {
		if (Objects.nonNull(singletonClass.getAnnotation(Named.class))) {
			return Optional.of(singletonClass);
		}

		return ReflectionUtils.getAllInheritances(singletonClass)
			.stream()
			.filter(aClass -> Objects.nonNull(aClass.getAnnotation(Named.class)))
			.findFirst();
	}

}
