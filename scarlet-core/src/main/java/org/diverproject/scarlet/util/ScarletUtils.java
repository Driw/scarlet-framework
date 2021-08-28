package org.diverproject.scarlet.util;

import org.diverproject.scarlet.language.Language;

import java.util.Collection;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public final class ScarletUtils {

	private ScarletUtils() { }

	public static String nameOf(Object obj) {
		if (Objects.isNull(obj))
			return null;

		if (obj instanceof Class)
			return StringUtils.getSimpleNameOf(((Class<?>) obj).getName());

		return StringUtils.getSimpleNameOf(obj.getClass().getSimpleName());
	}

	public static <T> T nvl(T value, T nullValue) {
		return Objects.isNull(value) ? nullValue : value;
	}

	public static void nonNull(Object obj) {
		nonNull(obj, (String) null);
	}

	public static void nonNull(Object obj, String message) {
		if (Objects.isNull(obj)) {
			if (Objects.isNull(message))
				throw new NullPointerException();

			throw new NullPointerException(message);
		}
	}

	public static void nonNull(Object obj, Language language) {
		nonNull(obj, language.getFormat());
	}

	public static void nonNull(Object obj, Language language, Object... args) {
		nonNull(obj, String.format(language.getFormat(), args));
	}

	public static int collectionSize(Collection<?> collection) {
		return Objects.isNull(collection) ? 0 : collection.size();
	}

	@SuppressWarnings("squid:S2142")
	public static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw ScarletUtilsError.sleep(e);
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

	public static <T> boolean in(T target, T... values) {
		for (T value : values) {
			if (Objects.equals(target, value))
				return true;
		}

		return false;
	}
}
