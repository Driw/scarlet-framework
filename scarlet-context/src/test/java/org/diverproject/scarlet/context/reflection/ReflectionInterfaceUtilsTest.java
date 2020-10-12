package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.Priority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

public class ReflectionInterfaceUtilsTest {

	@Test
	@DisplayName("Get All Implementations Of")
	public void getAllImplementationsOf() {
		Set<?> theInterfaceImplementations = ReflectionInterfaceUtils.getAllImplementationsOf(TheInterface.class);
		assertTrue(theInterfaceImplementations.contains(TheInterfaceConcrete.class));
		assertTrue(theInterfaceImplementations.contains(TheInterfaceAbstract.class));
		assertTrue(theInterfaceImplementations.contains(TheInterfaceExtendedClass.class));
		assertTrue(theInterfaceImplementations.contains(TheInterfaceExtendedInterface.class));
		assertFalse(theInterfaceImplementations.contains(TheInterface.class));
		assertFalse(theInterfaceImplementations.contains(TheInterfaceExtended.class));
	}

	@Test
	@DisplayName("Get Implementation Of")
	public void getImplementationOf() {
		Optional<Class<?>> optional = ReflectionInterfaceUtils.getImplementationOf(TheInterface.class);
		assertTrue(optional.isPresent());
		assertEquals(TheInterfaceExtendedInterface.class, optional.get());
	}

	@Test
	@DisplayName("Get Instance Of")
	public void getInstanceOf() {
		TheInterface theInterface = ReflectionInterfaceUtils.getInstanceOf(TheInterface.class);
		assertTrue(theInterface instanceof TheInterfaceExtendedInterface);

		assertThrows(ReflectionInterfaceException.class, () -> ReflectionInterfaceUtils.getInstanceOf(InterfaceNotImplemented.class));
	}

	public interface TheInterface {
	}

	@Priority(1)
	public static class TheInterfaceConcrete implements TheInterface {
	}

	@Priority(2)
	public abstract static class TheInterfaceAbstract implements TheInterface {
	}

	@Priority(3)
	public static class TheInterfaceExtendedClass extends TheInterfaceAbstract {
	}

	public interface TheInterfaceExtended extends TheInterface {
	}

	@Priority(4)
	public static class TheInterfaceExtendedInterface implements TheInterfaceExtended {
	}

	public static interface InterfaceNotImplemented {
	}
}
