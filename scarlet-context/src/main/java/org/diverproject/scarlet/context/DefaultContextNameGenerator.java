package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.reflection.ReflectionUtils;

import java.util.Objects;
import java.util.Optional;

public class DefaultContextNameGenerator implements ContextNameGenerator {

	@Override
	public String generateKeyFor(Class<?> targetClass) {
		// TODO - use configuration value as singleton name if the config exist
		Optional<Class<?>> optional = this.getClassWithNamedAnnotation(targetClass);

		return optional.map(namedClassAnnotation -> namedClassAnnotation.getAnnotation(Named.class))
			.map(named -> named.value().isEmpty() ? optional.get().getName() : named.value())
			.orElse(targetClass.getName());
	}

	protected Optional<Class<?>> getClassWithNamedAnnotation(Class<?> singletonClass) {
		if (Objects.nonNull(singletonClass.getAnnotation(Named.class))) {
			return Optional.of(singletonClass);
		}

		return ReflectionUtils.getAllInheritances(singletonClass)
			.stream()
			.filter(aClass -> Objects.nonNull(aClass.getAnnotation(Named.class)))
			.findFirst();
	}
}
