package org.diverproject.scarlet.logger.abstraction;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.LoggerLanguage;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.LoggerSubject;

public class DefaultLoggerLanguage extends AbstractLogger implements LoggerLanguage {

	public DefaultLoggerLanguage(String name) {
		this(name, new DefaultLoggerSubject());
	}

	public DefaultLoggerLanguage(String name, String pathname) {
		this(name, new DefaultFileLoggerSubject(pathname));
	}

	public DefaultLoggerLanguage(String name, LoggerSubject loggerOutputSubject) {
		super(name, loggerOutputSubject);
	}

	@Override
	public void log(LoggerLevel level, Language language, Object... args) {
		this.upperStackTrace(1);
		this.log(level, language.getFormat(), args);
	}

	@Override
	public void log(Language language) {
		this.upperStackTrace(1);
		this.log(NONE, language);
	}

	@Override
	public void log(Language language, Object... args) {
		this.upperStackTrace(1);
		this.log(NONE, language, args);
	}

	@Override
	public void debug(Language language) {
		this.upperStackTrace(1);
		this.debug(language.getFormat());
	}

	@Override
	public void debug(Language language, Object... args) {
		this.upperStackTrace(1);
		this.debug(language.getFormat(), args);
	}

	@Override
	public void system(Language language) {
		this.upperStackTrace(1);
		this.system(language.getFormat());
	}

	@Override
	public void system(Language language, Object... args) {
		this.upperStackTrace(1);
		this.system(language.getFormat(), args);
	}

	@Override
	public void info(Language language) {
		this.upperStackTrace(1);
		this.info(language.getFormat());
	}

	@Override
	public void info(Language language, Object... args) {
		this.upperStackTrace(1);
		this.info(language.getFormat(), args);
	}

	@Override
	public void notice(Language language) {
		this.upperStackTrace(1);
		this.notice(language.getFormat());
	}

	@Override
	public void notice(Language language, Object... args) {
		this.upperStackTrace(1);
		this.notice(language.getFormat(), args);
	}

	@Override
	public void packet(Language language) {
		this.upperStackTrace(1);
		this.packet(language.getFormat());
	}

	@Override
	public void packet(Language language, Object... args) {
		this.upperStackTrace(1);
		this.packet(language.getFormat(), args);
	}

	@Override
	public void warn(Language language) {
		this.upperStackTrace(1);
		this.warn(language.getFormat());
	}

	@Override
	public void warn(Language language, Object... args) {
		this.upperStackTrace(1);
		this.warn(language.getFormat(), args);
	}

	@Override
	public void error(Language language) {
		this.upperStackTrace(1);
		this.error(language.getFormat());
	}

	@Override
	public void error(Language language, Object... args) {
		this.upperStackTrace(1);
		this.error(language.getFormat(), args);
	}

	public void fatal(Language language) {
		this.upperStackTrace(1);
		this.fatal(language.getFormat());
	}

	@Override
	public void fatal(Language language, Object... args) {
		this.upperStackTrace(1);
		this.fatal(language.getFormat(), args);
	}

	@Override
	public void exception(Language language) {
		this.upperStackTrace(1);
		this.exception(language.getFormat());
	}

	@Override
	public void exception(Language language, Object... args) {
		this.upperStackTrace(1);
		this.exception(language.getFormat(), args);
	}

	@Override
	public void exception(Exception e, Language language) {
		this.upperStackTrace(1);
		this.exception(e, language.getFormat());
	}

	@Override
	public void exception(Exception e, Language language, Object... args) {
		this.upperStackTrace(1);
		this.exception(e, language.getFormat(), args);
	}
}
