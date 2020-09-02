package org.diverproject.scarlet.language;

import lombok.Getter;
import lombok.Setter;

@LanguageAutoloader
public enum LanguageTestIni implements Language {

	FIRST_MESSAGE("First"),
	SECOND_MESSAGE("Second"),
	THIRD_MESSAGE("Third"),
	FOURTH_MESSAGE("Fourth"),

	;

	@Getter
	@Setter
	private String format;

	LanguageTestIni(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
