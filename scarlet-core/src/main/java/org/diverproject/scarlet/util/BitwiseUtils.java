package org.diverproject.scarlet.util;

public class BitwiseUtils {

	private BitwiseUtils() { }

	public static boolean has(byte value, byte property) {
		return (value & property) == property;
	}

	public static byte set(byte value, byte property) {
		return (value |= property);
	}

	public static byte remove(byte value, byte property) {
		return (value -= value & property);
	}

	public static boolean has(short value, short property) {
		return (value & property) == property;
	}

	public static short set(short value, short property) {
		return (value |= property);
	}

	public static short remove(short value, short property) {
		return (value -= value & property);
	}

	public static boolean has(int value, int property) {
		return (value & property) == property;
	}

	public static int set(int value, int property) {
		return (value |= property);
	}

	public static int remove(int value, int property) {
		return (value -= value & property);
	}

	public static boolean has(long value, long property) {
		return (value & property) == property;
	}

	public static long set(long value, long property) {
		return (value |= property);
	}

	public static long remove(long value, long property) {
		return (value -= value & property);
	}

	public static String toString(int value, String[] propertys) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < propertys.length; i++)
			if (has(value, 1 << i))
				builder.append(propertys[i] + "|");

		return builder.length() == 0 ? "" : StringUtils.substr(builder.toString(), 0, -1);
	}
}
