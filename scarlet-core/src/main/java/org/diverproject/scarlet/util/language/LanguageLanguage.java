package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LanguageLanguage implements Language {

	LOAD_LANGUAGE_MAPPER_INPUT_EXCEPTION	("failure on open the ini file (file: %s)"),
	AUTO_LOADER_INPUT_EXCEPTION				("failure on open the ini file (file: %s)"),
	LANGUAGE_NOT_FOUND						("language enumeration not found (enum: %s)"),
	EMPTY_LANGUAGE_FILE						("empty language file is not acceptable (file: %s)"),
	AUTO_UNLOAD_NULL_FOLDER					("auto unloader can't accept a null folder"),
	AUTO_UNLOAD_FOLDER_NOT_EXISTS			("auto unloader can't accept a folder that not exist (folder: %s)")

	;

	@Getter
	@Setter
	private String format;

	LanguageLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
