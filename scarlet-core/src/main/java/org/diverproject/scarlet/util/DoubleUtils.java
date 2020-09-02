package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PARSE;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PATTERN;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleUtils extends NumberUtils
{
	private DoubleUtils() { }

	public static boolean isSafeDouble(String str)
	{
		if (!isFloatFormat(str))
			return false;

		return getPattern().matcher(str).find();
	}

	public static boolean isAllSafeDouble(String[] array)
	{
		for (String str : array)
			if (!isSafeDouble(str))
				return false;

		return true;
	}

	public static double parseDouble(String str)
	{
		return parseDouble(str, null);
	}

	public static double parseDouble(String str, Double failValue)
	{
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, DOUBLE_PARSER_PARSE, str);
		}
	}

	public static double parseDoubleObject(String str)
	{
		return parseDoubleObject(str, null);
	}

	public static double parseDoubleObject(String str, Double failValue)
	{
		return StringUtils.isEmpty(str) ? null : parseDouble(str, failValue);
	}

	public static double parseDouble(String str, int doubleType)
	{
		return parseDouble(str, doubleType, null);
	}

	public static double parseDouble(String str, int doubleType, DoubleParser doubleParser)
	{
		String raw = str;

		Pattern pattern = (
			BitwiseUtils.has(doubleType, DoubleUtils.DECIMAL_DOT_TYPE) &&
			BitwiseUtils.has(doubleType, DoubleUtils.DECIMAL_COMMA_TYPE) ? (PATTERN_ANY) : (
				BitwiseUtils.has(doubleType, DoubleUtils.DECIMAL_DOT_TYPE) ? (PATTERN_DOT) : (
					BitwiseUtils.has(doubleType, DoubleUtils.DECIMAL_COMMA_TYPE) ? (PATTERN_COMMA) :
						null
				)
			)
		);

		if (pattern == null)
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PATTERN, doubleType);

		final Matcher matcher = pattern.matcher(str);

		if (!matcher.find())
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PARSE, str);

		if (doubleParser != null)
		{
			String signal = matcher.group("signal");

			doubleParser.setRaw(raw);
			doubleParser.setPositive(signal.isEmpty() || signal.equals("+"));
			doubleParser.setExpression(!matcher.group("expoent").isEmpty());
			doubleParser.setValue(matcher.group("value"));

			if (doubleParser.isExpression())
			{
				String expoentSignal = matcher.group("expoentSignal");

				doubleParser.setExpoentPositive(expoentSignal.isEmpty() || expoentSignal.equals("+"));
				doubleParser.setExpoent(Integer.parseInt(matcher.group("expoentValue")));
			}
		}

		if (BitwiseUtils.has(doubleType, DoubleUtils.DECIMAL_COMMA_TYPE))
			raw = raw.replace(",", ".");

		return Double.parseDouble(raw);
	}

	public static double capMin(double value, double minValue)
	{
		return value < minValue ? minValue : value;
	}

	public static double capMax(double value, double maxValue)
	{
		return value > maxValue ? maxValue : value;
	}

	public static double cap(double value, double minValue, double maxValue)
	{
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(double value, double min)
	{
		return value >= min;
	}

	public static boolean hasMax(double value, double maxValue)
	{
		return value <= maxValue;
	}

	public static boolean hasBetween(double value, double minValue, double maxValue)
	{
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
