package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class ClassUtilsTest {

	@Test
	public void removeDuplicatedImplementations() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(SomeInterface.class);
		classes.add(SomeExtendInterface.class);
		classes.add(SomeAbstractClass.class);
		classes.add(SomeClass.class);
		classes.add(SomeExtendedClass.class);
		classes.add(SomeAbstractImplementation.class);
		classes.add(SomeInterfaceImplementation.class);
		classes.add(SomeExtendedInterfaceImplementation.class);
		assertEquals(8, classes.size());

		ClassUtils.removeDuplicatedImplementations(classes);
		assertEquals(4, classes.size());
		assertFalse(classes.contains(SomeInterface.class));
		assertFalse(classes.contains(SomeExtendInterface.class));
		assertFalse(classes.contains(SomeAbstractClass.class));
		assertFalse(classes.contains(SomeClass.class));
		assertTrue(classes.contains(SomeExtendedClass.class));
		assertTrue(classes.contains(SomeAbstractImplementation.class));
		assertTrue(classes.contains(SomeInterfaceImplementation.class));
		assertTrue(classes.contains(SomeExtendedInterfaceImplementation.class));
	}

	@Test
	public void isNotLeafImplementation() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(SomeInterface.class);
		classes.add(SomeExtendInterface.class);
		classes.add(SomeAbstractClass.class);
		classes.add(SomeClass.class);
		classes.add(SomeExtendedClass.class);
		classes.add(SomeAbstractImplementation.class);
		classes.add(SomeInterfaceImplementation.class);
		classes.add(SomeExtendedInterfaceImplementation.class);
		assertEquals(8, classes.size());

		assertTrue(ClassUtils.isNotLeafImplementation(classes, SomeInterface.class));
		assertTrue(ClassUtils.isNotLeafImplementation(classes, SomeExtendInterface.class));
		assertTrue(ClassUtils.isNotLeafImplementation(classes, SomeAbstractClass.class));
		assertTrue(ClassUtils.isNotLeafImplementation(classes, SomeClass.class));
		assertFalse(ClassUtils.isNotLeafImplementation(classes, SomeExtendedClass.class));
		assertFalse(ClassUtils.isNotLeafImplementation(classes, SomeAbstractImplementation.class));
		assertFalse(ClassUtils.isNotLeafImplementation(classes, SomeInterfaceImplementation.class));
		assertFalse(ClassUtils.isNotLeafImplementation(classes, SomeExtendedInterfaceImplementation.class));
	}

	private interface SomeInterface {}
	private interface SomeExtendInterface extends SomeInterface {}
	private static abstract class SomeAbstractClass {}
	private static class SomeClass {}
	private static class SomeExtendedClass extends SomeClass {}
	private static class SomeAbstractImplementation extends SomeAbstractClass {}
	private static class SomeInterfaceImplementation implements SomeInterface {}
	private static class SomeExtendedInterfaceImplementation implements SomeExtendInterface {}

}
