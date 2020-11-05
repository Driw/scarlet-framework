package org.diverproject.scarlet.context.reflection;

public class ReflectionInstanceUtils {

	private ReflectionInstanceUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <I> I getInstanceOf(Class<I> interfaceClass) {
		return (I) ReflectionInterfaceUtils.getImplementationOf(interfaceClass)
			.map(ReflectionUtils::createInstanceOfEmptyConstructor)
			.orElseThrow(() -> ReflectionInterfaceExceptionFactory.getInstanceOfUnimplementedInterface(interfaceClass));
	}
}
