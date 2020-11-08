package org.diverproject.scarlet.language;

import static org.diverproject.scarlet.util.StringUtils.nvl;

import org.ini4j.Wini;
import org.reflections.Reflections;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LanguageUnloader {

	public static final String DEFAULT_FOLDER_PATH = Objects.requireNonNull(LanguageUnloader.class.getClassLoader().getResource("")).getFile();
	private static final File DEFAULT_FOLDER = new File(DEFAULT_FOLDER_PATH);
	private static final boolean DEFAULT_SEPARATED = false;
	private static final boolean DEFAULT_NAMED = true;
	private static final String DEFAULT_PACKAGE_NAME = "";

	private LanguageUnloader() { }

	public static Set<Wini> autoUnload() {
		return autoUnload(DEFAULT_FOLDER, DEFAULT_SEPARATED, DEFAULT_NAMED, DEFAULT_PACKAGE_NAME);
	}

	public static Set<Wini> autoUnload(File folder) {
		return autoUnload(folder, DEFAULT_SEPARATED, DEFAULT_NAMED, DEFAULT_PACKAGE_NAME);
	}

	public static Set<Wini> autoUnload(File folder, boolean separated) {
		return autoUnload(folder, separated, DEFAULT_NAMED, DEFAULT_PACKAGE_NAME);
	}

	public static Set<Wini> autoUnload(File folder, boolean separated, boolean named) {
		return autoUnload(folder, separated, named, DEFAULT_PACKAGE_NAME);
	}

	public static Set<Wini> autoUnload(File folder, boolean separated, boolean named, String packageName) {
		if (Objects.isNull(folder)) throw LanguageError.autoUnloaderNullFolder();
		if (!folder.exists()) throw LanguageError.autoUnloaderFolderNotExists(folder);

		Set<Wini> winis = new HashSet<>();

		Wini wini = null;
		Reflections reflections = new Reflections(nvl(packageName, ""));
		Set<Class<?>> languagesAutoloader = reflections.getTypesAnnotatedWith(LanguageAutoloader.class);

		for (Class<?> languageAutoloader : languagesAutoloader) {
			String section = languageAutoloader.getName();

			if (separated || wini == null) {
				String filename = LanguageLoader.getLanguageFilename(languageAutoloader);
				File file = new File(folder, filename);
				wini = new Wini();
				wini.setFile(file);
				winis.add(wini);
			}

			Object[] enumConstants = languageAutoloader.getEnumConstants();
			autoUnloadWini(wini, section, enumConstants, named);
		}

		return winis;
	}

	private static void autoUnloadWini(Wini wini, String section, Object[] enumConstants, boolean named) {
		for (Object object : enumConstants)
			if (object instanceof Language) {
				Language language = (Language) object;
				String option = named ? language.toString() : Integer.toString(language.getCode());
				wini.add(section, option, language.getFormat());
			}
	}

}
