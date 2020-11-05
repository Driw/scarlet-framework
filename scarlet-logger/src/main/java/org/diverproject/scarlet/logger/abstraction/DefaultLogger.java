package org.diverproject.scarlet.logger.abstraction;

import org.diverproject.scarlet.logger.LoggerSubject;

public class DefaultLogger extends AbstractLogger {

	public DefaultLogger(String name) {
		this(name, new DefaultLoggerSubject());
	}

	public DefaultLogger(String name, LoggerSubject loggerSubject) {
		super(name, loggerSubject);
	}

}
