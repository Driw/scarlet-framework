package org.diverproject.scarlet.context;

import static org.diverproject.scarlet.context.ScarletContextLanguage.REPLACING_OLD_SINGLETON_INSTANCE;
import static org.diverproject.scarlet.context.ScarletContextLanguage.SINGLETON_INSTANCE_REGISTERED;

import lombok.Data;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.reflection.ReflectionUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Data
@Accessors(chain = true)
public class DefaultScarletContext implements ScarletContext {

	private static final Logger logger = LoggerFactory.get(ScarletContext.class);

	private SingletonContext singletonContext;
	private ContextNameGenerator contextNameGenerator;
	private Map<String, Object> instances;

	public DefaultScarletContext() {
		this.setInstances(new TreeMap<>());
	}

	@Override
	public void initialize(String[] args) {
		this.setSingletonContext(ReflectionInterfaceUtils.getInstanceOf(SingletonContext.class));
		this.setContextNameGenerator(ReflectionInterfaceUtils.getInstanceOf(ContextNameGenerator.class));
		this.getSingletonContext().initialize(this).forEach(this::registerSingleton);
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

		if (!ReflectionUtils.isInstanceOf(instance, classType)) {
			throw ScarletContextError.getInstanceCannotCast(key, instance, classType);
		}

		return (T) instance;
	}

	private void registerSingleton(String key, Object singletonInstance) {
		Object oldInstance = this.getInstances().put(key, singletonInstance);

		if (Objects.isNull(oldInstance)) {
			logger.notice(SINGLETON_INSTANCE_REGISTERED, key, singletonInstance);
		} else {
			logger.warn(REPLACING_OLD_SINGLETON_INSTANCE, key, oldInstance, singletonInstance);
		}
	}
}
