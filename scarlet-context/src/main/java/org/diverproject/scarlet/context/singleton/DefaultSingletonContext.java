package org.diverproject.scarlet.context.singleton;

import lombok.Data;
import lombok.ToString;
import org.diverproject.scarlet.context.ContextNameGenerator;
import org.diverproject.scarlet.context.DefaultInstanceEntry;
import org.diverproject.scarlet.context.InstanceEntry;
import org.diverproject.scarlet.context.LoggerFactory;
import org.diverproject.scarlet.context.ScarletContext;
import org.diverproject.scarlet.context.reflection.ClassUtils;
import org.diverproject.scarlet.context.reflection.ReflectionAnnotationUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.logger.LoggerLanguage;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class DefaultSingletonContext implements SingletonContext {

	private static final LoggerLanguage logger = LoggerFactory.get(SingletonContext.class);

	@ToString.Exclude
	private ScarletContext scarletContext;

	@Override
	public Map<String, Object> initialize(ScarletContext scarletContext) {
		this.setScarletContext(scarletContext);

		return this.getSingletonImplementations()
			.stream()
			.map(this::createInstanceAndRegister)
			.collect(Collectors.toMap(InstanceEntry::getKey, InstanceEntry::getValue));
	}

	@Override
	public <T> InstanceEntry<String, T> createInstanceAndRegister(Class<T> singletonClass) {
		if (!this.hasSingletonAnnotation(singletonClass)) {
			throw SingletonError.singletonClassNotAnnotated(singletonClass);
		}

		String singletonKey = this.generateKeyFor(singletonClass);
		T singletonInstance = singletonClass.isInterface() ?
			ReflectionInstanceUtils.getInstanceOf(singletonClass) :
			ReflectionUtils.createInstanceOfEmptyConstructor(singletonClass);

		return new DefaultInstanceEntry<>(singletonKey, singletonInstance);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> singletonClass) {
		String key = this.generateKeyFor(singletonClass);
		Object singletonInstance = this.getScarletContext().getInstance(key);

		if (Objects.isNull(singletonInstance)) {
			throw SingletonError.singletonNotFound(key, singletonClass);
		}

		if (!ReflectionUtils.isInstanceOf(singletonInstance, singletonClass)) {
			throw SingletonError.cannotCastSingleton(singletonInstance, singletonClass);
		}

		return (T) singletonInstance;
	}

	protected Set<Class<?>> getSingletonImplementations() {
		Set<Class<?>> singletonImplementations = ReflectionAnnotationUtils.getAllAnnotatedBy(Singleton.class);
		ClassUtils.removeDuplicatedImplementations(singletonImplementations);

		return singletonImplementations;
	}

	protected boolean hasSingletonAnnotation(Class<?> singletonClass) {
		return !ReflectionAnnotationUtils.getClassWithAnnotation(singletonClass, Singleton.class).isPresent();
	}

	private <T> String generateKeyFor(Class<T> singletonClass) {
		return ContextNameGenerator.generateKeyFor(singletonClass);
	}
}
