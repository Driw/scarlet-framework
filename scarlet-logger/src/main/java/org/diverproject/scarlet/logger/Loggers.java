package org.diverproject.scarlet.logger;

public interface Loggers<L extends Logger> {
	boolean contains(String name);
	L get(Class<?> targetClass);
	L get(String name);
	L getDefault();
}
