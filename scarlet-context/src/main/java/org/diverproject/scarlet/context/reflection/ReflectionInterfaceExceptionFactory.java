package org.diverproject.scarlet.context.reflection;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

public class ReflectionInterfaceExceptionFactory {

    public static ReflectionInterfaceException classIsNotAInterface(Class<?> className) {
        return new ReflectionInterfaceException(ReflectionInterfaceExceptionError.CLASS_IS_NOT_A_INTERFACE, nameOf(className));
    }

	public static ReflectionInterfaceException getInstanceOfUnimplementedInterface(Class<?> interfaceClass) {
    	return new ReflectionInterfaceException(ReflectionInterfaceExceptionError.GET_INSTANCE_OF_UNIMPLEMENTED_INTERFACE, nameOf(interfaceClass));
	}
}
