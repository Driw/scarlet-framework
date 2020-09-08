package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.reflection.ReflectionConfig;

public class TestUtils {
	public static void setReflectionPackageByClass(Class<?> aClass) {
		ReflectionConfig.setPackage(aClass.getPackage().getName());
	}
}
