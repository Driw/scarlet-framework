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
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diverproject.scarlet.logger.LoggerObserver;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Default Logger")
public class TestDefaultLogger {

	private static final String DATE = "2019-07-31 15:11:13";
	private static final String MESSAGE = "a text log message";
	private static final String FORMAT = "a text log message with %s";
	private static final String ARGUMENT = "format method";
	private static final String MESSAGE_FORMATTED = String.format(FORMAT, ARGUMENT);
	private static final String EXCEPTION_MESSAGE = "a exception message";
	private String lastOutput;

	@Test
	@DisplayName("Build the origin")
	public void testBuildOrigin() {
		DefaultLogger defaultLogger = new DefaultLogger("default");
		StackTraceElement origin = defaultLogger.buildOrigin(0); StackTraceElement stackTraceElement = this.getStackTraceElement();

		assertEquals(origin.getFileName(), stackTraceElement.getFileName());
		assertEquals(origin.getClassName(), stackTraceElement.getClassName());
		assertEquals(origin.getMethodName(), stackTraceElement.getMethodName());
		assertEquals(origin.getLineNumber(), stackTraceElement.getLineNumber());

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Internal logger")
	public void testIntegernalLogger() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.internalLogger(NONE, MESSAGE, null); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(DEBUG, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(SYSTEM, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(INFO, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(NOTICE, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(PACKET, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(WARN, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(ERROR, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(FATAL, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.internalLogger(EXCEPTION, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogDebug() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.debug(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.debug(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log system message")
	public void testLogSystem() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.system(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.system(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log information message")
	public void testLogInfo() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.info(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.info(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log notice message")
	public void testLogNotice() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.notice(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.notice(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log packet message")
	public void testLogPacket() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.packet(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.packet(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log warn message")
	public void testLogWarn() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.warn(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.warn(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogError() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.error(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.error(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log fatal message")
	public void testLogFatal() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.fatal(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.fatal(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log exception message")
	public void testLogException() {
		DefaultLogger defaultLogger = this.newDefaultLogger();

		defaultLogger.exception(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE));

		defaultLogger.exception(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		Exception exception = new Exception(EXCEPTION_MESSAGE);

		defaultLogger.exception(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s: %s", DATE, origin, nameOf(exception), exception.getMessage()));

		defaultLogger.exception(exception, MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE, nameOf(exception), exception.getMessage()));

		defaultLogger.exception(exception, FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE_FORMATTED, nameOf(exception), exception.getMessage()));

		defaultLogger.trace(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, StringUtils.getStackTrace(exception)));

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DefaultLogger newDefaultLogger() {
		LoggerSubject loggerSubject = new DefaultLoggerSubject();
		loggerSubject.unregister(DefaultLoggerObserver.getInstance());
		loggerSubject.register(new LoggerObserver() {
			@Override
			public void onMessage(String message) {
			}

			@Override
			public void onMessage(LoggerSubject subject, MessageOutput message) {
				this.onMessage(message.getOutput());
			}
		});
		return new DefaultLogger("default", loggerSubject) {
			@Override
			public String getCurrentDateFormatted() {
				return DATE;
			}

			@Override
			public void log(MessageOutput messageOutput) {
				TestDefaultLogger.this.lastOutput = messageOutput.getOutput();
			}
		};
	}

	private StackTraceElement getStackTraceElement() {
		return Thread.currentThread().getStackTrace()[2];
	}

	private String getOrigin() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];

		return String.format(
			"%s.%s:%d",
			stackTraceElement.getClassName(),
			stackTraceElement.getMethodName(),
			stackTraceElement.getLineNumber()
		);
	}
}
