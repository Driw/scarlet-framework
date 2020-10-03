package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;

public interface ScarletContext {

	void initialize(String[] args);
	Object getInstance(String key);
	<T> T getInstance(Class<T> classType, String key);

	SingletonContext getSingletonContext();
	ContextNameGenerator getContextNameGenerator();
	boolean isInitialized();

	public static ScarletContext start(String[] args) {
		ScarletContext scarletContext = ReflectionInterfaceUtils.getInstanceOf(ScarletContext.class);
		scarletContext.initialize(args);

		return scarletContext;
	}
}
