package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.options.Option;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class DefaultContextNameGeneratorTest {

	@Test
	public void Test01_GenerateKeyFor() {
		DefaultContextNameGenerator contextNameGenerator = new DefaultContextNameGenerator();
		assertEquals("org.diverproject.scarlet.context.DefaultContextNameGeneratorTest$NoNameClass", contextNameGenerator.generateKeyFor(NoNameClass.class));
		assertEquals("org.diverproject.scarlet.context.DefaultContextNameGeneratorTest$NoNameExtendedClass", contextNameGenerator.generateKeyFor(NoNameExtendedClass.class));
		assertEquals("ANamedClass", contextNameGenerator.generateKeyFor(NamedClass.class));
		assertEquals("ANamedClass", contextNameGenerator.generateKeyFor(NamedExtendedClass.class));
	}

	@Test
	public void Test02_GetClassWithNamedAnnotation() {
		DefaultContextNameGenerator contextNameGenerator = new DefaultContextNameGenerator();

		assertFalse(contextNameGenerator.getClassWithNamedAnnotation(NoNameClass.class).isPresent());
		assertFalse(contextNameGenerator.getClassWithNamedAnnotation(NoNameExtendedClass.class).isPresent());

		Optional<Class<?>> optional = contextNameGenerator.getClassWithNamedAnnotation(NamedClass.class);
		assertTrue(optional.isPresent());
		assertEquals(optional.get(), NamedClass.class);

		optional = contextNameGenerator.getClassWithNamedAnnotation(NamedExtendedClass.class);
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
