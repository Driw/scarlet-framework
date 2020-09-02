package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PARSE;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PATTERN;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatUtils extends NumberUtils
{
	private FloatUtils() { }

	public static boolean isSafeFloat(String str)
	{
		if (!isFloatFormat(str))
			return false;

		return getPattern().matcher(str).find();
	}

	public static boolean isAllSafeFloat(String[] array)
	{
		for (String str : array)
			if (!isSafeFloat(str))
				return false;

		return true;
	}

	public static float parseFloat(String str)
	{
		return parseFloat(str, null);
	}

	public static float parseFloat(String str, Float failValue)
	{
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, FLOAT_PARSER, str);
		}
	}

	public static float parseFloatObject(String str)
	{
		return parseFloatObject(str, null);
	}

	public static float parseFloatObject(String str, Float failValue)
	{
		return StringUtils.isEmpty(str) ? null : parseFloat(str, failValue);
	}

	public static float parseFloat(String str, int floatType)
	{
		return parseFloat(str, floatType, null);
	}

	public static float parseFloat(String str, int floatType, FloatParser floatParser)
	{
		String raw = str;

		Pattern pattern = (
			BitwiseUtils.has(floatType, FloatUtils.DECIMAL_DOT_TYPE) &&
			BitwiseUtils.has(floatType, FloatUtils.DECIMAL_COMMA_TYPE) ? (PATTERN_ANY) : (
				BitwiseUtils.has(floatType, FloatUtils.DECIMAL_DOT_TYPE) ? (PATTERN_DOT) : (
					BitwiseUtils.has(floatType, FloatUtils.DECIMAL_COMMA_TYPE) ? (PATTERN_COMMA) :
						null
				)
			)
		);

		if (pattern == null)
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_PATTERN, floatType);

		final Matcher matcher = pattern.matcher(str);

		if (!matcher.find())
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_PARSE, str);

		if (floatParser != null)
		{
			String signal = matcher.group("signal");

			floatParser.setRaw(raw);
			floatParser.setPositive(signal.isEmpty() || signal.equals("+"));
			floatParser.setExpression(!matcher.group("expoent").isEmpty());
			floatParser.setValue(matcher.group("value"));

			if (floatParser.isExpression())
			{
				String expoentSignal = matcher.group("expoentSignal");

				floatParser.setExpoentPositive(expoentSignal.isEmpty() || expoentSignal.equals("+"));
				floatParser.setExpoent(Integer.parseInt(matcher.group("expoentValue")));
			}
		}

		if (BitwiseUtils.has(floatType, FloatUtils.DECIMAL_COMMA_TYPE))
			raw = raw.replace(",", ".");

		return Float.parseFloat(raw);
	}

	public static float capMin(float value, float minValue)
	{
		return value < minValue ? minValue : value;
	}

	public static float capMax(float value, float maxValue)
	{
		return value > maxValue ? maxValue : value;
	}

	public static float cap(float value, float minValue, float maxValue)
	{
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(float value, float min)
	{
		return value >= min;
	}

	public static boolean hasMax(float value, float maxValue)
	{
		return value <= maxValue;
	}

	public static boolean hasBetween(float value, float minValue, float maxValue)
	{
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
