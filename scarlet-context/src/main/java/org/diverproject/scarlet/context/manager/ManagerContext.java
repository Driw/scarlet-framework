package org.diverproject.scarlet.context.manager;

import org.diverproject.scarlet.context.InstanceEntry;

import java.util.Map;

public interface ManagerContext {
	Map<String, Manager> initialize();
	void register(InstanceEntry<String, Manager> instanceEntry);
	<T extends Manager> T get(Class<T> managerClass);
	boolean isAlive();
	void start();
	void stop();
	void restart();
	void finish();
	Thread thread();
}
