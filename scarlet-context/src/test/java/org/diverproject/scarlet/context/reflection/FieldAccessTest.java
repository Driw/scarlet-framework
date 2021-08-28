package org.diverproject.scarlet.context.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldAccessTest {

	@Test
	void access() throws NoSuchFieldException {
		Field field = ClassTest.class.getDeclaredField("packageAttribute");
		FieldAccess fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());

		field = ClassTest.class.getDeclaredField("publicAttribute");
		fieldAccess = new FieldAccess(field);
		assertTrue(fieldAccess.access().isAccessible());

		field = ClassTest.class.getDeclaredField("privateAttribute");
		fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());

		field = ClassTest.class.getDeclaredField("protectedAttribute");
		fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());
	}

	@Test
	void set() throws NoSuchFieldException {
		ClassTest classTest = new ClassTest();
		Field field = classTest.getClass().getDeclaredField("publicAttribute");
		Field publicAttributeField = field;
		assertDoesNotThrow(() -> new FieldAccess(publicAttributeField).set(classTest, new Object()));

		field = classTest.getClass().getDeclaredField("privateAttribute");
		Field privateAttributeField = field;
		assertThrows(ReflectionException.class, () -> new FieldAccess(privateAttributeField).set(classTest, new Object()));
	}

	@Test
	void finish() throws NoSuchFieldException {
		Field field = ClassTest.class.getDeclaredField("packageAttribute");
		FieldAccess fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());
		assertTrue(field.isAccessible());
		fieldAccess.finish();
		assertFalse(field.isAccessible());

		field = ClassTest.class.getDeclaredField("publicAttribute");
		fieldAccess = new FieldAccess(field);
		assertTrue(fieldAccess.access().isAccessible());
		assertTrue(field.isAccessible());
		fieldAccess.finish();
		assertTrue(field.isAccessible());

		field = ClassTest.class.getDeclaredField("privateAttribute");
		fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());
		assertTrue(field.isAccessible());
		fieldAccess.finish();
		assertFalse(field.isAccessible());

		field = ClassTest.class.getDeclaredField("protectedAttribute");
		fieldAccess = new FieldAccess(field);
		assertFalse(fieldAccess.access().isAccessible());
		assertTrue(field.isAccessible());
		fieldAccess.finish();
		assertFalse(field.isAccessible());
	}

	@Test
	void accessThenSetThenFinish() throws NoSuchFieldException {
		ClassTest classTest = new ClassTest();
		Field privateAttributeField = classTest.getClass().getDeclaredField("privateAttribute");
		assertDoesNotThrow(() -> new FieldAccess(privateAttributeField).accessThenSetThenFinish(classTest, new Object()));
	}

	private static class ClassTest {
		Object packageAttribute;
		public Object publicAttribute;
		private Object privateAttribute;
		protected Object protectedAttribute;
	}
}