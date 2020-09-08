package org.diverproject.scarlet.context.reflection;

import org.reflections.Reflections;

public class ReflectionConfig {

	private static Reflections reflections = new Reflections("");

	public static Reflections getReflections() {
		return reflections;
	}

	public static void setPackage(String packageName) {
		reflections = new Reflections(packageName);
	}
}
