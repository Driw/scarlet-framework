package org.diverproject.scarlet.context.manager;

import org.diverproject.scarlet.context.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public final class ManagerUtils {

	private ManagerUtils() { }

	public static boolean isManagerType(Field field) {
		return ReflectionUtils.getAllInheritances(field.getType())
			.stream()
			.map(Class::getInterfaces)
			.anyMatch(classes -> Arrays.asList(classes).contains(Manager.class));
	}

}
