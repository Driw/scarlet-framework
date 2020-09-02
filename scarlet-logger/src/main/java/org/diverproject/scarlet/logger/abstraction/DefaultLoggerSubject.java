package org.diverproject.scarlet.logger.abstraction;

import lombok.Data;
import org.diverproject.scarlet.logger.LoggerObserver;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class DefaultLoggerSubject implements LoggerSubject {

	private Set<LoggerObserver> observers;

	public DefaultLoggerSubject() {
		this.setObservers(new LinkedHashSet<>());

		if (!this.register(DefaultLoggerObserver.getInstance()))
			throw LoggerAbstractionError.cannotRegisterLogger(DefaultLoggerObserver.getInstance());
	}

	@Override
	public boolean register(LoggerObserver observer) {
		return this.getObservers().add(observer);
	}

	@Override
	public boolean unregister(LoggerObserver observer) {
		return this.getObservers().remove(observer);
	}

	@Override
	public void notify(MessageOutput message) {
		this.getObservers().forEach(output -> output.onMessage(this, message));
	}

	@Override
	public int getObserversCount() {
		return this.getObservers().size();
	}

	@Override
	public void feedLine() {
		this.getObservers().forEach(observer -> observer.onMessage("\n"));
	}

	@Override
	public void close() throws IOException {
		this.getObservers().clear();
	}
}
