package org.diverproject.scarlet.logger;

import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Data
public class DefaultMapLogger<L extends Logger> implements MapLogger<L> {

	public static final String DEFAULT_LOGGER_NAME = "default";

	private Map<String, L> loggers;
	private boolean instanceNewLoggers;
	private Class<? extends L> defaultClassLogger;

	public DefaultMapLogger() {
		this.loggers = new TreeMap<>();
		this.setInstanceNewLoggers(false);
	}

	@Override
	public Set<String> names() {
		return this.getLoggers().keySet();
	}

	@Override
	public L get(String name) throws LoggerRuntimeException {
		L logger = this.getLoggers().get(name);

		if (logger == null)
			if (this.isInstanceNewLoggers())
				try {

					Class<? extends L> classz = this.getDefaultClassLogger();

					if (classz == null)
						throw LoggerExceptionError.defaultClassLoggerNotSet();

					Constructor<? extends L> constructor = classz.getDeclaredConstructor(String.class);

					logger = constructor.newInstance(name);

				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
					throw LoggerExceptionError.newLoggerInstance(e, name, this.getDefaultClassLogger());
				}

		if (logger == null)
			throw LoggerExceptionError.loggerNotFound(name);

		return logger;
	}

	@Override
	public boolean add(L logger) {
		synchronized (this.getLoggers()) {
			if (!this.hasAvailableName(logger.getName()))
				return false;

			return this.getLoggers().put(logger.getName(), logger) != null;
		}
	}

	@Override
	public boolean remove(Logger logger) {
		return this.getLoggers().remove(logger.getName()) != null;
	}

	@Override
	public boolean remove(String name) {
		return this.getLoggers().remove(name) != null;
	}

	@Override
	public boolean contains(Logger logger) {
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
		this.getLoggers().values().forEach(logger -> {
			try {
				logger.close();
			} catch (IOException e) {
				throw LoggerExceptionError.closeLogger(e, logger);
			}
		});
		this.getLoggers().clear();
	}

	@Override
	public int size() {
		return this.getLoggers().size();
	}
}
