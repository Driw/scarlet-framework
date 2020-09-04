package org.diverproject.scarlet.logger.abstraction;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.DEBUG;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.ERROR;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.EXCEPTION;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.FATAL;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.INFO;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NOTICE;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.PACKET;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.SYSTEM;
import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.WARN;
import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import lombok.Data;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.logger.message.AdvancedMessage;
import org.diverproject.scarlet.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public abstract class AbstractLogger implements Logger {

	public static final int DEFAULT_OFFSET_TRACE = 2;
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat DEFAULT_SIMPLE_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	private Date date;
	private String name;
	private Integer offsetTrace;
	private SimpleDateFormat simpleDateFormat;
	private LoggerSubject loggerOutputSubject;

	public AbstractLogger(String name, LoggerSubject loggerOutputSubject) {
		this.setName(name);
		this.setSimpleDateFormat(DEFAULT_SIMPLE_DATE_FORMAT);
		this.setDate(new Date());
		this.setLoggerOutputSubject(loggerOutputSubject);
	}

	protected void clearOffsetTrace() {
		this.setOffsetTrace(null);
	}

	private StackTraceElement buildOrigin() {
		if (this.getOffsetTrace() == null)
			this.setOffsetTrace(DEFAULT_OFFSET_TRACE);

		StackTraceElement stackTraceElement = AbstractLogger.getOrigin(++this.offsetTrace);
		this.clearOffsetTrace();

		return stackTraceElement;
	}

	protected StackTraceElement buildOrigin(int offset) {
		this.setOffsetTrace(DEFAULT_OFFSET_TRACE + offset);

		return this.buildOrigin();
	}

	public String getCurrentDateFormatted() {
		this.getDate().setTime(System.currentTimeMillis());

		return this.getSimpleDateFormat().format(this.date);
	}

	public void log(MessageOutput message) {
		this.getLoggerOutputSubject().notify(message);
	}

	protected void internalLogger(LoggerLevel level, String message, StackTraceElement origin) {
		if (level != null && level != NONE)
			this.feedLine();

		if (origin == null)
			origin = this.buildOrigin(1);

		AdvancedMessage loggerMessage = new AdvancedMessage();
		loggerMessage.setDateFormatted(this.getCurrentDateFormatted());
		loggerMessage.setLevel(level);
		loggerMessage.setMessage(message);
		loggerMessage.setOrigin(origin);

		this.log(loggerMessage);
	}

	@Override
	public void feedLine() {
		if (this.getLoggerOutputSubject() != null)
			this.getLoggerOutputSubject().feedLine();
	}

	@Override
	public void log(String message) {
		this.internalLogger(NONE, message, this.buildOrigin());
	}

	@Override
	public void log(String format, Object... args) {
		this.internalLogger(NONE, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void log(LoggerLevel level, String message) {
		this.internalLogger(level, message, this.buildOrigin());
	}

	@Override
	public void log(LoggerLevel level, String format, Object... args) {
		this.internalLogger(level, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void debug(String message) {
		this.internalLogger(DEBUG, message, this.buildOrigin());
	}

	@Override
	public void debug(String format, Object... args) {
		this.internalLogger(DEBUG, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void system(String message) {
		this.internalLogger(SYSTEM, message, this.buildOrigin());
	}

	@Override
	public void system(String format, Object... args) {
		this.internalLogger(SYSTEM, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void info(String message) {
		this.internalLogger(INFO, message, this.buildOrigin());
	}

	@Override
	public void info(String format, Object... args) {
		this.internalLogger(INFO, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void notice(String message) {
		this.internalLogger(NOTICE, message, this.buildOrigin());
	}

	@Override
	public void notice(String format, Object... args) {
		this.internalLogger(NOTICE, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void packet(String message) {
		this.internalLogger(PACKET, message, this.buildOrigin());
	}

	@Override
	public void packet(String format, Object... args) {
		this.internalLogger(PACKET, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void warn(String message) {
		this.internalLogger(WARN, message, this.buildOrigin());
	}

	@Override
	public void warn(String format, Object... args) {
		this.internalLogger(WARN, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void error(String message) {
		this.internalLogger(ERROR, message, this.buildOrigin());
	}

	@Override
	public void error(String format, Object... args) {
		this.internalLogger(ERROR, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void fatal(String message) {
		this.internalLogger(FATAL, message, this.buildOrigin());
	}

	@Override
	public void fatal(String format, Object... args) {
		this.internalLogger(FATAL, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void exception(String message) {
		this.internalLogger(EXCEPTION, message, this.buildOrigin());
	}

	@Override
	public void exception(String format, Object... args) {
		this.internalLogger(EXCEPTION, String.format(format, args), this.buildOrigin());
	}

	@Override
	public void exception(Exception e) {
		this.internalLogger(EXCEPTION, String.format("%s: %s", nameOf(e), e.getMessage()), this.buildOrigin());
	}

	@Override
	public void exception(Exception e, String message) {
		this.internalLogger(EXCEPTION, String.format("%s; %s: %s", message, nameOf(e), e.getMessage()), this.buildOrigin());
	}

	@Override
	public void exception(Exception e, String format, Object... args) {
		this.internalLogger(EXCEPTION, String.format("%s; %s: %s", String.format(format, args), nameOf(e), e.getMessage()), this.buildOrigin());
	}

	@Override
	public void trace(Exception e) {
		this.internalLogger(EXCEPTION, StringUtils.getStackTrace(e), this.buildOrigin());
	}

	@Override
	public void close() throws IOException {
		if (this.loggerOutputSubject != null) {
			this.loggerOutputSubject.close();
			this.loggerOutputSubject = null;
		}
	}

	protected void upperStackTrace(int levels) {
		if (this.offsetTrace == null)
			this.offsetTrace = DEFAULT_OFFSET_TRACE;

		this.setOffsetTrace(this.getOffsetTrace() + levels);
	}

	public static StackTraceElement getOrigin(int offsetTrace) {
		Thread thread = Thread.currentThread();

		if (++offsetTrace >= 0 && offsetTrace < thread.getStackTrace().length)
			return thread.getStackTrace()[offsetTrace];

		throw LoggerAbstractionError.offsetTrace(offsetTrace);
	}

	@Override
	public String toString() {
		return StringUtils.objectToString(
			this,
			"name", this.getName(),
			"currentDateFormatted", this.getCurrentDateFormatted()
		);
	}
}
