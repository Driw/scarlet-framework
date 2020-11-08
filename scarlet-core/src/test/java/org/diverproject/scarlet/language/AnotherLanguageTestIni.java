package org.diverproject.scarlet.language;

import lombok.Getter;
import lombok.Setter;

@LanguageAutoloader
public enum AnotherLanguageTestIni implements Language {
	FIRST_MESSAGE		("First"),
	SECOND_MESSAGE		("Second"),

	;

	@Getter
	@Setter
	private String format;

	AnotherLanguageTestIni(String format)
	{
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
