package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.language.Language;

public interface LoggerLanguage extends Logger
{
	void log(LoggerLevel level, Language language, Object... args);
	void log(Language language);
	void log(Language language, Object... args);
	void debug(Language language);
	void debug(Language language, Object... args);
	void system(Language language);
	void system(Language language, Object... args);
	void info(Language language);
	void info(Language language, Object... args);
	void notice(Language language);
	void notice(Language language, Object... args);
	void packet(Language language);
	void packet(Language language, Object... args);
	void warn(Language language);
	void warn(Language language, Object... args);
	void error(Language language);
	void error(Language language, Object... args);
	void fatal(Language language, Object... args);
	void exception(Language language);
	void exception(Language language, Object... args);
	void exception(Exception e, Language language);
	void exception(Exception e, Language language, Object... args);
}
