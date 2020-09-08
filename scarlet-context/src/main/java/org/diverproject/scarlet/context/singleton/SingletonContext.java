package org.diverproject.scarlet.context.singleton;

import org.diverproject.scarlet.context.ScarletContext;

public interface SingletonContext {
	void initialize(ScarletContext scarletContext);
	<T> T createInstanceAndRegister(Class<T> singletonClass);
	<T> T get(Class<T> singletonClass);
}
