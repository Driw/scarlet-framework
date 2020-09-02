package org.diverproject.scarlet.logger.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LoggerAbstractionLanguage implements Language {

	CANNOT_REGISTER_LOGGER_OBSERVER	("cannot register logger observer (className: %s)"),
	OPEN_FILE_CHANNEL				("failure on open a file channel (pathname: %s)"),
	WRITE_FILE_CHANNEL				("failure on write to file channel (pathname: %s)"),
	FEED_LINE_FILE_CHANNEL			("failure on feed a line to file channel (pathname: %s)"),
	OFFSET_TRACE					("invalid offset trace (offsetTrace: %d)"),

	;

	@Getter
	@Setter
	private String format;

	LoggerAbstractionLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal();
	}
}
