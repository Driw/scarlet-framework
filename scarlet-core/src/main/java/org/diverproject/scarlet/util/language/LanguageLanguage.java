package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LanguageLanguage implements Language
{
	INPUT_EXCEPTION		("failure on open the ini file (file: %s)"),
	LANGUAGE_NOT_FOUND	("language enumeration not found (enum: %s)"),
	EMPTY_LANGUAGE_FILE	("empty language file is not acceptable (file: %s)"),

	;

	@Getter
	@Setter
	private String format;

	LanguageLanguage(String format)
	{
		this.setFormat(format);
	}

	@Override
	public int getCode()
	{
		return this.ordinal() + 1;
	}
}
