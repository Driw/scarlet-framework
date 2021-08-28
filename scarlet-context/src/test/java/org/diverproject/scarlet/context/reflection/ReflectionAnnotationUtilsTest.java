package org.diverproject.scarlet.context.reflection;

import org.diverproject.scarlet.context.Injectable;
import org.diverproject.scarlet.context.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReflectionAnnotationUtilsTest {

	@BeforeEach
	void setUp() {
		TestUtils.setHereAsPackageReflection();
	}

	@Test
	void testGetAllAnnotatedBy() {
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
	void testGetClassWithAnnotation() {
		assertFalse(ReflectionAnnotationUtils.getClassWithAnnotation(ClassWithoutTheAnnotation.class, TheAnnotation.class).isPresent());
		assertFalse(ReflectionAnnotationUtils.getClassWithAnnotation(InterfaceWithoutTheAnnotation.class, TheAnnotation.class).isPresent());

		Optional<Class<?>> optional = ReflectionAnnotationUtils.getClassWithAnnotation(ClassWithTheAnnotation.class, TheAnnotation.class);
		assertTrue(optional.isPresent());

		optional = ReflectionAnnotationUtils.getClassWithAnnotation(ExtendedClassWithTheAnnotation.class, TheAnnotation.class);
		assertTrue(optional.isPresent());
		assertEquals(ClassWithTheAnnotation.class, optional.get());
	}

	@Test
	void testGetFieldsAnnotatedBy() {
		List<Field> fields = ReflectionAnnotationUtils.getFieldsAnnotatedBy(AnnotatedFieldClass.class, Injectable.class);
		assertNotNull(fields);
		assertEquals(1, fields.size());
		assertEquals("injectable", fields.get(0).getName());

		fields = ReflectionAnnotationUtils.getFieldsAnnotatedBy(ExtendedAnnotatedFieldClass.class, Injectable.class);
		assertNotNull(fields);
		assertEquals(1, fields.size());
		assertEquals("injectableString", fields.get(0).getName());
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

	private static class AnnotatedFieldClass {
		private static final int STATIC_INT = 0;
		private int unannotated;

		@Injectable
		private int injectable;
	}

	private static class ExtendedAnnotatedFieldClass extends AnnotatedFieldClass {
		@Injectable
		private static final int STATIC_INJECTABLE_INT = 0;

		@Injectable
		private String injectableString;
	}
}
