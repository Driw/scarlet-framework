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
import org.diverproject.scarlet.context.utils.ContextStreamUtils;
import org.diverproject.scarlet.context.utils.Pair;
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

		return this.getSingletonImplementations().entrySet()
			.stream()
			.map(entry -> this.createInstance(entry.getKey(), entry.getValue()))
			.collect(Collectors.toMap(InstanceEntry::getKey, InstanceEntry::getValue));
	}

	@Override
	public <T> InstanceEntry<String, T> createInstance(String singletonKey, Class<T> singletonClass) {
		if (!this.hasSingletonAnnotation(singletonClass)) {
			throw SingletonError.singletonClassNotAnnotated(singletonClass);
		}

		T singletonInstance = singletonClass.isInterface() ?
			ReflectionInstanceUtils.getInstanceOf(singletonClass) :
			ReflectionUtils.createInstanceOfEmptyConstructor(singletonClass);

		return new DefaultInstanceEntry<>(singletonKey, singletonInstance);
	}

	@Override
	public <T> T get(Class<T> singletonClass) {
		String singletonKey = this.generateKeyFor(singletonClass);

		return this.get(singletonClass, singletonKey);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> singletonClass, String singletonKey) {
		Object singletonInstance = this.getScarletContext().getInstance(singletonKey);

		if (Objects.isNull(singletonInstance)) {
			throw SingletonError.singletonNotFound(singletonKey, singletonClass);
		}

		if (!ReflectionUtils.isInstanceOf(singletonInstance, singletonClass)) {
			throw SingletonError.cannotCastSingleton(singletonInstance, singletonClass);
		}

		return (T) singletonInstance;
	}

	protected Map<String, Class<?>> getSingletonImplementations() {
		Set<Class<?>> singletonImplementations = ReflectionAnnotationUtils.getAllAnnotatedBy(Singleton.class);
		ClassUtils.removeDuplicatedImplementations(singletonImplementations);

		return singletonImplementations.stream()
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.map(singletonClass -> new Pair<String, Class<?>>(this.generateKeyFor(singletonClass), singletonClass))
			.filter(ContextStreamUtils.distinctByKey(Pair::getFirstValue))
			.collect(ContextStreamUtils.mapPair());
	}

	public boolean hasSingletonAnnotation(Class<?> singletonClass) {
		return ReflectionAnnotationUtils.getClassWithAnnotation(singletonClass, Singleton.class).isPresent();
	}

	public <T> String generateKeyFor(Class<T> singletonClass) {
		return ReflectionAnnotationUtils.getClassWithAnnotation(singletonClass, Singleton.class)
			.map(ContextNameGenerator::generateKeyFor)
			.orElseThrow(() -> SingletonError.generateKeyForNotASingleton(singletonClass));
	}

}
