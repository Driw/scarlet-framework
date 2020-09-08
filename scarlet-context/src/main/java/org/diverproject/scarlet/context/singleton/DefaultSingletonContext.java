package org.diverproject.scarlet.context.singleton;

import static org.diverproject.scarlet.context.singleton.SingletonLanguage.REPLACING_OLD_SINGLETON_INSTANCE;
import static org.diverproject.scarlet.context.singleton.SingletonLanguage.SINGLETON_INSTANCE_REGISTERED;
import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import lombok.Data;
import org.diverproject.scarlet.context.LoggerFactory;
import org.diverproject.scarlet.context.ScarletContext;
import org.diverproject.scarlet.context.reflection.ReflectionAnnotationUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.logger.LoggerLanguage;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

@Data
public class DefaultSingletonContext implements SingletonContext {

	private static final LoggerLanguage logger = LoggerFactory.get(SingletonContext.class);

	private Map<String, Object> singletons;

	public DefaultSingletonContext() {
		this.setSingletons(new TreeMap<>());
	}

	@Override
	public void initialize(ScarletContext scarletContext) {
		this.getSingletonImplementations()
			.forEach(this::createInstanceAndRegister);
	}

	@Override
	public <T> T createInstanceAndRegister(Class<T> singletonClass) {
		if (this.getClassWithSingletonAnnotation(singletonClass).isEmpty()) {
			throw SingletonError.singletonClassNotAnnotated(singletonClass);
		}

		String singletonKey = this.generateKeyFor(singletonClass);
		T singletonInstance = singletonClass.isInterface() ?
			ReflectionInterfaceUtils.getInstanceOf(singletonClass) :
			ReflectionUtils.createInstanceOfEmptyConstructor(singletonClass);

		this.registerSingletonInstance(singletonKey, singletonInstance);

		return singletonInstance;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> singletonClass) {
		String key = this.generateKeyFor(singletonClass);
		Object singletonInstance = this.getSingletons().get(key);

		if (Objects.isNull(singletonInstance)) {
			throw SingletonError.singletonNotFound(key, singletonClass);
		}

		if (!ReflectionUtils.isInstanceOf(singletonInstance, singletonClass)) {
			throw SingletonError.cannotCastSingleton(singletonInstance, singletonClass);
		}

		return (T) singletonInstance;
	}

	private Set<Class<?>> getSingletonImplementations() {
		Set<Class<?>> singletonImplementations = ReflectionAnnotationUtils.getAllAnnotatedBy(Singleton.class);
		this.removeDuplicatedSingletonImplementations(singletonImplementations);

		return singletonImplementations;
	}

	private void removeDuplicatedSingletonImplementations(Set<Class<?>> singletonImplementations) {
		Queue<Class<?>> queue = new LinkedList<>(singletonImplementations);

		while (!queue.isEmpty()) {
			Class<?> singletonImplementation = queue.poll();

			if (!this.hasDuplicatedSingletonImplementationOf(singletonImplementations, singletonImplementation)) {
				singletonImplementations.remove(singletonImplementation);
			}
		}
	}

	private boolean hasDuplicatedSingletonImplementationOf(Set<Class<?>> singletonImplementations, Class<?> singletonImplementation) {
		return ReflectionUtils.getAllInheritances(singletonImplementation)
			.stream()
			.filter(singletonImplementations::contains)
			.count() > 1;
	}

	private <T> void registerSingletonInstance(String singletonKey, T singletonInstance) {
		Optional.ofNullable(this.getSingletons().put(singletonKey, singletonInstance))
			.ifPresentOrElse(
				oldSingletonInstance -> logger.warn(REPLACING_OLD_SINGLETON_INSTANCE, singletonKey, nameOf(oldSingletonInstance), nameOf(singletonInstance)),
				() -> logger.notice(SINGLETON_INSTANCE_REGISTERED, singletonKey, nameOf(singletonInstance))
			);
	}

	protected String generateKeyFor(Class<?> singletonClass) {
		// TODO - use configuration value as singleton name if the config exist
		Optional<Class<?>> optional = this.getClassWithSingletonAnnotation(singletonClass);

		return optional.map(singletonClassAnnotated -> singletonClassAnnotated.getAnnotation(Singleton.class))
			.map(singleton -> singleton.key().isEmpty() ? optional.get().getName() : singleton.key())
			.orElseThrow(() -> SingletonError.notASingletonClass(singletonClass));
	}

	private Optional<Class<?>> getClassWithSingletonAnnotation(Class<?> singletonClass) {
		if (Objects.nonNull(singletonClass.getAnnotation(Singleton.class))) {
			return Optional.of(singletonClass);
		}

		return ReflectionUtils.getAllInheritances(singletonClass)
			.stream()
			.filter(aClass -> Objects.nonNull(aClass.getAnnotation(Singleton.class)))
			.findFirst();
	}
}
