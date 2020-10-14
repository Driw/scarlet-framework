package org.diverproject.scarlet.context;

public interface NameGenerator {
	String generateKeyFor(Class<?> targetClass);
}
