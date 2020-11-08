package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.util.StringUtils.nvl;

import org.reflections.Reflections;

public final class ReflectionConfig {

	private static Reflections reflections = new Reflections("");

	private ReflectionConfig() { }

	public static Reflections getReflections() {
		return reflections;
	}

	public static void setPackage(String packageName) {
		reflections = new Reflections(nvl(packageName, ""));
	}

}
