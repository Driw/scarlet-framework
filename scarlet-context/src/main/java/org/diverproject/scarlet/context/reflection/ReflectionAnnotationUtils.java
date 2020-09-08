package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.context.reflection.ReflectionConfig.getReflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ReflectionAnnotationUtils {

	public static Set<Class<?>> getAllAnnotatedBy(Class<? extends Annotation> singletonClass) {
		return getReflections().getTypesAnnotatedWith(singletonClass);
	}
}
