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
	private boolean traceEnabled;
	private boolean debugEnabled;
	private boolean infoEnabled;
	private boolean warnEnabled;
	private boolean errorEnabled;
	private boolean closed;

	public ScarletLogger(String name, org.apache.log4j.Logger logger) {
		this.setName(name);
		this.setLogger(logger);
	}

	protected boolean canLog(Level log4jLevel) {
		if (Level.OFF.equals(log4jLevel)) {
			return false;
		}

		if (Level.FATAL.equals(log4jLevel) || Level.ERROR.equals(log4jLevel)) {
			return this.isErrorEnabled();
		}

		if (Level.WARN.equals(log4jLevel)) {
			return this.isWarnEnabled();
		}

		if (Level.INFO.equals(log4jLevel)) {
			return this.isInfoEnabled();
		}

		if (Level.DEBUG.equals(log4jLevel)) {
			return this.isDebugEnabled();
		}

		if (Level.TRACE.equals(log4jLevel)) {
			return this.isTraceEnabled();
		}

		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return this.traceEnabled || this.getLogger().isTraceEnabled();
	}

	@Override
	public boolean isDebugEnabled() {
		return this.debugEnabled || this.getLogger().isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return this.infoEnabled || this.getLogger().isInfoEnabled();
	}

	public abstract void handle(LoggerMessage loggerMessage);

	protected SimpleLoggerMessage message(String message, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(DefaultLoggerLevel.NONE)
			.setMessage(message)
			.setArguments(args);
	}

	protected SimpleLoggerMessage message(Language language, Object... args) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(DefaultLoggerLevel.NONE)
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

	public SimpleLoggerMessage message(LoggerLevel loggerLevel, Marker marker, Throwable throwable) {
		return new SimpleLoggerMessage()
			.setLoggerLevel(loggerLevel)
			.setThrowable(throwable)
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
		this.handle(this.message("\n"));
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
	public void log(LoggerLevel level, String message) {
		this.handle(this.message(level, message));
	}

	@Override
	public void log(LoggerLevel level, String format, Object... args) {
		this.handle(this.message(level, format, args));
	}

	@Override
	public void log(LoggerLevel level, Language language, Object... args) {
		this.handle(this.message(level, language.getFormat(), args));
	}

	@Override
	public void trace(String msg) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, msg));
	}

	@Override
	public void trace(String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, format, arg));
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, format, arg1, arg2));
	}

	@Override
	public void trace(String format, Object... arguments) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, format, arguments));
	}

	@Override
	public void trace(String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, t, msg));
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return this.isTraceEnabled();
	}

	@Override
	public void trace(Marker marker, String msg) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, msg));
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, marker, format, arg));
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, marker, format, arg1, arg2));
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, marker, format, argArray));
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, marker, t));
	}

	@Override
	public void debug(String message) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, message));
	}

	@Override
	public void debug(String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, format, arg));
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, format, arg1, arg2));
	}

	@Override
	public void debug(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, format, args));
	}

	@Override
	public void debug(String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, msg, t));
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return this.isDebugEnabled();
	}

	@Override
	public void debug(Marker marker, String msg) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, marker, msg));
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, marker, format, arg));
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, marker, format, arg1, arg2));
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, marker, format, arguments));
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, marker, msg, t));
	}

	@Override
	public void debug(Language language) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, language));
	}

	@Override
	public void debug(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.DEBUG, language, args));
	}

	@Override
	public void system(String message) {
		this.handle(this.message(DefaultLoggerLevel.SYSTEM, message));
	}

	@Override
	public void system(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.SYSTEM, format, args));
	}

	@Override
	public void system(Language language) {
		this.handle(this.message(DefaultLoggerLevel.SYSTEM, language));
	}

	@Override
	public void system(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.SYSTEM, language, args));
	}

	@Override
	public void info(String message) {
		this.handle(this.message(DefaultLoggerLevel.INFO, message));
	}

	@Override
	public void info(String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.INFO, format, arg));
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.INFO, format, arg1, arg2));
	}

	@Override
	public void info(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.INFO, format, args));
	}

	@Override
	public void info(String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.INFO, msg, t));
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return this.isInfoEnabled();
	}

	@Override
	public void info(Marker marker, String msg) {
		this.handle(this.message(DefaultLoggerLevel.INFO, marker, msg));
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.INFO, marker, format, arg));
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.INFO, marker, format, arg1, arg2));
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		this.handle(this.message(DefaultLoggerLevel.INFO, marker, format, arguments));
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.INFO, marker, msg, t));
	}

	@Override
	public void info(Language language) {
		this.handle(this.message(DefaultLoggerLevel.INFO, language));
	}

	@Override
	public void info(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.INFO, language, args));
	}

	@Override
	public void notice(String message) {
		this.handle(this.message(DefaultLoggerLevel.NOTICE, message));
	}

	@Override
	public void notice(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.NOTICE, format, args));
	}

	@Override
	public void notice(Language language) {
		this.handle(this.message(DefaultLoggerLevel.NOTICE, language));
	}

	@Override
	public void notice(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.NOTICE, language, args));
	}

	@Override
	public void packet(String message) {
		this.handle(this.message(DefaultLoggerLevel.PACKET, message));
	}

	@Override
	public void packet(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.PACKET, format, args));
	}

	@Override
	public void packet(Language language) {
		this.handle(this.message(DefaultLoggerLevel.PACKET, language));
	}

	@Override
	public void packet(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.PACKET, language, args));
	}

	@Override
	public void warn(String message) {
		this.handle(this.message(DefaultLoggerLevel.WARN, message));
	}

	@Override
	public void warn(String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.WARN, format, arg));
	}

	@Override
	public void warn(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.WARN, format, args));
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.WARN, format, arg1, arg2));
	}

	@Override
	public void warn(String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.WARN, msg, t));
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return this.isWarnEnabled();
	}

	@Override
	public void warn(Marker marker, String msg) {
		this.handle(this.message(DefaultLoggerLevel.WARN, marker, msg));
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.WARN, marker, format, arg));
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.WARN, marker, format, arg1, arg2));
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		this.handle(this.message(DefaultLoggerLevel.WARN, marker, format, arguments));
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.WARN, marker, msg, t));
	}

	@Override
	public void warn(Language language) {
		this.handle(this.message(DefaultLoggerLevel.WARN, language));
	}

	@Override
	public void warn(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.WARN, language, args));
	}

	@Override
	public void error(String message) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, message));
	}

	@Override
	public void error(String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, format, arg));
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, format, arg1, arg2));
	}

	@Override
	public void error(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, format, args));
	}

	@Override
	public void error(String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, msg, t));
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return this.isErrorEnabled();
	}

	@Override
	public void error(Marker marker, String msg) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, marker, msg));
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, marker, format, arg));
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, marker, format, arg1, arg2));
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, marker, format, arguments));
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, marker, msg, t));
	}

	@Override
	public void error(Language language) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, language));
	}

	@Override
	public void error(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.ERROR, language, args));
	}

	@Override
	public void fatal(String message) {
		this.handle(this.message(DefaultLoggerLevel.FATAL, message));
	}

	@Override
	public void fatal(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.FATAL, format, args));
	}

	@Override
	public void fatal(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.FATAL, language, args));
	}

	@Override
	public void exception(String message) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, message));
	}

	@Override
	public void exception(String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, format, args));
	}

	@Override
	public void exception(Exception e) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, e));
	}

	@Override
	public void exception(Exception e, String message) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, e, message));
	}

	@Override
	public void exception(Exception e, String format, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, e, format, args));
	}

	@Override
	public void exception(Language language) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, language));
	}

	@Override
	public void exception(Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, language, args));
	}

	@Override
	public void exception(Exception e, Language language) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, e, language));
	}

	@Override
	public void exception(Exception e, Language language, Object... args) {
		this.handle(this.message(DefaultLoggerLevel.EXCEPTION, e, language, args));
	}

	@Override
	public void trace(Exception e) {
		this.handle(this.message(DefaultLoggerLevel.TRACE, e));
	}

	@Override
	public void close() {
		this.setClosed(true);
	}

}
