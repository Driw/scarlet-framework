package org.diverproject.scarlet.context;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestUtils {

	private TestUtils() {
	}

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

}
