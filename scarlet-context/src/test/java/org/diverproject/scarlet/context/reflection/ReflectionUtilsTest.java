package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.context.Priority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReflectionUtilsTest {

	@Test
	@DisplayName("Compare by Priority Annotation")
	public void compareByPriorityAnnotation() {
		List<Class<?>> classes = Stream.of(FirstPriority.class, SecondPriority.class)
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.collect(Collectors.toList());
		assertEquals(2, classes.size());
		assertEquals(SecondPriority.class, classes.get(0));
		assertEquals(FirstPriority.class, classes.get(1));

		classes = Stream.of(SecondPriority.class, FirstPriority.class)
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.collect(Collectors.toList());
		assertEquals(2, classes.size());
		assertEquals(SecondPriority.class, classes.get(0));
		assertEquals(FirstPriority.class, classes.get(1));
	}

	@Test
	@DisplayName("Compare by Priority Annotation")
	public void maxPriorityAnnotation() {
		Optional<Class<?>> optional = Stream.of(FirstPriority.class, SecondPriority.class)
			.max(ReflectionUtils.maxPriorityAnnotation());
		assertEquals(SecondPriority.class, optional.get());
	}

	@Test
	@DisplayName("Get a Empty Constructor")
	public void getAEmptyConstructor() {
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(NonConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PackageConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PrivateConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(ProtectedConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PublicConstructor.class));
		assertThrows(ReflectionException.class, () -> ReflectionUtils.getEmptyConstructorOf(NonEmptyConstructor.class));
	}

	@Test
	@DisplayName("Create Instance of Empty Constructor")
	public void createInstanceOfEmptyConstructor() {
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(NonConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PackageConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PrivateConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(ProtectedConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PublicConstructor.class));

		assertThrows(ReflectionException.class, () -> ReflectionUtils.createInstanceOfEmptyConstructor(NonEmptyConstructor.class));
		assertThrows(ReflectionException.class, () -> ReflectionUtils.createInstanceOfEmptyConstructor(ConstructorWithException.class));
	}

	@Test
	@DisplayName("Get All Inheritances Classes")
	public void getAllInheritancesClasses() {
		Set<Class<?>> inheritances;

		assertTrue(ReflectionUtils.getAllInheritancesClasses(NonExtendedClass.class).isEmpty());
		assertTrue(ReflectionUtils.getAllInheritancesClasses(NonExtendedClassWithInterface.class).isEmpty());
		assertTrue(ReflectionUtils.getAllInheritancesClasses(ThirdChildClass.class).isEmpty());

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesClasses(SecondChildClass.class));
		assertEquals(1, inheritances.size());
		assertTrue(inheritances.contains(ThirdChildClass.class));
		assertFalse(inheritances.contains(SecondChildClass.class));
		assertFalse(inheritances.contains(FirstChildClass.class));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesClasses(FirstChildClass.class));
		assertEquals(2, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(ThirdChildClass.class, SecondChildClass.class)));
		assertFalse(inheritances.contains(FirstChildClass.class));

		assertTrue(ReflectionUtils.getAllInheritancesClasses(DoubleInterfaceImplementation.class).isEmpty());
	}

	@Test
	@DisplayName("Get All Inheritances Interfaces")
	public void getAllInheritancesInterfaces() {
		Set<Class<?>> inheritances;

		assertTrue(ReflectionUtils.getAllInheritancesInterfaces(NonExtendedInterface.class).isEmpty());
		assertTrue(ReflectionUtils.getAllInheritancesInterfaces(ThirdChildInterface.class).isEmpty());

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(SecondChildInterface.class));
		assertEquals(1, inheritances.size());
		assertTrue(inheritances.contains(ThirdChildInterface.class));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(FirstChildInterface.class));
		assertEquals(2, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(SecondChildInterface.class, ThirdChildInterface.class)));

		assertTrue(ReflectionUtils.getAllInheritancesInterfaces(FirstDoubleInterface.class).isEmpty());
		assertTrue(ReflectionUtils.getAllInheritancesInterfaces(SecondDoubleInterface.class).isEmpty());

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(DoubleInterfaceExtended.class));
		assertEquals(2, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(FirstDoubleInterface.class, SecondDoubleInterface.class)));

		assertTrue(ReflectionUtils.getAllInheritancesInterfaces(NonExtendedClass.class).isEmpty());

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(NonExtendedClassWithInterface.class));
		assertEquals(1, inheritances.size());
		assertTrue(inheritances.contains(NonExtendedInterface.class));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(ThirdChildClass.class));
		assertEquals(1, inheritances.size());
		assertTrue(inheritances.contains(ThirdChildInterface.class));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(SecondChildClass.class));
		assertEquals(2, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(SecondChildInterface.class, ThirdChildInterface.class)));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(FirstChildClass.class));
		assertEquals(3, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(FirstChildInterface.class, SecondChildInterface.class, ThirdChildInterface.class)));

		assertNotNull(inheritances = ReflectionUtils.getAllInheritancesInterfaces(DoubleInterfaceImplementation.class));
		assertEquals(3, inheritances.size());
		assertTrue(inheritances.containsAll(Arrays.asList(DoubleInterfaceExtended.class, FirstDoubleInterface.class, SecondDoubleInterface.class)));
	}

	@Priority(1)
	private static class FirstPriority { }

	@Priority(2)
	private static class SecondPriority { }
	private static class NonConstructor { }
	private static class PackageConstructor { PackageConstructor() { } }
	private static class PrivateConstructor { private PrivateConstructor() { } }
	private static class ProtectedConstructor { protected ProtectedConstructor() { } }
	private static class PublicConstructor { public PublicConstructor() { } }
	private static class NonEmptyConstructor { public NonEmptyConstructor(String... args) { } }
	private static class ConstructorWithException { public ConstructorWithException() { throw new RuntimeException(); } }
	private interface NonExtendedInterface { }
	private interface ThirdChildInterface { }
	private interface SecondChildInterface extends ThirdChildInterface { }
	private interface FirstChildInterface extends SecondChildInterface { }
	private static class NonExtendedClass { }
	private static class NonExtendedClassWithInterface implements NonExtendedInterface { }
	private static class ThirdChildClass implements ThirdChildInterface { }
	private static class SecondChildClass extends ThirdChildClass implements SecondChildInterface { }
	private static class FirstChildClass extends SecondChildClass implements FirstChildInterface { }
	private interface FirstDoubleInterface { }
	private interface SecondDoubleInterface { }
	private interface DoubleInterfaceExtended extends FirstDoubleInterface, SecondDoubleInterface { 	}
	private static class DoubleInterfaceImplementation implements DoubleInterfaceExtended {	}
}
