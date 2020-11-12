package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum ArrayUtilsLanguage implements Language {

	SUB_ARRAY_LENGTH_INVALID		("invalid length (length: %d)"),
	SUB_ARRAY_NULL					("cannot sub null array"),
	SUB_ARRAY_OFFSET_INVALID		("invalid offset (offset: %d)"),

	;

	@Getter
	@Setter
	private String format;

	ArrayUtilsLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
