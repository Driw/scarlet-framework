package org.diverproject.scarlet.language;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class LanguageLoader {

	private LanguageLoader() { }

	public static List<LanguageMapper> loadLanguageMapper(File file) throws LanguageException {
		try {
			Wini wini = new Wini(file);
			return loadLanguageMapper(wini);
		} catch (IOException e) {
			throw LanguageError.loadLanguageMapperIOException(e, file);
		}
	}

	private static List<LanguageMapper> loadLanguageMapper(Wini wini) {
		List<LanguageMapper> languageMappers = new LinkedList<>();

		for (Entry<String, Section> languageSection : wini.entrySet()) {
			String classpath = languageSection.getKey();
			Section section = languageSection.getValue();

			LanguageMapper languageMapper = new LanguageMapper();
			languageMapper.setClassPath(classpath);
			languageMappers.add(languageMapper);

			for (Entry<String, String> languageEntry : section.entrySet())
				languageMapper.getMessages().put(languageEntry.getKey(), languageEntry.getValue());
		}

		return languageMappers;
	}

	public static int loadEnumeration(File file, Class<? extends Language> enumeration) throws LanguageException {
		int affected = 0;
		Object[] enumConstants = enumeration.getEnumConstants();
		List<LanguageMapper> languageMappers = loadLanguageMapper(file);

		for (LanguageMapper languageMapper : languageMappers) {
			if (!languageMapper.getClassPath().equals(enumeration.getName()))
				continue;

			affected += loadEnumLanguageMapper(enumConstants, languageMapper);
		}

		return affected;
	}

	public static int loadEnumerations(File file) throws LanguageException {
		int affected = 0;
		List<LanguageMapper> languageMappers = loadLanguageMapper(file);

		for (LanguageMapper languageMapper : languageMappers) {
			try {

				Class<?> classz = Class.forName(languageMapper.getClassPath());
				Object[] enumConstants = classz.getEnumConstants();
				affected += loadEnumLanguageMapper(enumConstants, languageMapper);
			} catch (ClassNotFoundException e) {
				throw LanguageError.languageNotFound(e, file);
			}
		}

		return affected;
	}

	public static int autoLoad(File folder) throws LanguageException {
		int affected = 0;

		Reflections reflections = new Reflections("");
		Set<Class<?>> languagesAutoloader = reflections.getTypesAnnotatedWith(LanguageAutoloader.class);

		for (Class<?> languageAutoloader : languagesAutoloader) {
			Object[] enumConstants = languageAutoloader.getEnumConstants();
			String filename = getLanguageFilename(languageAutoloader);
			File file = new File(folder, filename);

			if (!file.exists())
				continue;

			try {
				Wini wini = new Wini(file);
				List<LanguageMapper> languageMappers = loadLanguageMapper(wini);

				if (languageMappers.isEmpty())
					throw LanguageError.emptyLanguage(file);

				for (LanguageMapper languageMapper : languageMappers)
					affected += loadEnumLanguageMapper(enumConstants, languageMapper);
			} catch (IOException e) {
				throw LanguageError.autoLoaderIOException(e, folder);
			}
		}

		return affected;
	}

	private static int loadEnumLanguageMapper(Object[] enumConstants, LanguageMapper languageMapper) {
		int affected = 0;

		for (Object enumConstant : enumConstants)
			if (enumConstant instanceof Language) {
				Language language = (Language) enumConstant;
				String code = Integer.toString(language.getCode());
				String format = languageMapper.getMessages().get(code);

				if (format != null) {
					language.setFormat(format);
					affected++;
				} else if ((format = languageMapper.getMessages().get(language.toString())) != null) {
					affected++;
					language.setFormat(format);
				}
			}

		return affected;
	}

	public static String getLanguageFilename(Class<?> languageAutoloader) {
		return String.format("%s.ini", languageAutoloader.getName());
	}
}
