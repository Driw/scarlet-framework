package org.diverproject.scarlet.context.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public final class ContextCollectionUtils {

	private ContextCollectionUtils() { }

	public static <T> Optional<Integer> searchOrderIndex(List<T> list, BiPredicate<T, T> orderBy) {
		for (int i = 0; i < list.size(); i++) {
			T next = list.get(i);
			T previous = i == 0 ? null : list.get(i - 1);

			if (orderBy.test(previous, next)) {
				return Optional.of(i);
			}
		}

		return Optional.empty();
	}
}
