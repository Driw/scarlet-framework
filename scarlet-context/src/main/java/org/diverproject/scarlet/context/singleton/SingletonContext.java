package org.diverproject.scarlet.context.singleton;

import org.diverproject.scarlet.context.InstanceEntry;
import org.diverproject.scarlet.context.ScarletContext;

import java.util.Map;

public interface SingletonContext {
	Map<String, Object> initialize(ScarletContext scarletContext);
	<T> InstanceEntry<String, T> createInstanceAndRegister(Class<T> singletonClass);
	<T> T get(Class<T> singletonClass);
}
