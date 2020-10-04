package org.diverproject.scarlet.context.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ContextCollectionUtils {

	public static final int NOT_FOUND = -1;

	private ContextCollectionUtils() { }

	public static <T> Optional<Pair<Integer, T>> search(List<T> list, Function<T, Boolean> searchBy) {
		for (int i = 0; i < list.size(); i++) {
			T element = list.get(i);

			if (searchBy.apply(element)) {
				return Optional.of(new Pair<>(i, element));
			}
		}

		return Optional.empty();
	}
}
