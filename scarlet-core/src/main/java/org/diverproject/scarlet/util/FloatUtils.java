package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PARSE;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PATTERN;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FloatUtils {

	private FloatUtils() { }

	public static boolean isSafeFloat(String str) {
		if (NumberUtils.isFloatFormat(str))
			return NumberUtils.getPattern().matcher(str).find();

		return false;
	}

	public static boolean isAllSafeFloat(String[] array) {
		for (String str : array)
			if (!isSafeFloat(str))
				return false;

		return true;
	}

	public static float parseFloat(String str) {
		return parseFloat(str, null);
	}

	public static float parseFloat(String str, Float failValue) {
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, FLOAT_PARSER, str);
		}
	}

	public static Float parseFloatObject(String str) {
		return parseFloatObject(str, null);
	}

	public static Float parseFloatObject(String str, Float failValue) {
		return StringUtils.isEmpty(str) ? null : parseFloat(str, failValue);
	}

	public static float parseFloat(String str, int floatType) {
		return parseFloat(str, floatType, null);
	}

	public static float parseFloat(String str, int floatType, FloatParser floatParser) {
		String raw = str;
		boolean dotType = BitwiseUtils.has(floatType, NumberUtils.DECIMAL_DOT_TYPE);
		boolean commaType = BitwiseUtils.has(floatType, NumberUtils.DECIMAL_COMMA_TYPE);
		Pattern pattern = null;

		if (dotType && commaType) {
			pattern = NumberUtils.PATTERN_ANY;
		} else if (dotType) {
			pattern = NumberUtils.PATTERN_DOT;
		} else if (commaType) {
			pattern = NumberUtils.PATTERN_COMMA;
		}

		if (pattern == null)
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_PATTERN, floatType);

		final Matcher matcher = pattern.matcher(str);

		if (!matcher.find())
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_PARSE, str);

		if (floatParser != null) {
			String signal = matcher.group("signal");

			floatParser.setRaw(raw);
			floatParser.setPositive(!signal.equals("-"));
			floatParser.setExpression(!matcher.group("exponent").isEmpty());
			floatParser.setValue(matcher.group("value"));

			if (floatParser.isExpression()) {
				String exponentSignal = matcher.group("exponentSignal");

				floatParser.setExponentPositive(exponentSignal.isEmpty() || exponentSignal.equals("+"));
				floatParser.setExponent(Integer.parseInt(matcher.group("exponentValue")));
			}
		}

		if (BitwiseUtils.has(floatType, NumberUtils.DECIMAL_COMMA_TYPE))
			raw = raw.replace(",", ".");

		return Float.parseFloat(raw);
	}

	public static float capMin(float value, float minValue) {
		return Math.max(value, minValue);
	}

	public static float capMax(float value, float maxValue) {
		return Math.min(value, maxValue);
	}

	public static float cap(float value, float minValue, float maxValue) {
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(float value, float min) {
		return value >= min;
	}

	public static boolean hasMax(float value, float maxValue) {
		return value <= maxValue;
	}

	public static boolean hasBetween(float value, float minValue, float maxValue) {
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
