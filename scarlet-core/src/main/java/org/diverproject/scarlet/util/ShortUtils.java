package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.SHORT_PARSER;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

public final class ShortUtils {

	private ShortUtils() { }

	public static boolean isShort(String str) {
		return NumberUtils.hasNumberRange(str, Short.MIN_VALUE, Short.MAX_VALUE);
	}

	public static boolean isAllShort(String[] array) {
		for (String str : array)
			if (!isShort(str))
				return false;

		return true;
	}

	public static short parseShort(String str) {
		return parseShort(str, null);
	}

	public static short parseShort(String str, Short failValue) {
		try {
			return Short.parseShort(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, SHORT_PARSER, str);
		}
	}

	public static Short parseShortObject(String str) {
		return parseShortObject(str, null);
	}

	public static Short parseShortObject(String str, Short failValue) {
		return StringUtils.isEmpty(str) ? null : parseShort(str, failValue);
	}

	public static short parseUnsignedByte(byte b) {
		return (short) ((short) b >= 0 ? (short) b : 256 + (short) b);
	}

	public static short capMin(short value, short minValue) {
		return value < minValue ? minValue : value;
	}

	public static short capMax(short value, short maxValue) {
		return value > maxValue ? maxValue : value;
	}

	public static short cap(short value, short minValue, short maxValue) {
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(short value, short min) {
		return value >= min;
	}

	public static boolean hasMax(short value, short maxValue) {
		return value <= maxValue;
	}

	public static boolean hasBetween(short value, short minValue, short maxValue) {
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
