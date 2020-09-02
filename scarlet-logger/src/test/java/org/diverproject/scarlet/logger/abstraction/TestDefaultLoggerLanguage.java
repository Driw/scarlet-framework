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

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Default Logger Language")
public class TestDefaultLoggerLanguage {
	private static final String DATE = "2019-07-31 15:11:13";
	private static final Language MESSAGE = new Language() {
		@Override
		public String getFormat() {
			return "a text log message";
		}

		@Override
		public void setFormat(String format) {
		}

		;

		@Override
		public int getCode() {
			return 0;
		}
	};
	private static final Language FORMAT = new Language() {
		@Override
		public String getFormat() {
			return "a text log message with %s";
		}

		@Override
		public void setFormat(String format) {
		}

		;

		@Override
		public int getCode() {
			return 0;
		}
	};
	private static final String ARGUMENT = "format method";
	private static final String MESSAGE_FORMATTED = String.format(FORMAT.getFormat(), ARGUMENT);
	private static final String EXCEPTION_MESSAGE = "a exception message";
	private String lastOutput;

	@Test
	@DisplayName("Build the origin")
	public void testBuildOrigin() {
		DefaultLoggerLanguage defaultLoggerLanguage = new DefaultLoggerLanguage("default");
		StackTraceElement origin = defaultLoggerLanguage.buildOrigin(0); StackTraceElement stackTraceElement = this.getStackTraceElement();

		assertEquals(origin.getFileName(), stackTraceElement.getFileName());
		assertEquals(origin.getClassName(), stackTraceElement.getClassName());
		assertEquals(origin.getMethodName(), stackTraceElement.getMethodName());
		assertEquals(origin.getLineNumber(), stackTraceElement.getLineNumber());

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Internal logger")
	public void testIntegernalLogger() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.internalLogger(NONE, MESSAGE.getFormat(), null); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("%s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(DEBUG, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(SYSTEM, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(INFO, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(NOTICE, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(PACKET, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(WARN, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(ERROR, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(FATAL, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.internalLogger(EXCEPTION, MESSAGE.getFormat(), null); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogDebug() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.debug(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.debug(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[DEBUG ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log system message")
	public void testLogSystem() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.system(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.system(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[SYSTEM] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log information message")
	public void testLogInfo() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.info(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.info(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[INFO  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log notice message")
	public void testLogNotice() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.notice(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.notice(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[NOTICE] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log packet message")
	public void testLogPacket() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.packet(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.packet(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[PACKET] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log warn message")
	public void testLogWarn() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.warn(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.warn(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[WARN  ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogError() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.error(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.error(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[ERROR ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log fatal message")
	public void testLogFatal() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.fatal(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.fatal(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[FATAL ] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Log exception message")
	public void testLogException() {
		DefaultLoggerLanguage defaultLoggerLanguage = this.newDefaultLoggerLanguage();

		defaultLoggerLanguage.exception(MESSAGE); String origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE.getFormat()));

		defaultLoggerLanguage.exception(FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, MESSAGE_FORMATTED));

		Exception exception = new Exception(EXCEPTION_MESSAGE);

		defaultLoggerLanguage.exception(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s: %s", DATE, origin, nameOf(exception), exception.getMessage()));

		defaultLoggerLanguage.exception(exception, MESSAGE); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE.getFormat(), nameOf(exception), exception.getMessage()));

		defaultLoggerLanguage.exception(exception, FORMAT, ARGUMENT); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin, MESSAGE_FORMATTED, nameOf(exception), exception.getMessage()));

		defaultLoggerLanguage.trace(exception); origin = this.getOrigin();
		assertEquals(this.lastOutput, String.format("[EXCEPT] %s | %s - %s", DATE, origin, StringUtils.getStackTrace(exception)));

		try {
			defaultLoggerLanguage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DefaultLoggerLanguage newDefaultLoggerLanguage() {
		return new DefaultLoggerLanguage("default") {
			@Override
			public String getCurrentDateFormatted() {
				return DATE;
			}

			@Override
			public void log(MessageOutput messageOutput) {
				TestDefaultLoggerLanguage.this.lastOutput = messageOutput.getOutput();
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
