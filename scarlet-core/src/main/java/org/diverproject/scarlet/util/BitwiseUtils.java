package org.diverproject.scarlet.util;

public final class BitwiseUtils {

	private BitwiseUtils() { }

	public static boolean has(byte value, byte property) {
		return (value & property) == property;
	}

	@SuppressWarnings("squid:S1121")
	public static byte set(byte value, byte property) {
		return (value |= property);
	}

	@SuppressWarnings("squid:S1121")
	public static byte remove(byte value, byte property) {
		return (value -= value & property);
	}

	public static boolean has(short value, short property) {
		return (value & property) == property;
	}

	@SuppressWarnings("squid:S1121")
	public static short set(short value, short property) {
		return (value |= property);
	}

	@SuppressWarnings("squid:S1121")
	public static short remove(short value, short property) {
		return (value -= value & property);
	}

	public static boolean has(int value, int property) {
		return (value & property) == property;
	}

	@SuppressWarnings("squid:S1121")
	public static int set(int value, int property) {
		return (value |= property);
	}

	@SuppressWarnings("squid:S1121")
	public static int remove(int value, int property) {
		return (value -= value & property);
	}

	public static boolean has(long value, long property) {
		return (value & property) == property;
	}

	@SuppressWarnings("squid:S1121")
	public static long set(long value, long property) {
		return (value |= property);
	}

	@SuppressWarnings("squid:S1121")
	public static long remove(long value, long property) {
		return (value -= value & property);
	}

	public static String toString(long value, String[] properties, String[] defaultProperties) {
		String separator = "|";
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < defaultProperties.length || i < properties.length; i++)
			if (has(value, 1L << (long) i)) {
				if (i < properties.length)
					builder.append(properties[i]).append(separator);
				else
					builder.append(defaultProperties[i]).append(separator);
			}

		return builder.length() == 0 ? "" : StringUtils.substr(builder.toString(), 0, -separator.length());
	}

}
