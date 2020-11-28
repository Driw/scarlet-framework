package org.diverproject.scarlet.context.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.diverproject.scarlet.context.InstanceEntry;
import org.diverproject.scarlet.context.Priority;
import org.diverproject.scarlet.context.ScarletContext;
import org.diverproject.scarlet.context.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reflections.Reflections;

import java.util.Map;

class DefaultSingletonContextTest {

	private static Reflections reflections;

	private DefaultSingletonContext singletonContext;

	@BeforeAll
	static void beforeAll() {
		TestUtils.setHereAsPackageReflection();
	}

	@AfterAll
	static void afterAll() {
		TestUtils.resetPackageReflection();
	}

	@BeforeEach
	void beforeEach() {
		this.singletonContext = new DefaultSingletonContext();
	}

	@Test
	@DisplayName("Initialize the context")
	void testInitialize() {
		ScarletContext scarletContext = Mockito.mock(ScarletContext.class);
		Map<String, Object> singletons = this.singletonContext.initialize(scarletContext);
		assertNotNull(singletons);
		assertEquals(2, singletons.size());
		assertTrue(singletons.get(SingletonTestInterface.class.getName()) instanceof SingletonTestImplementation2);
		assertTrue(singletons.get(SingletonTestNotFound.class.getName()) instanceof SingletonTestNotFoundImplementation);
	}

	@Test
	@DisplayName("Create singleton instance")
	void testCreateInstance() {
		InstanceEntry<String, SingletonTestImplementation> entry = this.singletonContext.createInstance("Key", SingletonTestImplementation.class);
		assertEquals("Key", entry.getKey());

		InstanceEntry<String, SingletonTestInterface> entry2 = this.singletonContext.createInstance("Key", SingletonTestInterface.class);
		assertTrue(entry2.getValue() instanceof SingletonTestImplementation2);

		assertThrows(SingletonException.class, () -> this.singletonContext.createInstance("SingletonTestWithoutAnnotation", SingletonTestWithoutAnnotation.class));
	}

	@Test
	@DisplayName("Get singleton by class target or singleton key")
	void testGet() {
		ScarletContext scarletContext = Mockito.mock(ScarletContext.class);
		this.singletonContext.setScarletContext(scarletContext);

		String singletonKey = SingletonTestInterface.class.getName();
		InstanceEntry<String, SingletonTestImplementation> entry = this.singletonContext.createInstance(singletonKey, SingletonTestImplementation.class);
		assertNotNull(entry);
		assertNotNull(entry.getValue());
		assertEquals(singletonKey, entry.getKey());

		when(scarletContext.getInstance(singletonKey)).thenReturn(entry.getValue());
		SingletonTestInterface singletonTestInterface = this.singletonContext.get(SingletonTestInterface.class);
		assertNotNull(singletonTestInterface);
		assertEquals(entry.getValue(), singletonTestInterface);

		SingletonTestImplementation singletonTestImplementation = this.singletonContext.get(SingletonTestImplementation.class);
		assertNotNull(singletonTestImplementation);
		assertEquals(entry.getValue(), singletonTestInterface);

		assertThrows(SingletonException.class, () -> this.singletonContext.get(SingletonTestNotFound.class));
		assertThrows(SingletonException.class, () -> this.singletonContext.get(SingletonTestImplementation2.class, singletonKey));
	}

	@Test
	@DisplayName("Get singleton implementations by reflection")
	void testGetSingletonImplementations() {
		Map<String, Class<?>> singletonClasses = this.singletonContext.getSingletonImplementations();
		assertNotNull(singletonClasses);
		assertEquals(2, singletonClasses.size());
		assertEquals(SingletonTestImplementation2.class, singletonClasses.get(SingletonTestInterface.class.getName()));
		assertEquals(SingletonTestNotFoundImplementation.class, singletonClasses.get(SingletonTestNotFound.class.getName()));
	}

	@Test
	@DisplayName("Some target class has Singleton Annotation")
	void testHasSingletonAnnotation() {
		assertTrue(this.singletonContext.hasSingletonAnnotation(SingletonTestInterface.class));
		assertTrue(this.singletonContext.hasSingletonAnnotation(SingletonTestImplementation.class));
		assertTrue(this.singletonContext.hasSingletonAnnotation(SingletonTestImplementation2.class));
		assertFalse(this.singletonContext.hasSingletonAnnotation(SingletonTestWithoutAnnotation.class));
	}

	@Test
	@DisplayName("Generate singleton key for some class")
	void testGenerateKeyFor() {
		assertEquals(SingletonTestInterface.class.getName(), this.singletonContext.generateKeyFor(SingletonTestInterface.class));

		assertThrows(SingletonException.class, () -> this.singletonContext.generateKeyFor(SingletonTestWithoutAnnotation.class));
	}

	@Singleton
	private interface SingletonTestInterface {}
	private static class SingletonTestImplementation implements SingletonTestInterface {}
	@Priority(2)
	private static class SingletonTestImplementation2 implements  SingletonTestInterface {}
	private static class SingletonTestWithoutAnnotation {}

	@Singleton
	private interface SingletonTestNotFound {}
	private static class SingletonTestNotFoundImplementation implements SingletonTestNotFound {}

}
