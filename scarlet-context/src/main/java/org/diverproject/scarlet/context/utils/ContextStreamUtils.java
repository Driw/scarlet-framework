package org.diverproject.scarlet.context.utils;

import org.diverproject.scarlet.context.manager.Manager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ContextStreamUtils {

	private ContextStreamUtils() { }

	public static <K, V> Collector<Pair<K, V>, ?, Map<K, V>> mapPair() {
		return Collectors.toMap(Pair::getFirstValue, Pair::getSecondValue);
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

}
