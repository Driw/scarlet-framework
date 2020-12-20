package org.diverproject.scarlet.context;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.manager.ManagerContext;
import org.diverproject.scarlet.context.manager.ManagerUtils;
import org.diverproject.scarlet.context.reflection.FieldAccess;
import org.diverproject.scarlet.context.reflection.ReflectionAnnotationUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;
import org.diverproject.scarlet.context.singleton.SingletonUtils;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

@Data
@Accessors(chain = true)
public class DefaultScarletContext implements ScarletContext {

	private static final Logger logger = LoggerFactory.get(ScarletContext.class);

	private Map<String, Object> instances;
	private SingletonContext singletonContext;
	private ManagerContext managerContext;
	private boolean initialized;

	public DefaultScarletContext() {
		this.setInstances(new TreeMap<>());
		this.setSingletonContext(ReflectionInstanceUtils.getInstanceOf(SingletonContext.class));
		this.setManagerContext(ReflectionInstanceUtils.getInstanceOf(ManagerContext.class));
	}

	@Override
	public void initialize(String[] args) {
		if (this.isInitialized()) {
			throw ScarletContextError.alreadyInitialized();
		}

		this.setInitialized(true);
		this.getSingletonContext().initialize(this).forEach(this::registerSingleton);
		this.getManagerContext().initialize().forEach(this::registerManager);
		this.getInstances().values().forEach(this::inject);
	}

	@Override
	public Object getInstance(String key) {
		return this.getInstances().get(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> classType, String key) {
		Object instance = this.getInstance(key);

		if (Objects.isNull(instance)) {
			return null;
		}

		if (ReflectionUtils.isInstanceOf(instance, classType)) {
			return (T) instance;
		}

		throw ScarletContextError.getInstanceCannotCast(key, instance, classType);
	}

	@Override
	public void inject(Object object) {
		String objectName = nameOf(object);

		logger.trace(ScarletContextLanguage.INJECT_OBJECT, objectName);

		ReflectionAnnotationUtils.getFieldsAnnotatedBy(object.getClass(), Injectable.class)
			.forEach(field -> this.injectField(object, field));
	}

	public void registerSingleton(String key, Object singletonInstance) {
		this.contextInstanceRegister(key, singletonInstance, ScarletContextLanguage.SINGLETON_INSTANCE_REGISTERED);
	}

	public void registerManager(String key, Object managerInstance) {
		this.contextInstanceRegister(key, managerInstance, ScarletContextLanguage.MANAGER_INSTANCE_REGISTERED);
	}

	public void contextInstanceRegister(String key, Object contextInstance, Language successfullyMessage) {
		Object oldInstance = this.getInstances().put(key, contextInstance);

		if (Objects.isNull(oldInstance)) {
			logger.notice(successfullyMessage, key, contextInstance);
		} else {
			logger.warn(ScarletContextLanguage.REPLACING_CONTEXT_INSTANCE, key, oldInstance, contextInstance);
		}
	}

	private void injectField(Object object, Field field) {
		Object value = this.getInjectValueOf(object, field);

		String objectName = nameOf(object);
		String valueName = nameOf(value);

		new FieldAccess(field)
			.accessThenSetThenFinish(object, value);

		logger.trace(ScarletContextLanguage.INJECT_FIELD, objectName, field.getName(), valueName);
	}

	private Object getInjectValueOf(Object object, Field field) {
		String injectionName = this.injectionNameOf(field);

		if (ManagerUtils.isManagerType(field) || SingletonUtils.isSingleton(field)) {
			if (StringUtils.isEmpty(injectionName))
				return this.getInstance(ContextNameGenerator.generateKeyFor(field.getType()));

			return this.getInstance(injectionName);
		}

		if (StringUtils.isEmpty(injectionName)) {
			if (field.getType().isInterface())
				return ReflectionInterfaceUtils.getImplementationOf(field.getType())
					.map(ReflectionUtils::createInstanceOfEmptyConstructor)
					.orElseThrow(() -> ScarletContextError.injectableFieldNotImplemented(object, field));

			return ReflectionUtils.createInstanceOfEmptyConstructor(field.getType());
		}

		return this.getInstance(injectionName);
	}

	private String injectionNameOf(Field field) {
		return Optional.ofNullable(field.getAnnotation(Named.class))
			.map(Named::value)
			.orElse("");
	}

}
