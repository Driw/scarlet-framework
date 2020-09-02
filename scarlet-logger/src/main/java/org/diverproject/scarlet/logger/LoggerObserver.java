package org.diverproject.scarlet.logger;

public interface LoggerObserver {
	void onMessage(String message);
	void onMessage(LoggerSubject subject, MessageOutput message);
}
