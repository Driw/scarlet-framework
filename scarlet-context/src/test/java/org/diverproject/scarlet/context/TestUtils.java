package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.diverproject.scarlet.context.options.OptionContext;
import org.diverproject.scarlet.context.reflection.ReflectionConfig;
import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;
import org.diverproject.scarlet.context.singleton.SingletonContext;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

public class TestUtils {

	public static void setReflectionPackageByClass(Class<?> aClass) {
		ReflectionConfig.setPackage(aClass.getPackage().getName());
	}

	public static String[] contextArguments() {
		return new String[] { "managerContextParalleled" };
	}

	@PrepareForTest(ReflectionInterfaceUtils.class)
	public static void mockDefaultImplementations() {
		assertNotNull(mockStatic(ReflectionInterfaceUtils.class));
		when(ReflectionInterfaceUtils.getInstanceOf(OptionContext.class)).thenReturn(Mockito.mock(OptionContext.class));
		when(ReflectionInterfaceUtils.getInstanceOf(SingletonContext.class)).thenReturn(Mockito.mock(SingletonContext.class));
		when(ReflectionInterfaceUtils.getInstanceOf(ContextNameGenerator.class)).thenReturn(Mockito.mock(ContextNameGenerator.class));
	}
}
