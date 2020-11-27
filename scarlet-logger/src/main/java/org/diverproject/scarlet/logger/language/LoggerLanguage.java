package org.diverproject.scarlet.logger.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LoggerLanguage implements Language {

	DEFAULT_CLASS_LOGGER_NOT_SET		("none default class logger set to new instances"),
	SCARLET_LOGGER_CLOSE				("failure on clear ScarletLogger (logger: %s)"),

	;

	@Getter
	@Setter
	private String format;

	LoggerLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
