package org.diverproject.scarlet.language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

@DisplayName("Language Loader")
public class LanguageLoaderTest {

	@Test
	@DisplayName("Load language mapper")
	public void testLoadLanguageMapper() throws Exception {
		String filepath = Objects.requireNonNull(LanguageLoaderTest.class.getClassLoader().getResource("language.ini")).getFile();
		File file = new File(filepath);
		List<LanguageMapper> languageMappers = LanguageLoader.loadLanguageMapper(file);
		assertEquals(languageMappers.size(), 2);

		LanguageMapper languageTestIni = this.search(languageMappers, "org.diverproject.scarlet.language.LanguageTestIni");
		assertEquals("org.diverproject.scarlet.language.LanguageTestIni", languageTestIni.getClasspath());
		assertTrue(languageTestIni.getMessages().containsKey("FIRST_MESSAGE"));
		assertTrue(languageTestIni.getMessages().containsKey("SECOND_MESSAGE"));
		assertTrue(languageTestIni.getMessages().containsKey("THIRD_MESSAGE"));
		assertTrue(languageTestIni.getMessages().containsKey("FOURTH_MESSAGE"));
		assertFalse(languageTestIni.getMessages().containsKey("FIFTH_MESSAGE"));
		assertEquals("The first message", languageTestIni.getMessages().get("FIRST_MESSAGE"));
		assertEquals("The second message", languageTestIni.getMessages().get("SECOND_MESSAGE"));
		assertEquals("The third message", languageTestIni.getMessages().get("THIRD_MESSAGE"));
		assertEquals("The fourth message", languageTestIni.getMessages().get("FOURTH_MESSAGE"));
		assertNull(languageTestIni.getMessages().get("FIFTH_MESSAGE"));

		LanguageMapper anotherLanguageTestIni = this.search(languageMappers, "org.diverproject.scarlet.language.AnotherLanguageTestIni");
		assertEquals("org.diverproject.scarlet.language.AnotherLanguageTestIni", anotherLanguageTestIni.getClasspath());
		assertTrue(anotherLanguageTestIni.getMessages().containsKey("FIRST_MESSAGE"));
		assertTrue(anotherLanguageTestIni.getMessages().containsKey("SECOND_MESSAGE"));
		assertFalse(anotherLanguageTestIni.getMessages().containsKey("THIRD_MESSAGE"));
		assertEquals("The first another message", anotherLanguageTestIni.getMessages().get("FIRST_MESSAGE"));
		assertEquals("The second another message", anotherLanguageTestIni.getMessages().get("SECOND_MESSAGE"));
		assertNull(anotherLanguageTestIni.getMessages().get("THIRD_MESSAGE"));
	}

	@Test
	@DisplayName("Load enumeration")
	public void testLoadEnumeration() throws Exception {
		this.resetLanguageMessages();

		String filepath = Objects.requireNonNull(LanguageLoaderTest.class.getClassLoader().getResource("language.ini")).getFile();
		File file = new File(filepath);

		assertEquals(4, LanguageLoader.loadEnumeration(file, LanguageTestIni.class));
		assertEquals("The first message", LanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second message", LanguageTestIni.SECOND_MESSAGE.getFormat());
		assertEquals("The third message", LanguageTestIni.THIRD_MESSAGE.getFormat());
		assertEquals("The fourth message", LanguageTestIni.FOURTH_MESSAGE.getFormat());

		assertEquals(2, LanguageLoader.loadEnumeration(file, AnotherLanguageTestIni.class));
		assertEquals("The first another message", AnotherLanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second another message", AnotherLanguageTestIni.SECOND_MESSAGE.getFormat());
	}

	@Test
	@DisplayName("Load enumerations")
	public void testLoadEnumerations() throws Exception {
		this.resetLanguageMessages();

		String filepath = Objects.requireNonNull(LanguageLoaderTest.class.getClassLoader().getResource("language.ini")).getFile();
		File file = new File(filepath);

		assertEquals(6, LanguageLoader.loadEnumerations(file));
		assertEquals("The first message", LanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second message", LanguageTestIni.SECOND_MESSAGE.getFormat());
		assertEquals("The third message", LanguageTestIni.THIRD_MESSAGE.getFormat());
		assertEquals("The fourth message", LanguageTestIni.FOURTH_MESSAGE.getFormat());
		assertEquals("The first another message", AnotherLanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second another message", AnotherLanguageTestIni.SECOND_MESSAGE.getFormat());
	}

	@Test
	@DisplayName("Load enumerations")
	public void testAutoLoad() throws Exception {
		this.resetLanguageMessages();

		String filepath = Objects.requireNonNull(LanguageLoaderTest.class.getClassLoader().getResource("en-us")).getFile();
		File folder = new File(filepath);

		assertEquals(6, LanguageLoader.autoLoad(folder));
		assertEquals("The first message", LanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second message", LanguageTestIni.SECOND_MESSAGE.getFormat());
		assertEquals("The third message", LanguageTestIni.THIRD_MESSAGE.getFormat());
		assertEquals("The fourth message", LanguageTestIni.FOURTH_MESSAGE.getFormat());
		assertEquals("The first another message", AnotherLanguageTestIni.FIRST_MESSAGE.getFormat());
		assertEquals("The second another message", AnotherLanguageTestIni.SECOND_MESSAGE.getFormat());
	}

	private void resetLanguageMessages() {
		LanguageTestIni.FIRST_MESSAGE.setFormat("The first message");
		LanguageTestIni.SECOND_MESSAGE.setFormat("The second message");
		LanguageTestIni.THIRD_MESSAGE.setFormat("The third message");
		LanguageTestIni.FOURTH_MESSAGE.setFormat("The fourth message");
		AnotherLanguageTestIni.FIRST_MESSAGE.setFormat("The first another message");
		AnotherLanguageTestIni.SECOND_MESSAGE.setFormat("The second another message");
	}

	private LanguageMapper search(List<LanguageMapper> languageMappers, String name) {
		for (LanguageMapper languageMapper : languageMappers)
			if (languageMapper.getClasspath().equals(name))
				return languageMapper;

		return null;
	}
}
