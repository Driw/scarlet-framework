package org.diverproject.scarlet.logger;

import java.io.Closeable;

public interface Logger extends Closeable {
	String getName();

	void feedLine();

	void log(String message);
	void log(String format, Object... args);
	void log(LoggerLevel level, String message);
	void log(LoggerLevel level, String format, Object... args);

	void debug(String message);
	void debug(String format, Object... args);
	void system(String message);
	void system(String format, Object... args);
	void info(String message);
	void info(String format, Object... args);
	void notice(String message);
	void notice(String format, Object... args);
	void packet(String message);
	void packet(String format, Object... args);
	void warn(String message);
	void warn(String format, Object... args);
	void error(String message);
	void error(String format, Object... args);
	void fatal(String message);
	void fatal(String format, Object... args);
	void exception(String message);
	void exception(String format, Object... args);
	void exception(Exception e);
	void exception(Exception e, String message);
	void exception(Exception e, String format, Object... args);
	void trace(Exception e);
}
