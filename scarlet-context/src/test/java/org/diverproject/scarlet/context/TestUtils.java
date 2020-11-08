package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.reflection.ReflectionInstanceUtils;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class TestUtils {

	private static MockedStatic<ReflectionInstanceUtils> reflectionInstanceUtils;

	private TestUtils() { }

	public static String[] contextArguments() {
		return new String[] { "managerContextParalleled" };
	}

	public static <T, R> boolean containsAll(Collection<T> collection, Function<T, R> mapFunction, Collection<R> collectionContains) {
		return collection.stream()
			.map(mapFunction)
			.collect(Collectors.toList())
			.containsAll(collectionContains);
	}

	@SafeVarargs
	public static <T, R> boolean containsAll(Collection<T> collection, Function<T, R> mapFunction, R... collectionContains) {
		return containsAll(collection, mapFunction, Arrays.asList(collectionContains));
	}

	public static void initialize() {
		if (Objects.nonNull(reflectionInstanceUtils)) reflectionInstanceUtils = mockReflectionInstanceUtils();
	}

	public static MockedStatic<ReflectionInstanceUtils> mockReflectionInstanceUtils() {
		return Mockito.mockStatic(ReflectionInstanceUtils.class, (Answer<Object>) invocation -> {
			if (invocation.getMethod().getName().equals("getInstanceOf")) {
				Class<?> type = invocation.getArgument(0);
				return Mockito.mock(type);
			}

			return null;
		});
	}

}
