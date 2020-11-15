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

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.LoggerObserver;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Default Logger")
public class DefaultLoggerTest {

	private static final String DATE = "2019-07-31 15:11:13";
	private static final String MESSAGE = "a text log message";
	private static final String FORMAT = "a text log message with %s";
	private static final String ARGUMENT = "format method";
	private static final String MESSAGE_FORMATTED = String.format(FORMAT, ARGUMENT);
	private static final String EXCEPTION_MESSAGE = "a exception message";
	private static final Language LANGUAGE_MESSAGE = new Language() {
		@Override
		public String getFormat() {
			return "a text log message";
		}

		@Override
		public void setFormat(String format) { }

		@Override
		public int getCode() {
			return 0;
		}
	};
	private static final Language LANGUAGE_FORMAT = new Language() {
		@Override
		public String getFormat() {
			return "a text log message with %s";
		}

		@Override
		public void setFormat(String format) { }

		@Override
		public int getCode() {
			return 0;
		}
	};
	private String lastOutput;
	private DefaultLogger defaultLogger;

	@BeforeEach
	void beforeEach() {
		this.defaultLogger = this.newDefaultLogger();
	}

	@Test
	public void testBuildOrigin() {
		StackTraceElement origin = this.defaultLogger.buildOrigin(0); StackTraceElement stackTraceElement = this.getStackTraceElement();

		assertEquals(origin.getFileName(), stackTraceElement.getFileName());
		assertEquals(origin.getClassName(), stackTraceElement.getClassName());
		assertEquals(origin.getMethodName(), stackTraceElement.getMethodName());
		assertEquals(origin.getLineNumber(), stackTraceElement.getLineNumber());
	}

	@Test
	public void testInternalLogger() {
		this.defaultLogger.internalLogger(NONE, MESSAGE, null); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(DEBUG, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(SYSTEM, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(INFO, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(NOTICE, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(PACKET, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(WARN, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(ERROR, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(FATAL, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.internalLogger(EXCEPTION, MESSAGE, null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE));
	}

	@Test
	public void testLog() {
		this.defaultLogger.log(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.log(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.log(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.log(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.log(DEBUG, MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));
	}

	@Test
	public void testLogDebug() {
		this.defaultLogger.debug(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.debug(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.debug(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.debug(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogSystem() {
		this.defaultLogger.system(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.system(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.system(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.system(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogInfo() {
		this.defaultLogger.info(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.info(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.info(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.info(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogNotice() {
		this.defaultLogger.notice(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.notice(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.notice(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.notice(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogPacket() {
		this.defaultLogger.packet(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.packet(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.packet(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.packet(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogWarn() {
		this.defaultLogger.warn(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.warn(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.warn(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.warn(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogError() {
		this.defaultLogger.error(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.error(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.error(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.error(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));
	}

	@Test
	public void testLogFatal() {
		this.defaultLogger.fatal(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.fatal(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.fatal(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.fatal(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		Exception exception = new Exception(EXCEPTION_MESSAGE);

		this.defaultLogger.exception(LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.exception(LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		this.defaultLogger.exception(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s: %s", DATE, origin, nameOf(exception), exception.getMessage()));

		this.defaultLogger.exception(exception, LANGUAGE_MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE, nameOf(exception), exception.getMessage()));

		this.defaultLogger.exception(exception, LANGUAGE_FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE_FORMATTED, nameOf(exception), exception.getMessage()));

		this.defaultLogger.trace(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, ExceptionUtils.getStackTrace(exception)));
	}

	@Test
	public void testLogException() {
		this.defaultLogger.exception(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE));

		this.defaultLogger.exception(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		Exception exception = new Exception(EXCEPTION_MESSAGE);

		this.defaultLogger.exception(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s: %s", DATE, origin, nameOf(exception), exception.getMessage()));

		this.defaultLogger.exception(exception, MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE, nameOf(exception), exception.getMessage()));

		this.defaultLogger.exception(exception, FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE_FORMATTED, nameOf(exception), exception.getMessage()));

		this.defaultLogger.trace(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, ExceptionUtils.getStackTrace(exception)));
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
				DefaultLoggerTest.this.lastOutput = messageOutput.getOutput();
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
