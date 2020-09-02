package org.diverproject.scarlet.logger.abstraction;

import java.io.File;

public class DefaultLoggerFile extends AbstractLogger {

	public DefaultLoggerFile(String name, String pathname) {
		this(name, new File(pathname));
	}

	public DefaultLoggerFile(String name, File file) {
		super(name, new DefaultFileLoggerSubject(file));
	}
}
