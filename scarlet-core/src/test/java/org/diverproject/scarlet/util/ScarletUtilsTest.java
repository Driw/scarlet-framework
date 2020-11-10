package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@DisplayName("Scarlet Utils")
public class ScarletUtilsTest {

	@Test
	@DisplayName("Name of Object or Class")
	public void testNameOf() {
		assertEquals("Object", ScarletUtils.nameOf(new Object()));
		assertEquals("Object", ScarletUtils.nameOf(Object.class));
	}

	@Test
	@DisplayName("NVL")
	public void testNvl() {
		assertEquals(1, ScarletUtils.nvl(1, 1));
		assertEquals(1, ScarletUtils.nvl(1, null));
		assertEquals(1, ScarletUtils.nvl(null, 1));
		assertNull(ScarletUtils.nvl(null, null));
	}

	@Test
	@DisplayName("Not null with and without message")
	public void testNotNull() {
		assertThrows(NullPointerException.class, () -> ScarletUtils.nonNull(null));
		assertThrows(NullPointerException.class, () -> ScarletUtils.nonNull(null, "Test a null nonNull"));
		assertThrows(NullPointerException.class, () -> ScarletUtils.nonNull(null, new NullLanguage(1, "Test a null nonNull")));
		assertThrows(NullPointerException.class, () -> ScarletUtils.nonNull(null, new NullLanguage(1, "Test a null nonNull %s"), "format message"));
	}

	@Test
	void testCollectionSize() {
		assertEquals(0, ScarletUtils.collectionSize(null));
		assertEquals(0, ScarletUtils.collectionSize(Collections.emptyList()));
		assertEquals(3, ScarletUtils.collectionSize(Arrays.asList(1, 2, 3)));
	}

	@Test
	void testSleep() {
		AtomicBoolean threadInterrupted = new AtomicBoolean(false);
		Thread thread = new Thread(() -> {
			ScarletUtils.waitUntil(threadInterrupted::get);
			assertThrows(ScarletRuntimeException.class, () -> ScarletUtils.sleep(1));
		});
		thread.start();
		thread.interrupt();
		assertDoesNotThrow(() -> ScarletUtils.sleep(10));
		threadInterrupted.set(true);
	}

	@Test
	void testWaitUntil() {
		AtomicBoolean condition = new AtomicBoolean(true);
		assertDoesNotThrow(() -> ScarletUtils.waitUntil(condition::get));
		assertDoesNotThrow(() -> ScarletUtils.waitUntil(condition::get, 1));
		assertDoesNotThrow(() -> ScarletUtils.waitUntil(condition::get, 1, 2));

		Thread thread = new Thread(() -> ScarletUtils.sleep(1000));
		thread.start();
		assertDoesNotThrow(() -> ScarletUtils.waitUntil(() -> !thread.isAlive(), 1, 3));
	}

	@Data
	@AllArgsConstructor
	private static class NullLanguage implements Language {
		private int code;
		private String format;
	}

}
