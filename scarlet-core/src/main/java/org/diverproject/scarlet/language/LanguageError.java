package org.diverproject.scarlet.language;

import static org.diverproject.scarlet.util.language.LanguageLanguage.EMPTY_LANGUAGE_FILE;

import org.diverproject.scarlet.util.language.LanguageLanguage;

import java.io.File;
import java.io.IOException;

public class LanguageError {

	private LanguageError() { }

	public static LanguageException loadLanguageMapperIOException(IOException e, File file) {
		return new LanguageException(e, LanguageLanguage.LOAD_LANGUAGE_MAPPER_INPUT_EXCEPTION, file.getAbsolutePath());
	}

	public static LanguageException autoLoaderIOException(IOException e, File file) {
		return new LanguageException(e, LanguageLanguage.AUTO_LOADER_INPUT_EXCEPTION, file.getAbsolutePath());
	}

	public static LanguageException languageNotFound(ClassNotFoundException e, File file) {
		return new LanguageException(e, LanguageLanguage.LANGUAGE_NOT_FOUND, file.getAbsolutePath());
	}

	public static LanguageException emptyLanguage(File file) {
		return new LanguageException(EMPTY_LANGUAGE_FILE, file.getAbsolutePath());
	}

	public static LanguageRuntimeException autoUnloaderNullFolder() {
		return new LanguageRuntimeException(LanguageLanguage.AUTO_UNLOAD_NULL_FOLDER);
	}

	public static LanguageRuntimeException autoUnloaderFolderNotExists(File folder) {
		return new LanguageRuntimeException(LanguageLanguage.AUTO_UNLOAD_FOLDER_NOT_EXISTS, folder.getAbsolutePath());
	}

}
