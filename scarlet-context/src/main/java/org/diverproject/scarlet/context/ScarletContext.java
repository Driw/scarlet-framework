package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.manager.ManagerContext;
import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;

public interface ScarletContext {

	void initialize(String[] args);
	Object getInstance(String key);
	<T> T getInstance(Class<T> classType, String key);
	void inject(Object object);

	SingletonContext getSingletonContext();
	ManagerContext getManagerContext();
	boolean isInitialized();

	public static ScarletContext start(String[] args) {
		ScarletContext scarletContext = ReflectionInstanceUtils.getInstanceOf(ScarletContext.class);
		scarletContext.initialize(args);

		return scarletContext;
	}
}
