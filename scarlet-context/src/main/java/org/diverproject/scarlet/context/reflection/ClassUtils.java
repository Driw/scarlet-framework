package org.diverproject.scarlet.context.reflection;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public final class ClassUtils {

	private ClassUtils() {
	}

	public static void removeDuplicatedImplementations(Set<Class<?>> classes) {
		Queue<Class<?>> classQueue = new LinkedList<>(classes);

		while (!classQueue.isEmpty()) {
			Class<?> aClass = classQueue.poll();

			if (isNotLeafImplementation(classes, aClass)) {
				classes.remove(aClass);
			}
		}
	}

	public static boolean isNotLeafImplementation(Set<Class<?>> classes, Class<?> aClass) {
		if (Modifier.isAbstract(aClass.getModifiers()) || aClass.isInterface()) {
			return true;
		}

		return classes.stream()
			.anyMatch(c -> ReflectionUtils.getAllInheritances(c).contains(aClass));
	}

}
