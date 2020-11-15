package org.diverproject.scarlet.context;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.manager.ManagerContext;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.Logger;

import java.util.Map;
import java.util.Objects;
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
}
