package org.diverproject.scarlet.context;

public interface ContextNameGeneratorInterceptor {
	String generateKeyFor(Class<?> targetClass);
}
