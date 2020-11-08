package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

class ReflectionConfigTest {

	@BeforeEach
	public void beforeEach() {
		ReflectionConfig.setPackage(null);
	}

	@Test
	void getReflections() {
		assertNotNull(ReflectionConfig.getReflections());
		assertTrue(ReflectionConfig.getReflections().getConfiguration().getInputsFilter().test("RootClass"));
		new Reflections(this.getClass().getPackage().getName()).getConfiguration().getInputsFilter().test("org.diverproject.scarlet.context.reflection");
	}

	@Test
	void setPackage() {
		String packageName = this.getClass().getPackage().getName();
		ReflectionConfig.setPackage(packageName);
		assertNotNull(ReflectionConfig.getReflections());
		assertFalse(ReflectionConfig.getReflections().getConfiguration().getInputsFilter().test("RootClass"));
		assertTrue(ReflectionConfig.getReflections().getConfiguration().getInputsFilter().test(packageName.concat(".LeafClass")));
	}

}
