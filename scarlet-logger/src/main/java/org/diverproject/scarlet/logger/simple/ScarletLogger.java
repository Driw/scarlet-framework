package org.diverproject.scarlet.logger.simple;

import lombok.Data;
import org.apache.log4j.Level;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.message.LoggerMessage;
import org.diverproject.scarlet.logger.message.SimpleLoggerMessage;
import org.slf4j.Marker;

@Data
public abstract class ScarletLogger implements Logger {

	protected static final String CALLER_FUNCTION = ScarletLogger.class.getName();

	private org.apache.log4j.Logger logger;
	private String name;
	private boolean closed;

	public ScarletLogger(String name, org.apache.log4j.Logger logger) {
		this.setName(name);
		this.setLogger(logger);
	}

	protected boolean canLog(Level log4jLevel) {
		if (this.getLogger().getLoggerRepository().isDisabled(log4jLevel.toInt()))
			return false;

		return log4jLevel.isGreaterOrEqual(this.getLogger().getEffectiveLevel());
	}

	@Override
	public boolean isTraceEnabled() {
		return this.getLogger().isTraceEnabled();
	}

	@Override
	public boolean isDebugEnabled() {
		return this.getLogger().isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return this.getLogger().isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		if (this.getLogger().getLoggerRepository().isDisabled(ScarletLoggerLevel.WARN_INT)) {
			return false;
		}

		return ScarletLoggerLevel.WARN.level().isGreaterOrEqual(this.getLogger().getEffectiveLevel());
	}

	@Override
	public boolean isErrorEnabled() {
		return this.canLog(ScarletLoggerLevel.ERROR.level());
	}

	public abstract void handle(LoggerMessage loggerMessage);

	protected SimpleLoggerMessage message(String message, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(ScarletLoggerLevel.NONE)
			.setMessage(message)
			.setArguments(args);
	}

	protected SimpleLoggerMessage message(Language language, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(ScarletLoggerLevel.NONE)
			.setMessage(language.getFormat())
			.setArguments(args);
	}

	protected SimpleLoggerMessage message(LoggerLevel loggerLevel, Language language, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(loggerLevel)
			.setMessage(language.getFormat())
			.setArguments(args);
	}

	public SimpleLoggerMessage message(LoggerLevel loggerLevel, String message, Object... args) {
		return this.message(message, args)
			.setLoggerLevel(loggerLevel);
	}

	public SimpleLoggerMessage message(LoggerLevel loggerLevel, Marker marker, String message, Object... args) {
		return this.message(message, args)
			.setLoggerLevel(loggerLevel)
			.setMarker(marker);
	}

	public SimpleLoggerMessage message(LoggerLevel loggerLevel, Throwable throwable, String message, Object... args) {
		return this.message(message, args)
			.setLoggerLevel(loggerLevel)
			.setThrowable(throwable);
	}

	protected SimpleLoggerMessage message(LoggerLevel loggerLevel, Throwable throwable) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(loggerLevel)
			.setThrowable(throwable);
	}

	protected SimpleLoggerMessage message(LoggerLevel loggerLevel, Throwable throwable, Language language, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(loggerLevel)
			.setThrowable(throwable)
			.setMessage(language.getFormat())
			.setArguments(args);
	}

	@Override
	public void feedLine() {
		this.handle(this.message(System.lineSeparator()));
	}

	@Override
	public void log(String message) {
		this.handle(this.message(message));
	}

	@Override
	public void log(String format, Object... args) {
		this.handle(this.message(format, args));
	}

	@Override
	public void log(Language language) {
		this.handle(this.message(language));
	}

	@Override
	public void log(Language language, Object... args) {
		this.handle(this.message(language, args));
	}

	@Override
	public void log(org.diverproject.scarlet.logger.LoggerLevel level, String message) {
		this.handle(this.message(level, message));
	}

	@Override
	public void log(org.diverproject.scarlet.logger.LoggerLevel level, String format, Object... args) {
		this.handle(this.message(level, format, args));
	}

	@Override
	public void log(org.diverproject.scarlet.logger.LoggerLevel level, Language language, Object... args) {
		this.handle(this.message(level, language.getFormat(), args));
	}

	@Override
	public void trace(String msg) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, msg));
	}

	@Override
	public void trace(String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, format, arg));
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, format, arg1, arg2));
	}

	@Override
	public void trace(String format, Object... arguments) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, format, arguments));
	}

	@Override
	public void trace(String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, t, msg));
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return this.isTraceEnabled();
	}

	@Override
	public void trace(Marker marker, String msg) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, msg));
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, marker, format, arg));
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, marker, format, arg1, arg2));
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, marker, format, argArray));
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, marker, msg, t));
	}

	@Override
	public void debug(String message) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, message));
	}

	@Override
	public void debug(String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, format, arg));
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, format, arg1, arg2));
	}

	@Override
	public void debug(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, format, args));
	}

	@Override
	public void debug(String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, msg, t));
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return this.isDebugEnabled();
	}

	@Override
	public void debug(Marker marker, String msg) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, marker, msg));
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, marker, format, arg));
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, marker, format, arg1, arg2));
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, marker, format, arguments));
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, marker, msg, t));
	}

	@Override
	public void debug(Language language) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, language));
	}

	@Override
	public void debug(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.DEBUG, language, args));
	}

	@Override
	public void system(String message) {
		this.handle(this.message(ScarletLoggerLevel.SYSTEM, message));
	}

	@Override
	public void system(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.SYSTEM, format, args));
	}

	@Override
	public void system(Language language) {
		this.handle(this.message(ScarletLoggerLevel.SYSTEM, language));
	}

	@Override
	public void system(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.SYSTEM, language, args));
	}

	@Override
	public void info(String message) {
		this.handle(this.message(ScarletLoggerLevel.INFO, message));
	}

	@Override
	public void info(String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.INFO, format, arg));
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.INFO, format, arg1, arg2));
	}

	@Override
	public void info(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.INFO, format, args));
	}

	@Override
	public void info(String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.INFO, msg, t));
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return this.isInfoEnabled();
	}

	@Override
	public void info(Marker marker, String msg) {
		this.handle(this.message(ScarletLoggerLevel.INFO, marker, msg));
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.INFO, marker, format, arg));
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.INFO, marker, format, arg1, arg2));
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		this.handle(this.message(ScarletLoggerLevel.INFO, marker, format, arguments));
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.INFO, marker, msg, t));
	}

	@Override
	public void info(Language language) {
		this.handle(this.message(ScarletLoggerLevel.INFO, language));
	}

	@Override
	public void info(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.INFO, language, args));
	}

	@Override
	public void notice(String message) {
		this.handle(this.message(ScarletLoggerLevel.NOTICE, message));
	}

	@Override
	public void notice(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.NOTICE, format, args));
	}

	@Override
	public void notice(Language language) {
		this.handle(this.message(ScarletLoggerLevel.NOTICE, language));
	}

	@Override
	public void notice(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.NOTICE, language, args));
	}

	@Override
	public void packet(String message) {
		this.handle(this.message(ScarletLoggerLevel.PACKET, message));
	}

	@Override
	public void packet(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.PACKET, format, args));
	}

	@Override
	public void packet(Language language) {
		this.handle(this.message(ScarletLoggerLevel.PACKET, language));
	}

	@Override
	public void packet(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.PACKET, language, args));
	}

	@Override
	public void warn(String message) {
		this.handle(this.message(ScarletLoggerLevel.WARN, message));
	}

	@Override
	public void warn(String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.WARN, format, arg));
	}

	@Override
	public void warn(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.WARN, format, args));
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.WARN, format, arg1, arg2));
	}

	@Override
	public void warn(String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.WARN, msg, t));
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return this.isWarnEnabled();
	}

	@Override
	public void warn(Marker marker, String msg) {
		this.handle(this.message(ScarletLoggerLevel.WARN, marker, msg));
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.WARN, marker, format, arg));
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.WARN, marker, format, arg1, arg2));
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		this.handle(this.message(ScarletLoggerLevel.WARN, marker, format, arguments));
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.WARN, marker, msg, t));
	}

	@Override
	public void warn(Language language) {
		this.handle(this.message(ScarletLoggerLevel.WARN, language));
	}

	@Override
	public void warn(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.WARN, language, args));
	}

	@Override
	public void error(String message) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, message));
	}

	@Override
	public void error(String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, format, arg));
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, format, arg1, arg2));
	}

	@Override
	public void error(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, format, args));
	}

	@Override
	public void error(String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, msg, t));
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return this.isErrorEnabled();
	}

	@Override
	public void error(Marker marker, String msg) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, marker, msg));
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, marker, format, arg));
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, marker, format, arg1, arg2));
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, marker, format, arguments));
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, marker, msg, t));
	}

	@Override
	public void error(Language language) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, language));
	}

	@Override
	public void error(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.ERROR, language, args));
	}

	@Override
	public void fatal(String message) {
		this.handle(this.message(ScarletLoggerLevel.FATAL, message));
	}

	@Override
	public void fatal(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.FATAL, format, args));
	}

	@Override
	public void fatal(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.FATAL, language, args));
	}

	@Override
	public void exception(String message) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, message));
	}

	@Override
	public void exception(String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, format, args));
	}

	@Override
	public void exception(Exception e) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, e));
	}

	@Override
	public void exception(Exception e, String message) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, e, message));
	}

	@Override
	public void exception(Exception e, String format, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, e, format, args));
	}

	@Override
	public void exception(Language language) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, language));
	}

	@Override
	public void exception(Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, language, args));
	}

	@Override
	public void exception(Exception e, Language language) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, e, language));
	}

	@Override
	public void exception(Exception e, Language language, Object... args) {
		this.handle(this.message(ScarletLoggerLevel.EXCEPTION, e, language, args));
	}

	@Override
	public void trace(Exception e) {
		this.handle(this.message(ScarletLoggerLevel.TRACE, e));
	}

	@Override
	public void close() {
		this.setClosed(true);
	}

}
