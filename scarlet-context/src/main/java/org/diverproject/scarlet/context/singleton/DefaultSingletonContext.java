package org.diverproject.scarlet.context.singleton;

import lombok.Data;
import lombok.ToString;
import org.diverproject.scarlet.context.DefaultInstanceEntry;
import org.diverproject.scarlet.context.InstanceEntry;
import org.diverproject.scarlet.context.LoggerFactory;
import org.diverproject.scarlet.context.ScarletContext;
import org.diverproject.scarlet.context.reflection.ReflectionAnnotationUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.logger.LoggerLanguage;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
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
			ReflectionInterfaceUtils.getInstanceOf(singletonClass) :
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
		this.removeDuplicatedSingletonImplementations(singletonImplementations);

		return singletonImplementations;
	}

	protected void removeDuplicatedSingletonImplementations(Set<Class<?>> singletonImplementations) {
		Queue<Class<?>> queue = new LinkedList<>(singletonImplementations);

		while (!queue.isEmpty()) {
			Class<?> singletonImplementation = queue.poll();

			if (!this.hasDuplicatedSingletonImplementationOf(singletonImplementations, singletonImplementation)) {
				singletonImplementations.remove(singletonImplementation);
			}
		}
	}

	protected boolean hasDuplicatedSingletonImplementationOf(Set<Class<?>> singletonImplementations, Class<?> singletonImplementation) {
		return ReflectionUtils.getAllInheritances(singletonImplementation)
			.stream()
			.filter(singletonImplementations::contains)
			.count() > 1;
	}

	protected boolean hasSingletonAnnotation(Class<?> singletonClass) {
		return !ReflectionAnnotationUtils.getClassWithAnnotation(singletonClass, Singleton.class).isEmpty();
	}

	private <T> String generateKeyFor(Class<T> singletonClass) {
		return this.getScarletContext().getContextNameGenerator().generateKeyFor(singletonClass);
	}
}
