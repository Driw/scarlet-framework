package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.SHORT_PARSER;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

public class ByteUtils extends NumberUtils
{
	private ByteUtils() { }

	public static boolean isByte(String str)
	{
		return hasNumberRange(str, Byte.MIN_VALUE, Byte.MAX_VALUE);
	}

	public static boolean isAllByte(String[] array)
	{
		for (String str : array)
			if (!isByte(str))
				return false;

		return true;
	}

	public static byte parseByte(String str)
	{
		return parseByte(str, null);
	}

	public static byte parseByte(String str, Byte failValue)
	{
		try {
			return Byte.parseByte(str);
		} catch (NumberFormatException e) {
			if (failValue != null)
				return failValue;
			throw new NumberUtilsRuntimeException(e, SHORT_PARSER, str);
		}
	}

	public static Byte parseByteObject(String str)
	{
		return parseByteObject(str, null);
	}

	public static Byte parseByteObject(String str, Byte failValue)
	{
		return StringUtils.isEmpty(str) ? null : parseByte(str, failValue);
	}

	public static byte capMin(byte value, byte minValue)
	{
		return value < minValue ? minValue : value;
	}

	public static byte capMax(byte value, byte maxValue)
	{
		return value > maxValue ? maxValue : value;
	}

	public static byte cap(byte value, byte minValue, byte maxValue)
	{
		return capMin(capMax(value, maxValue), minValue);
	}

	public static boolean hasMin(byte value, byte min)
	{
		return value >= min;
	}

	public static boolean hasMax(byte value, byte maxValue)
	{
		return value <= maxValue;
	}

	public static boolean hasBetween(byte value, byte minValue, byte maxValue)
	{
		return hasMin(value, minValue) && hasMax(value, maxValue);
	}
}
