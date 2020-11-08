package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerLanguage;

public class DefaultLoggerContext extends DefaultLoggerLanguage implements Logger {

	public DefaultLoggerContext(String name) {
		super(name);
	}

	public DefaultLoggerContext(String name, String pathname) {
		super(name, pathname);
	}

	public DefaultLoggerContext(String name, LoggerSubject loggerOutputSubject) {
		super(name, loggerOutputSubject);
	}

}
