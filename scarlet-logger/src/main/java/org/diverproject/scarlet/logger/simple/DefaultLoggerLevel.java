package org.diverproject.scarlet.logger.simple;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Level;
import org.diverproject.scarlet.logger.LoggerLevel;

public enum DefaultLoggerLevel implements LoggerLevel {

	NONE		("", Level.ALL),
	SYSTEM		("SYSTEM", Level.ALL),
	TRACE		("TRACE ", Level.TRACE),
	DEBUG		("DEBUG ", Level.DEBUG),
	INFO		("INFO  ", Level.INFO),
	NOTICE		("NOTICE", Level.INFO),
	PACKET		("PACKET", Level.INFO),
	WARN		("WARN  ", Level.WARN),
	ERROR		("ERROR ", Level.ERROR),
	FATAL		("FATAL ", Level.FATAL),
	EXCEPTION	("EXCEPT", Level.FATAL),
	;

	@Getter
	@Setter
	private String label;

	@Getter
	private final Level level;

	DefaultLoggerLevel(String label, Level level) {
		this.setLabel(label);
		this.level = level;
	}

	@Override
	public String getLabel() {
		return this.toString();
	}

}
