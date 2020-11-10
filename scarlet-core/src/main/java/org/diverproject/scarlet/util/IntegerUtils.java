package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.INTEGER_PARSER;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

public final class IntegerUtils {

	private IntegerUtils() { }

	public static boolean isInteger(String str) {
		return NumberUtils.hasNumberRange(str, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean isAllInteger(String[] array) {
		for (String str : array)
			if (!isInteger(str))
				return false;

		return true;
	}

	public static int parseInt(String str) {
		return parseInt(str, null);
	}

	public static int parseInt(String str, Integer failValue) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, INTEGER_PARSER, str);
		}
	}

	public static Integer parseIntObject(String str) {
		return parseIntObject(str, null);
	}

	public static Integer parseIntObject(String str, Integer failValue) {
		return StringUtils.isEmpty(str) ? null : parseInt(str, failValue);
	}

	public static int parseUnsignedByte(byte b) {
		return (int) b >= 0 ? (int) b : 256 + (int) b;
	}

	public static int parseUnsignedShort(short b) {
		return (int) b >= 0 ? (int) b : 65536 + (int) b;
	}

	public static int capMin(int value, int minValue) {
		return Math.max(value, minValue);
	}

	public static int capMax(int value, int maxValue) {
		return Math.min(value, maxValue);
	}

	public static int cap(int value, int minValue, int maxValue) {
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(int value, int min) {
		return value >= min;
	}

	public static boolean hasMax(int value, int maxValue) {
		return value <= maxValue;
	}

	public static boolean hasBetween(int value, int minValue, int maxValue) {
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
