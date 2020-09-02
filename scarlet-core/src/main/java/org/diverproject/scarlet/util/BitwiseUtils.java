package org.diverproject.scarlet.util;

public class BitwiseUtils
{
	public static boolean has(byte value, byte propertie)
	{
		return (value & propertie) == propertie;
	}

	public static byte set(byte value, byte propertie)
	{
		return (value |= propertie);
	}

	public static byte remove(byte value, byte propertie)
	{
		return (value -= value & propertie);
	}

	public static boolean has(short value, short propertie)
	{
		return (value & propertie) == propertie;
	}

	public static short set(short value, short propertie)
	{
		return (value |= propertie);
	}

	public static short remove(short value, short propertie)
	{
		return (value -= value & propertie);
	}

	public static boolean has(int value, int propertie)
	{
		return (value & propertie) == propertie;
	}

	public static int set(int value, int propertie)
	{
		return (value |= propertie);
	}

	public static int remove(int value, int propertie)
	{
		return (value -= value & propertie);
	}

	public static boolean has(long value, long propertie)
	{
		return (value & propertie) == propertie;
	}

	public static long set(long value, long propertie)
	{
		return (value |= propertie);
	}

	public static long remove(long value, long propertie)
	{
		return (value -= value & propertie);
	}

	public static String toString(int value, String[] properties)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < properties.length; i++)
			if (has(value, 1 << i))
				builder.append(properties[i]+ "|");

		return builder.length() == 0 ? "" : StringUtils.substr(builder.toString(), 0, -1);
	}
}
