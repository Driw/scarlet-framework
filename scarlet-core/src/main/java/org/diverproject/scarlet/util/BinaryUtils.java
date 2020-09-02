package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_INT_NULL;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_INT_SIZE;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_LONG_NULL;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_LONG_SIZE;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_SHORT_NULL;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.NEW_SHORT_SIZE;
import static org.diverproject.scarlet.util.language.BinaryUtilsLanguage.SHIFT_OFFSET;

import org.diverproject.scarlet.util.exceptions.BinaryUtilsRuntimeException;

public class BinaryUtils
{
	public static final byte ZERO = 0x00;

	public static final int BYTE_BITS = Byte.SIZE;
	public static final int SHORT_BITS = Short.SIZE;
	public static final int INT_BITS = Integer.SIZE;
	public static final int LONG_BITS = Long.SIZE;
	public static final int FLOAT_BITS = Float.SIZE;
	public static final int DOUBLE_BITS = Double.SIZE;

	public static final int BYTE_BYTES = Byte.BYTES;
	public static final int SHORT_BYTES = Short.BYTES;
	public static final int INT_BYTES = Integer.BYTES;
	public static final int LONG_BYTES = Long.BYTES;
	public static final int FLOAT_BYTES = Float.BYTES;
	public static final int DOUBLE_BYTES = Double.BYTES;

	private BinaryUtils() { }

	public static int shift(boolean reverse, int offset, int bytes, int dataBytes)
	{
		if (offset >= dataBytes)
			throw new BinaryUtilsRuntimeException(SHIFT_OFFSET, offset, dataBytes);

		int shift = bytes - 1 - offset + dataBytes - bytes;
		return (BYTE_BITS * (reverse ? bytes - shift - 1 : shift));
	}

	public static short newShort(byte... bytes)
	{
		return newShort(false, bytes);
	}

	public static short newShort(boolean reverse, byte... bytes)
	{
		if (bytes == null)
			throw new BinaryUtilsRuntimeException(NEW_SHORT_NULL);

		if (bytes.length < 0 || bytes.length > SHORT_BYTES)
			throw new BinaryUtilsRuntimeException(NEW_SHORT_SIZE, SHORT_BYTES, bytes.length);

		short value = 0;

		for (int i = 0; i < bytes.length; i++)
		{
			if (bytes[i] == 0)
				continue;

			value |=  (bytes[i] & 255) << shift(reverse, i, SHORT_BYTES, bytes.length);
		}

		return value;
	}

	public static int newInt(byte... bytes)
	{
		return newInt(false, bytes);
	}

	public static int newInt(boolean reverse, byte... bytes)
	{
		if (bytes == null)
			throw new BinaryUtilsRuntimeException(NEW_INT_NULL);

		if (bytes.length < 0 || bytes.length > INT_BYTES)
			throw new BinaryUtilsRuntimeException(NEW_INT_SIZE, INT_BYTES, bytes.length);

		int value = 0;

		for (int i = 0; i < bytes.length; i++)
		{
			if (bytes[i] == 0)
				continue;

			value |=  (bytes[i] & 255) << shift(reverse, i, INT_BYTES, bytes.length);
		}

		return value;
	}

	public static long newLong(byte... bytes)
	{
		return newLong(false, bytes);
	}

	public static long newLong(boolean reverse, byte... bytes)
	{
		if (bytes == null)
			throw new BinaryUtilsRuntimeException(NEW_LONG_NULL);

		if (bytes.length < 0 || bytes.length > LONG_BYTES)
			throw new BinaryUtilsRuntimeException(NEW_LONG_SIZE, LONG_BYTES, bytes.length);

		long value = 0;

		for (int i = 0; i < bytes.length; i++)
		{
			if (bytes[i] == 0)
				continue;

			value |=  (bytes[i] & 255L) << shift(reverse, i, LONG_BYTES, bytes.length);
		}

		return value;
	}
}
