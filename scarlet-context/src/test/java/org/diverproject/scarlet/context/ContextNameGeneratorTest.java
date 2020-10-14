package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ContextNameGeneratorTest {

	@Test
	public void Test01_GenerateKeyFor() {
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameClass", ContextNameGenerator.generateKeyFor(NoNameClass.class));
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameExtendedClass", ContextNameGenerator.generateKeyFor(NoNameExtendedClass.class));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(NamedClass.class));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(NamedExtendedClass.class));
	}

	@Test
	public void Test02_GetClassWithNamedAnnotation() {
		assertFalse(ContextNameGenerator.getClassWithNamedAnnotation(NoNameClass.class).isPresent());
		assertFalse(ContextNameGenerator.getClassWithNamedAnnotation(NoNameExtendedClass.class).isPresent());

		Optional<Class<?>> optional = ContextNameGenerator.getClassWithNamedAnnotation(NamedClass.class);
		assertTrue(optional.isPresent());
		assertEquals(optional.get(), NamedClass.class);

		optional = ContextNameGenerator.getClassWithNamedAnnotation(NamedExtendedClass.class);
		assertTrue(optional.isPresent());
		assertEquals(optional.get(), NamedClass.class);
	}

	private static class NoNameClass {
	}

	private static class NoNameExtendedClass extends NoNameClass {
	}

	@Named("ANamedClass")
	private static class NamedClass {
	}

	private static class NamedExtendedClass extends NamedClass {
	}

}
