package org.diverproject.scarlet.logger.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LoggerAbstractionLanguage implements Language {

	CANNOT_REGISTER_LOGGER_OBSERVER	("cannot register logger observer (className: %s)"),

	;

	@Getter
	@Setter
	private String format;

	LoggerAbstractionLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
