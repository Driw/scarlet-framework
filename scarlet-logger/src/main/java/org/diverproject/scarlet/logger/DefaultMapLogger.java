package org.diverproject.scarlet.logger;

import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class DefaultMapLogger<L extends Logger> implements MapLogger<L> {

	public static final String DEFAULT_LOGGER_NAME = "default";

	private Map<String, L> loggers;

	public DefaultMapLogger() {
		this.loggers = new TreeMap<>();
	}

	@Override
	public L get(String name) {
		return this.getLoggers().get(name);
	}

	@Override
	public boolean add(L logger) {
		synchronized (this.getLoggers()) {
			if (!this.hasAvailableName(logger.getName()))
				return false;

			this.getLoggers().put(logger.getName(), logger);
			return true;
		}
	}

	@Override
	public boolean contains(L logger) {
		return this.getLoggers().containsValue(logger);
	}

	@Override
	public boolean contains(String name) {
		return this.getLoggers().containsKey(name);
	}

	@Override
	public boolean hasAvailableName(String name) {
		return !this.getLoggers().containsKey(name);
	}

	@Override
	public void clear() {
		this.getLoggers().values().forEach(this::closeLogger);
		this.getLoggers().clear();
	}

	private void closeLogger(L logger) {
		try {
			logger.close();
		} catch (IOException e) {
			throw LoggerError.closeLogger(e, logger);
		}
	}

	@Override
	public int size() {
		return this.getLoggers().size();
	}

}
