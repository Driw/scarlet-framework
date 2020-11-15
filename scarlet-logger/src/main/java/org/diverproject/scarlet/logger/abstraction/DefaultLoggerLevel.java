package org.diverproject.scarlet.logger.abstraction;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.logger.LoggerLevel;

public enum DefaultLoggerLevel implements LoggerLevel {

	NONE		(""),
	DEBUG		("DEBUG "),
	SYSTEM		("SYSTEM"),
	INFO		("INFO  "),
	NOTICE		("NOTICE"),
	PACKET		("PACKET"),
	WARN		("WARN  "),
	ERROR		("ERROR "),
	FATAL		("FATAL "),
	EXCEPTION	("EXCEPT"),
	;

	@Getter
	@Setter
	private String format;

	DefaultLoggerLevel(String format) {
		this.setFormat(format);
	}

	@Override
	public String getLabel() {
		return this.toString();
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
