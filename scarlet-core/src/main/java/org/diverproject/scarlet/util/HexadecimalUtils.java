package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_BYTE;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_BYTE_FORMAT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_INT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_INT_FORMAT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_LONG;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_LONG_FORMAT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_SHORT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.PARSE_HEX_SHORT_FORMAT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.TO_HEX_NEGATIVE_BYTE;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.TO_HEX_NEGATIVE_INT;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.TO_HEX_NEGATIVE_LONG;
import static org.diverproject.scarlet.util.language.HexadecimalUtilsLanguage.TO_HEX_NEGATIVE_SHORT;

import org.diverproject.scarlet.util.exceptions.HexadecimalUtilsRuntimeException;

public final class HexadecimalUtils {

	public static final String HEXADECIMAL_REGEX = "^0[xX](?<value>[0-9a-fA-F]+)$";
	public static final int HEX_BYTE_SIZE = Byte.BYTES * 2;
	public static final int HEX_SHORT_SIZE = Short.BYTES * 2;
	public static final int HEX_INT_SIZE = Integer.BYTES * 2;
	public static final int HEX_LONG_SIZE = Long.BYTES * 2;
	public static final int HEX_CHAR_SIZE = Character.BYTES * 2;

	public static final int TO_HEX_ZEROS = 1;
	public static final int TO_HEX_X = 2;
	public static final int TO_HEX_BEAUTIFUL = TO_HEX_ZEROS | TO_HEX_X;

	private HexadecimalUtils() { }

	public static boolean hasHexFormat(String str) {
		if (str == null || str.length() == 0)
			return false;

		if (str.startsWith("0x"))
			str = str.substring(2);

		for (char c : str.toLowerCase().toCharArray())
			if (!((c >= 'a' && c <= 'f') || (c >= '0' && c <= '9')))
				return false;

		return true;
	}

	public static boolean isHex(String str, int bytes) {
		if (!hasHexFormat(str))
			return false;

		if (str.startsWith("0x"))
			str = str.substring(2);

		str = StringUtils.trimLeft(str, "0");

		return str.isEmpty() || (Math.ceil(str.length() / 2D)) <= bytes;
	}

	public static boolean isHexByte(String str) {
		return isHex(str, Byte.BYTES);
	}

	public static boolean isHexShort(String str) {
		return isHex(str, Short.BYTES);
	}

	public static boolean isHexInt(String str) {
		return isHex(str, Integer.BYTES);
	}

	public static boolean isHexLong(String str) {
		return isHex(str, Long.BYTES);
	}

	public static String clearHex(String str) {
		return str.startsWith("0x") ? str.substring(2) : str;
	}

	public static String toHex(byte value) {
		if (value < 0)
			throw new HexadecimalUtilsRuntimeException(TO_HEX_NEGATIVE_BYTE, value);

		return Integer.toHexString(value).toUpperCase();
	}

	public static String toHex(short value) {
		if (value < 0)
			throw new HexadecimalUtilsRuntimeException(TO_HEX_NEGATIVE_SHORT, value);

		return Integer.toHexString(value).toUpperCase();
	}

	public static String toHex(int value) {
		if (value < 0)
			throw new HexadecimalUtilsRuntimeException(TO_HEX_NEGATIVE_INT, value);

		return Integer.toHexString(value).toUpperCase();
	}

	public static String toHex(long value) {
		if (value < 0)
			throw new HexadecimalUtilsRuntimeException(TO_HEX_NEGATIVE_LONG, value);

		return Long.toHexString(value).toUpperCase();
	}

	public static String toHex(char value) {
		return Integer.toHexString(value).toUpperCase();
	}

	public static String toHex(byte value, int formatter) {
		String hex = toHex(value);

		if (BitwiseUtils.has(formatter, TO_HEX_ZEROS))
			hex = StringUtils.leftPadLength(hex, "0", HEX_BYTE_SIZE);

		if (BitwiseUtils.has(formatter, TO_HEX_X))
			hex = "0x" + hex;

		return hex;
	}

	public static String toHex(short value, int formatter) {
		String hex = toHex(value);

		if (BitwiseUtils.has(formatter, TO_HEX_ZEROS))
			hex = StringUtils.leftPadLength(hex, "0", HEX_SHORT_SIZE);

		if (BitwiseUtils.has(formatter, TO_HEX_X))
			hex = "0x" + hex;

		return hex;
	}

	public static String toHex(int value, int formatter) {
		String hex = toHex(value);

		if (BitwiseUtils.has(formatter, TO_HEX_ZEROS))
			hex = StringUtils.leftPadLength(hex, "0", HEX_INT_SIZE);

		if (BitwiseUtils.has(formatter, TO_HEX_X))
			hex = "0x" + hex;

		return hex;
	}

	public static String toHex(long value, int formatter) {
		String hex = toHex(value);

		if (BitwiseUtils.has(formatter, TO_HEX_ZEROS))
			hex = StringUtils.leftPadLength(hex, "0", HEX_LONG_SIZE);

		if (BitwiseUtils.has(formatter, TO_HEX_X))
			hex = "0x" + hex;

		return hex;
	}

	public static byte parseHexByte(String hex) {
		if (!isHex(hex, HEX_BYTE_SIZE))
			throw new HexadecimalUtilsRuntimeException(PARSE_HEX_BYTE, hex);

		try {
			return Byte.parseByte(clearHex(hex), 16);
		} catch (NumberFormatException e) {
			throw new HexadecimalUtilsRuntimeException(e, PARSE_HEX_BYTE_FORMAT, hex);
		}
	}

	public static short parseHexShort(String hex) {
		if (!isHex(hex, HEX_SHORT_SIZE))
			throw new HexadecimalUtilsRuntimeException(PARSE_HEX_SHORT, hex);

		try {
			return Short.parseShort(clearHex(hex), 16);
		} catch (NumberFormatException e) {
			throw new HexadecimalUtilsRuntimeException(e, PARSE_HEX_SHORT_FORMAT, hex);
		}
	}

	public static int parseHexInt(String hex) {
		if (!isHex(hex, HEX_INT_SIZE))
			throw new HexadecimalUtilsRuntimeException(PARSE_HEX_INT, hex);

		try {
			return Integer.parseInt(clearHex(hex), 16);
		} catch (NumberFormatException e) {
			throw new HexadecimalUtilsRuntimeException(e, PARSE_HEX_INT_FORMAT, hex);
		}
	}

	public static long parseHexLong(String hex) {
		if (!isHex(hex, HEX_LONG_SIZE))
			throw new HexadecimalUtilsRuntimeException(PARSE_HEX_LONG, hex);

		try {
			return Long.parseLong(clearHex(hex), 16);
		} catch (NumberFormatException e) {
			throw new HexadecimalUtilsRuntimeException(e, PARSE_HEX_LONG_FORMAT, hex);
		}
	}
}
