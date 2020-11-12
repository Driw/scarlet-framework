package org.diverproject.scarlet.language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;
import java.util.Set;

@DisplayName("Language Unloader")
public class LanguageUnloaderTest {

	@BeforeEach
	public void beforeEach() {
		this.resetLanguageMessages();
	}

	@Test
	@DisplayName("Auto unload all languages")
	public void testAutoUnloadNotSeparated() {
		Set<Wini> winis = LanguageUnloader.autoUnload();
		assertEquals(1, winis.size());

		winis = LanguageUnloader.autoUnload(new File(LanguageUnloader.DEFAULT_FOLDER_PATH));
		assertEquals(1, winis.size());

		winis = LanguageUnloader.autoUnload(new File(LanguageUnloader.DEFAULT_FOLDER_PATH), false);
		assertEquals(1, winis.size());

		winis = LanguageUnloader.autoUnload(new File(LanguageUnloader.DEFAULT_FOLDER_PATH), false, true);
		assertEquals(1, winis.size());

		winis = LanguageUnloader.autoUnload(new File(LanguageUnloader.DEFAULT_FOLDER_PATH), false, true, this.getClass().getPackage().getName());
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

		Section languageTestIni = wini.get(LanguageTestIni.class.getName());
		assertNotNull(languageTestIni);
		assertEquals("The first message", languageTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second message", languageTestIni.get("SECOND_MESSAGE"));
		assertEquals("The third message", languageTestIni.get("THIRD_MESSAGE"));
		assertEquals("The fourth message", languageTestIni.get("FOURTH_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("FIFTH_MESSAGE"));

		assertThrows(LanguageRuntimeException.class, () -> LanguageUnloader.autoUnload(null));
		assertThrows(LanguageRuntimeException.class, () -> LanguageUnloader.autoUnload(new File("unknown-folder")));

		File resources = new File(Objects.requireNonNull(LanguageUnloader.class.getClassLoader().getResource("")).getFile());
		winis = LanguageUnloader.autoUnload(resources, true, true, this.getClass().getPackage().getName());
		assertEquals(2, winis.size());

		wini = this.search(winis, AnotherLanguageTestIni.class.getName());
		assertNotNull(wini);

		anotherLanguageTestIni = wini.get(AnotherLanguageTestIni.class.getName());
		assertNotNull(anotherLanguageTestIni);
		assertEquals("The first another message", anotherLanguageTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second another message", anotherLanguageTestIni.get("SECOND_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("THIRD_MESSAGE"));

		wini = this.search(winis, LanguageTestIni.class.getName());
		assertNotNull(wini);

		languageTestIni = wini.get(LanguageTestIni.class.getName());
		assertNotNull(languageTestIni);
		assertEquals("The first message", languageTestIni.get("FIRST_MESSAGE"));
		assertEquals("The second message", languageTestIni.get("SECOND_MESSAGE"));
		assertEquals("The third message", languageTestIni.get("THIRD_MESSAGE"));
		assertEquals("The fourth message", languageTestIni.get("FOURTH_MESSAGE"));
		assertNull(anotherLanguageTestIni.get("FIFTH_MESSAGE"));

		winis = LanguageUnloader.autoUnload(resources, true, false, this.getClass().getPackage().getName());
		assertEquals(2, winis.size());

		wini = this.search(winis, AnotherLanguageTestIni.class.getName());
		assertNotNull(wini);

		anotherLanguageTestIni = wini.get(AnotherLanguageTestIni.class.getName());
		assertNotNull(anotherLanguageTestIni);
		assertEquals("The first another message", anotherLanguageTestIni.get("1"));
		assertEquals("The second another message", anotherLanguageTestIni.get("2"));
		assertNull(anotherLanguageTestIni.get("3"));

		wini = this.search(winis, LanguageTestIni.class.getName());
		assertNotNull(wini);

		languageTestIni = wini.get(LanguageTestIni.class.getName());
		assertNotNull(languageTestIni);
		assertEquals("The first message", languageTestIni.get("1"));
		assertEquals("The second message", languageTestIni.get("2"));
		assertEquals("The third message", languageTestIni.get("3"));
		assertEquals("The fourth message", languageTestIni.get("4"));
		assertNull(anotherLanguageTestIni.get("5"));
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
