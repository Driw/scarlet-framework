package org.diverproject.scarlet.logger;

import java.lang.reflect.InvocationTargetException;

public class ScarletLoggers implements Loggers<Logger> {

	private static final String DEFAULT_LOGGER_NAME = "default";
	private static final ScarletLoggers INSTANCE = new ScarletLoggers();

	private final MapLogger<Logger> mapLogger;

	private ScarletLoggers() {
		this.mapLogger = new DefaultMapLogger<>();
	}

	@Override
	public boolean contains(String name) {
		return this.mapLogger.contains(name);
	}

	public <T extends Logger> T add(String name, Class<T> loggerClass) {
		try {
			T logger = loggerClass.getConstructor(String.class).newInstance(name);
			this.mapLogger.add(logger);
			return logger;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw LoggerError.newLoggerInstanceAtAdd(e, loggerClass);
		}
	}

	@Override
	public Logger get(Class<?> classz) {
		return this.mapLogger.get(classz.getName());
	}

	@Override
	public Logger get(String name) {
		return this.mapLogger.get(name);
	}

	@Override
	public Logger getDefault() {
		return this.mapLogger.get(DEFAULT_LOGGER_NAME);
	}

	public static ScarletLoggers getInstance() {
		return INSTANCE;
	}
}
