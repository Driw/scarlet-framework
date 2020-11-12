package org.diverproject.scarlet.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public final class BooleanUtils {

	private static final Set<String> FALSE_VALUES;
	private static final Set<String> TRUE_VALUES;

	static {
		FALSE_VALUES = new LinkedHashSet<>(Arrays.asList("false", "no", "0"));
		TRUE_VALUES = new LinkedHashSet<>(Arrays.asList("true", "yes", "1"));
	}

	private BooleanUtils() { }

	public static boolean parseBoolean(String str) {
		return Optional.ofNullable(parseBoolean(str, null))
			.orElseThrow(IllegalArgumentException::new);
	}

	public static Boolean parseBoolean(String str, Boolean failValue) {
		if (TRUE_VALUES.contains(str))
			return true;

		if (FALSE_VALUES.contains(str))
			return false;

		return failValue;
	}

	public static boolean addTrueValue(String trueValue) {
		return TRUE_VALUES.add(trueValue);
	}

	public static boolean addFalseValue(String falseValue) {
		return FALSE_VALUES.add(falseValue);
	}
}
