package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class ReflectionAnnotationUtilsTest {

	@Test
	@DisplayName("Get All Annotated By")
	public void testGetAllAnnotatedBy() {
		Set<Class<?>> annotatedClasses = ReflectionAnnotationUtils.getAllAnnotatedBy(TheAnnotation.class);
		assertEquals(4, annotatedClasses.size());
		assertTrue(annotatedClasses.containsAll(Arrays.asList(
			ClassWithTheAnnotation.class,
			ExtendedClassWithTheAnnotation.class,
			InterfaceWithTheAnnotation.class,
			ExtendedInterfaceWithTheAnnotation.class)
		));
	}

	@Test
	@DisplayName("Get Class with some annotation in target inheritances")
	public void testGetClassWithAnnotation() {
		assertFalse(ReflectionAnnotationUtils.getClassWithAnnotation(ClassWithoutTheAnnotation.class, TheAnnotation.class).isPresent());
		assertFalse(ReflectionAnnotationUtils.getClassWithAnnotation(InterfaceWithoutTheAnnotation.class, TheAnnotation.class).isPresent());

		Optional<Class<?>> optional = ReflectionAnnotationUtils.getClassWithAnnotation(ClassWithTheAnnotation.class, TheAnnotation.class);
		assertTrue(optional.isPresent());

		optional = ReflectionAnnotationUtils.getClassWithAnnotation(ExtendedClassWithTheAnnotation.class, TheAnnotation.class);
		assertTrue(optional.isPresent());
		assertEquals(ClassWithTheAnnotation.class, optional.get());
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	private @interface TheAnnotation { }
	private static class ClassWithoutTheAnnotation { }

	@TheAnnotation
	private static class ClassWithTheAnnotation { }
	private static class ExtendedClassWithTheAnnotation extends ClassWithTheAnnotation { }
	private interface InterfaceWithoutTheAnnotation { }

	@TheAnnotation
	private interface InterfaceWithTheAnnotation { }
	private interface ExtendedInterfaceWithTheAnnotation extends InterfaceWithTheAnnotation { }

}
