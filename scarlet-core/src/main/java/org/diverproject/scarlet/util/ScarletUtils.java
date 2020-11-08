package org.diverproject.scarlet.util;

import org.diverproject.scarlet.language.Language;

import java.util.Collection;
import java.util.function.BooleanSupplier;

public final class ScarletUtils {

	private ScarletUtils() { }

	public static String nameOf(Object obj) {
		if (obj instanceof Class)
			return StringUtils.getSimpleNameOf(((Class<?>) obj).getName());

		return StringUtils.getSimpleNameOf(obj.getClass().getSimpleName());
	}

	public static <T> T nvl(T value, T nullValue) {
		return value == null ? nullValue : value;
	}

	public static void notANull(Object obj) {
		notANull(obj, (String) null);
	}

	public static void notANull(Object obj, String message) {
		if (obj == null) {
			if (message == null)
				throw new NullPointerException();

			throw new NullPointerException(message);
		}
	}

	public static void notANull(Object obj, Language language) {
		if (obj == null) {
			if (language == null)
				throw new NullPointerException();

			throw new NullPointerException(language.getFormat());
		}
	}

	public static void notANull(Object obj, Language language, Object... args) {
		if (obj == null) {
			if (language == null)
				throw new NullPointerException();

			throw new NullPointerException(String.format(language.getFormat(), args));
		}
	}

	public static int collectionSize(Collection<?> collection) {
		return collection == null ? 0 : collection.size();
	}

	public static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitUntil(BooleanSupplier supplier) {
		waitUntil(supplier, 1, 0);
	}

	public static void waitUntil(BooleanSupplier supplier, int delay) {
		waitUntil(supplier, delay, 0);
	}

	public static void waitUntil(BooleanSupplier supplier, int delay, int times) {
		int waitCount = 0;
		delay = IntegerUtils.capMin(delay, 1);

		while (!supplier.getAsBoolean()) {
			if (times != 0 && waitCount++ == times) {
				break;
			}

			sleep(delay);
		}
	}

}
