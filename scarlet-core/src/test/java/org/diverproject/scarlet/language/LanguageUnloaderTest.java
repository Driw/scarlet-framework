package org.diverproject.scarlet.language;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

@DisplayName("Language Unloader")
public class LanguageUnloaderTest {

	@Test
	@DisplayName("Auto unload without separated")
	public void testAutoUnloadNotSeparated() {
		this.resetLanguageMessages();

		Set<Wini> winis = LanguageUnloader.autoUnload(null, false, true, this.getClass().getPackageName());
		assertEquals(1, winis.size());

		Wini wini = this.search(winis, AnotherLanguageTestIni.class.getName());
		assertNotNull(wini);

		Section anotherLanguageTestIni = wini.get(AnotherLanguageTestIni.class.getName());
		assertNotNull(anotherLanguageTestIni);
		assertEquals("The first another message", anotherLanguageTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second another message", anotherLanguageTestIni.get("SECOND_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("THIRD_MESSAGE"));

		wini = this.search(winis, LanguageTestIni.class.getName());
		assertNotNull(wini);

		Section languateTestIni = wini.get(LanguageTestIni.class.getName());
		assertNotNull(languateTestIni);
		assertEquals("The first message", languateTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second message", languateTestIni.get("SECOND_MESSAGE"));
		assertEquals("The third message", languateTestIni.get("THIRD_MESSAGE"));
		assertEquals("The fourth message", languateTestIni.get("FOURTH_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("FIFTH_MESSAGE"));
	}

	@Test
	@DisplayName("Auto unload separeted")
	public void testAutoUnloadSepareted() {
		this.resetLanguageMessages();

		Set<Wini> winis = LanguageUnloader.autoUnload(null, true, true, this.getClass().getPackageName());
		assertEquals(2, winis.size());

		Wini wini = this.search(winis, AnotherLanguageTestIni.class.getName());
		assertNotNull(wini);

		Section anotherLanguageTestIni = wini.get(AnotherLanguageTestIni.class.getName());
		assertNotNull(anotherLanguageTestIni);
		assertEquals("The first another message", anotherLanguageTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second another message", anotherLanguageTestIni.get("SECOND_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("THIRD_MESSAGE"));

		wini = this.search(winis, LanguageTestIni.class.getName());
		assertNotNull(wini);

		Section languateTestIni = wini.get(LanguageTestIni.class.getName());
		assertNotNull(languateTestIni);
		assertEquals("The first message", languateTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second message", languateTestIni.get("SECOND_MESSAGE"));
		assertEquals("The third message", languateTestIni.get("THIRD_MESSAGE"));
		assertEquals("The fourth message", languateTestIni.get("FOURTH_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("FIFTH_MESSAGE"));
	}

	private Wini search(Set<Wini> winis, String name) {
		for (Wini wini : winis)
			if (wini.containsKey(name))
				return wini;

		return null;
	}

	private void resetLanguageMessages() {
		LanguageTestIni.FIRST_MESSAGE.setFormat("The first message");
		LanguageTestIni.SECOND_MESSAGE.setFormat("The second message");
		LanguageTestIni.THIRD_MESSAGE.setFormat("The third message");
		LanguageTestIni.FOURTH_MESSAGE.setFormat("The fourth message");
		AnotherLanguageTestIni.FIRST_MESSAGE.setFormat("The first another message");
		AnotherLanguageTestIni.SECOND_MESSAGE.setFormat("The second another message");
	}
}
