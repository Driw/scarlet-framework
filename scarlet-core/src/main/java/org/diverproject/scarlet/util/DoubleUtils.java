package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PARSE;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PATTERN;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DoubleUtils {

	private DoubleUtils() { }

	public static boolean isSafeDouble(String str) {
		if (NumberUtils.isFloatFormat(str))
			return NumberUtils.getPattern().matcher(str).find();

		return false;
	}

	public static boolean isAllSafeDouble(String[] array) {
		for (String str : array)
			if (!isSafeDouble(str))
				return false;

		return true;
	}

	public static double parseDouble(String str) {
		return parseDouble(str, null);
	}

	public static double parseDouble(String str, Double failValue) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, DOUBLE_PARSER_PARSE, str);
		}
	}

	public static Double parseDoubleObject(String str) {
		return parseDoubleObject(str, null);
	}

	public static Double parseDoubleObject(String str, Double failValue) {
		return StringUtils.isEmpty(str) ? null : parseDouble(str, failValue);
	}

	public static double parseDouble(String str, int doubleType) {
		return parseDouble(str, doubleType, null);
	}

	public static double parseDouble(String str, int doubleType, DoubleParser doubleParser) {
		String raw = str;
		boolean dotType = BitwiseUtils.has(doubleType, NumberUtils.DECIMAL_DOT_TYPE);
		boolean commaType = BitwiseUtils.has(doubleType, NumberUtils.DECIMAL_COMMA_TYPE);
		Pattern pattern = null;

		if (dotType && commaType) {
			pattern = NumberUtils.PATTERN_ANY;
		} else if (dotType) {
			pattern = NumberUtils.PATTERN_DOT;
		} else if (commaType) {
			pattern = NumberUtils.PATTERN_COMMA;
		}

		if (pattern == null)
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PATTERN, doubleType);

		final Matcher matcher = pattern.matcher(str);

		if (!matcher.find())
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PARSE, str);

		if (doubleParser != null) {
			String signal = matcher.group("signal");

			doubleParser.setRaw(raw);
			doubleParser.setPositive(!signal.equals("-"));
			doubleParser.setExpression(!matcher.group("exponent").isEmpty());
			doubleParser.setValue(matcher.group("value"));

			if (doubleParser.isExpression()) {
				String exponentSignal = matcher.group("exponentSignal");

				doubleParser.setExponentPositive(exponentSignal.isEmpty() || exponentSignal.equals("+"));
				doubleParser.setExponent(Integer.parseInt(matcher.group("exponentValue")));
			}
		}

		if (BitwiseUtils.has(doubleType, NumberUtils.DECIMAL_COMMA_TYPE))
			raw = raw.replace(",", ".");

		return Double.parseDouble(raw);
	}

	public static double capMin(double value, double minValue) {
		return Math.max(value, minValue);
	}

	public static double capMax(double value, double maxValue) {
		return Math.min(value, maxValue);
	}

	public static double cap(double value, double minValue, double maxValue) {
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(double value, double min) {
		return value >= min;
	}

	public static boolean hasMax(double value, double maxValue) {
		return value <= maxValue;
	}

	public static boolean hasBetween(double value, double minValue, double maxValue) {
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
