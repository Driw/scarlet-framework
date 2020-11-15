package org.diverproject.scarlet.context;

import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.abstraction.DefaultLogger;

public class DefaultLoggerContext extends DefaultLogger {

	public DefaultLoggerContext(String name) {
		super(name);
	}

	public DefaultLoggerContext(String name, LoggerSubject loggerOutputSubject) {
		super(name, loggerOutputSubject);
	}

}
