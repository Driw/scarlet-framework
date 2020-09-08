package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Set;

public class ReflectionAnnotationUtilsTest {

	@Before
	public void init() {
		TestUtils.setReflectionPackageByClass(ReflectionAnnotationUtilsTest.class);
	}

	@Test
	@DisplayName("Get All Annotated By")
	public void getAllAnnotatedBy() {
		Set<Class<?>> annotatedClasses = ReflectionAnnotationUtils.getAllAnnotatedBy(TheAnnotation.class);
		assertEquals(4, annotatedClasses.size());
		assertTrue(annotatedClasses.containsAll(Arrays.asList(ClassWithTheAnnotation.class, ExtendedClassWithTheAnnotation.class, InterfaceWithTheAnnotation.class, ExtendedInterfaceWithTheAnnotation.class)));
	}

	private static @interface TheAnnotation {
	}

	private static class ClassWithoutTheAnnotation {
	}

	@TheAnnotation
	private static class ClassWithTheAnnotation {
	}

	private static class ExtendedClassWithTheAnnotation extends ClassWithTheAnnotation {
	}

	private static interface InterfaceWithoutTheAnnotation {
	}

	@TheAnnotation
	private static interface InterfaceWithTheAnnotation {
	}

	private static interface ExtendedInterfaceWithTheAnnotation extends InterfaceWithTheAnnotation {
	}
}
