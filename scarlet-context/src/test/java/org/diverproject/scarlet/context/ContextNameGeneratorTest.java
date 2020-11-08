package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.ScarletUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ContextNameGeneratorTest {

	@Test
	public void testGenerateKeyFor() {
		assertThrows(NullPointerException.class, () -> ContextNameGenerator.generateKeyFor(null));

		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameClass", ContextNameGenerator.generateKeyFor(new NoNameClass()));
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameExtendedClass", ContextNameGenerator.generateKeyFor(new NoNameExtendedClass()));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(new NamedClass()));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(new NamedExtendedClass()));
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$EmptyNamedClass", ContextNameGenerator.generateKeyFor(new EmptyNamedClass()));

		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameClass", ContextNameGenerator.generateKeyFor(NoNameClass.class));
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$NoNameExtendedClass", ContextNameGenerator.generateKeyFor(NoNameExtendedClass.class));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(NamedClass.class));
		assertEquals("ANamedClass", ContextNameGenerator.generateKeyFor(NamedExtendedClass.class));
		assertEquals("org.diverproject.scarlet.context.ContextNameGeneratorTest$EmptyNamedClass", ContextNameGenerator.generateKeyFor(EmptyNamedClass.class));

		ContextNameGenerator.setNameGenerator(ScarletUtils::nameOf);
		assertEquals("NoNameClass", ContextNameGenerator.generateKeyFor(new NoNameClass()));
		ContextNameGenerator.setNameGenerator(null);
	}

	@Test
	public void testGetClassWithNamedAnnotation() {
		assertFalse(ContextNameGenerator.getClassWithNamedAnnotation(NoNameClass.class).isPresent());
		assertFalse(ContextNameGenerator.getClassWithNamedAnnotation(NoNameExtendedClass.class).isPresent());

		Optional<Class<?>> optional = ContextNameGenerator.getClassWithNamedAnnotation(NamedClass.class);
		assertTrue(optional.isPresent());
		assertEquals(optional.get(), NamedClass.class);

		optional = ContextNameGenerator.getClassWithNamedAnnotation(NamedExtendedClass.class);
		assertTrue(optional.isPresent());
		assertEquals(optional.get(), NamedClass.class);
	}

	private static class NoNameClass { }
	private static class NoNameExtendedClass extends NoNameClass { }

	@Named("ANamedClass")
	private static class NamedClass { }
	private static class NamedExtendedClass extends NamedClass { }
	@Named
	private static class EmptyNamedClass { }

}
