package org.diverproject.scarlet.logger.abstraction;

public class DefaultLogger extends AbstractLogger {
	public DefaultLogger(String name) {
		super(name, new DefaultLoggerSubject());
	}
}
