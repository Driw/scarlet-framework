package org.diverproject.scarlet.context.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diverproject.scarlet.context.Priority;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtilsTest {

	@Test
	@DisplayName("Compare by Priority Annotation")
	public void compareByPriorityAnnotation() {
		List<Class<?>> classes = Stream.of(FirstPriority.class, SecondPriority.class)
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.collect(Collectors.toList());
		assertEquals(classes.size(), 2);
		assertEquals(classes.get(0), SecondPriority.class);
		assertEquals(classes.get(1), FirstPriority.class);

		classes = Stream.of(SecondPriority.class, FirstPriority.class)
			.sorted(ReflectionUtils.compareByPriorityAnnotation())
			.collect(Collectors.toList());
		assertEquals(classes.size(), 2);
		assertEquals(classes.get(0), SecondPriority.class);
		assertEquals(classes.get(1), FirstPriority.class);
	}

	@Test
	@DisplayName("Compare by Priority Annotation")
	public void maxPriorityAnnotation() {
		Optional<Class<?>> optional = Stream.of(FirstPriority.class, SecondPriority.class)
			.max(ReflectionUtils.maxPriorityAnnotation());
		assertEquals(SecondPriority.class, optional.get());
	}

	@Test
	@DisplayName("Get a Empty Constructor")
	public void getAEmptyConstructor() {
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(NonConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PackageConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PrivateConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(ProtectedConstructor.class));
		assertNotNull(ReflectionUtils.getEmptyConstructorOf(PublicConstructor.class));
		assertThrows(ReflectionException.class, () -> ReflectionUtils.getEmptyConstructorOf(NonEmptyConstructor.class));
	}

	@Test
	@DisplayName("Create Instance of Empty Constructor")
	public void createInstanceOfEmptyConstructor() {
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(NonConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PackageConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PrivateConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(ProtectedConstructor.class));
		assertNotNull(ReflectionUtils.createInstanceOfEmptyConstructor(PublicConstructor.class));
		assertThrows(ReflectionException.class, () -> ReflectionUtils.createInstanceOfEmptyConstructor(NonEmptyConstructor.class));
	}

	@Priority(1)
	private static class FirstPriority {
	}

	@Priority(2)
	private static class SecondPriority {
	}

	private static class NonConstructor {
	}

	private static class PackageConstructor {
		PackageConstructor() {
		}

	}
	private static class PrivateConstructor {
		private PrivateConstructor() {
		}

	}
	private static class ProtectedConstructor {
		protected ProtectedConstructor() {
		}

	}
	private static class PublicConstructor {
		public PublicConstructor() {
		}

	}

	private static class NonEmptyConstructor {
		public NonEmptyConstructor(Object... args) {
		}
	}
}
