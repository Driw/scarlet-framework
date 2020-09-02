package org.diverproject.scarlet.logger.abstraction;

import org.diverproject.scarlet.logger.LoggerObserver;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;

public final class DefaultLoggerObserver implements LoggerObserver {

	private static final DefaultLoggerObserver INSTANCE = new DefaultLoggerObserver();

	private DefaultLoggerObserver() {
	}

	@Override
	public void onMessage(String message) {
		System.out.print(message);
	}

	@Override
	public void onMessage(LoggerSubject subject, MessageOutput message) {
		this.onMessage(message.getOutput());
	}

	public static DefaultLoggerObserver getInstance() {
		return INSTANCE;
	}
}
