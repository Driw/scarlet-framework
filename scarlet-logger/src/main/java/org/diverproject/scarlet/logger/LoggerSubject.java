package org.diverproject.scarlet.logger;

import java.io.Closeable;

public interface LoggerSubject extends Closeable {
	boolean register(LoggerObserver observer);
	boolean unregister(LoggerObserver observer);
	void notify(MessageOutput message);
	int getObserversCount();
	void feedLine();
}
