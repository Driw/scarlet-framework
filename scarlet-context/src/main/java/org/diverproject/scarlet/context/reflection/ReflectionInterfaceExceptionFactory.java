package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

public final class ReflectionInterfaceExceptionFactory {

	private ReflectionInterfaceExceptionFactory() { }

	public static ReflectionInterfaceException classIsNotAInterface(Class<?> className) {
        return new ReflectionInterfaceException(ReflectionInterfaceError.CLASS_IS_NOT_A_INTERFACE, nameOf(className));
    }

	public static ReflectionInterfaceException getInstanceOfUnimplementedInterface(Class<?> interfaceClass) {
    	return new ReflectionInterfaceException(ReflectionInterfaceError.GET_INSTANCE_OF_UNIMPLEMENTED_INTERFACE, nameOf(interfaceClass));
	}
}
