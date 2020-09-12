package org.diverproject.scarlet.context;

public interface ContextNameGenerator {
	String generateKeyFor(Class<?> targetClass);
}
