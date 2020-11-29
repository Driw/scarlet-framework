package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.language.Language;

import java.io.Closeable;

public interface Logger extends Closeable, org.slf4j.Logger {
	String getName();

	void feedLine();

	void log(String message);
	void log(String format, Object... args);
	void log(Language language);
	void log(Language language, Object... args);
	void log(LoggerLevel level, String message);
	void log(LoggerLevel level, String format, Object... args);
	void log(LoggerLevel level, Language language, Object... args);

	void trace(Language language);
	void trace(Language language, Object... args);
	void trace(Exception e);

	void debug(String message);
	void debug(String format, Object... args);
	void debug(Language language);
	void debug(Language language, Object... args);

	void system(String message);
	void system(String format, Object... args);
	void system(Language language);
	void system(Language language, Object... args);

	void info(String message);
	void info(String format, Object... args);
	void info(Language language);
	void info(Language language, Object... args);

	void notice(String message);
	void notice(String format, Object... args);
	void notice(Language language);
	void notice(Language language, Object... args);

	void packet(String message);
	void packet(String format, Object... args);
	void packet(Language language);
	void packet(Language language, Object... args);

	void warn(String message);
	void warn(String format, Object... args);
	void warn(Language language);
	void warn(Language language, Object... args);

	void error(String message);
	void error(String format, Object... args);
	void error(Language language);
	void error(Language language, Object... args);

	void fatal(String message);
	void fatal(String format, Object... args);
	void fatal(Language language, Object... args);

	void exception(String message);
	void exception(String format, Object... args);
	void exception(Exception e);
	void exception(Exception e, String message);
	void exception(Exception e, String format, Object... args);
	void exception(Language language);
	void exception(Language language, Object... args);
	void exception(Exception e, Language language);
	void exception(Exception e, Language language, Object... args);
}
