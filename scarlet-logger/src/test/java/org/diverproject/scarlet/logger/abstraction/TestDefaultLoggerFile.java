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

import org.diverproject.scarlet.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

@DisplayName("Default Logger File")
public class TestDefaultLoggerFile {
	private static final String DATE = "2019-07-31 15:11:13";
	private static final String MESSAGE = "a text log message";
	private static final String FORMAT = "a text log message with %s";
	private static final String ARGUMENT = "format method";
	private static final String MESSAGE_FORMATTED = String.format(FORMAT, ARGUMENT);
	private static final String EXCEPTION_MESSAGE = "a exception message";
	private static final String LOGGER_NAME = "default";
	private static final String LOGGER_PATHNAME = "default.log";

	@Test
	@DisplayName("Build the origin")
	public void testBuildOrigin() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();
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

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Internal logger")
	public void testIntegernalLogger() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.internalLogger(NONE, MESSAGE, null); String origin1 = this.getOrigin();
		defaultLogger.internalLogger(DEBUG, MESSAGE, null); String origin2 = this.getOrigin();
		defaultLogger.internalLogger(SYSTEM, MESSAGE, null); String origin3 = this.getOrigin();
		defaultLogger.internalLogger(INFO, MESSAGE, null); String origin4 = this.getOrigin();
		defaultLogger.internalLogger(NOTICE, MESSAGE, null); String origin5 = this.getOrigin();
		defaultLogger.internalLogger(PACKET, MESSAGE, null); String origin6 = this.getOrigin();
		defaultLogger.internalLogger(WARN, MESSAGE, null); String origin7 = this.getOrigin();
		defaultLogger.internalLogger(ERROR, MESSAGE, null); String origin8 = this.getOrigin();
		defaultLogger.internalLogger(FATAL, MESSAGE, null); String origin9 = this.getOrigin();
		defaultLogger.internalLogger(EXCEPTION, MESSAGE, null); String origin10 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), String.format("%s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[DEBUG ] %s | %s - %s", DATE, origin2, MESSAGE));
		assertEquals(lines.poll(), String.format("[SYSTEM] %s | %s - %s", DATE, origin3, MESSAGE));
		assertEquals(lines.poll(), String.format("[INFO  ] %s | %s - %s", DATE, origin4, MESSAGE));
		assertEquals(lines.poll(), String.format("[NOTICE] %s | %s - %s", DATE, origin5, MESSAGE));
		assertEquals(lines.poll(), String.format("[PACKET] %s | %s - %s", DATE, origin6, MESSAGE));
		assertEquals(lines.poll(), String.format("[WARN  ] %s | %s - %s", DATE, origin7, MESSAGE));
		assertEquals(lines.poll(), String.format("[ERROR ] %s | %s - %s", DATE, origin8, MESSAGE));
		assertEquals(lines.poll(), String.format("[FATAL ] %s | %s - %s", DATE, origin9, MESSAGE));
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s", DATE, origin10, MESSAGE));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogDebug() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.debug(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.debug(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[DEBUG ] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[DEBUG ] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log system message")
	public void testLogSystem() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.system(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.system(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[SYSTEM] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[SYSTEM] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log information message")
	public void testLogInfo() {

		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.info(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.info(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[INFO  ] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[INFO  ] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log notice message")
	public void testLogNotice() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.notice(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.notice(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[NOTICE] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[NOTICE] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log packet message")
	public void testLogPacket() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.packet(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.packet(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[PACKET] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[PACKET] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log warn message")
	public void testLogWarn() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.warn(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.warn(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[WARN  ] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[WARN  ] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log debug message")
	public void testLogError() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.error(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.error(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[ERROR ] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[ERROR ] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log fatal message")
	public void testLogFatal() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();

		defaultLogger.fatal(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.fatal(FORMAT, ARGUMENT); String origin2 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[FATAL ] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[FATAL ] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));

		this.deleteTestFile();
	}

	@Test
	@DisplayName("Log exception message")
	public void testLogException() {
		DefaultLoggerFile defaultLogger = this.newDefaultLoggerFile();
		Exception exception = new Exception(EXCEPTION_MESSAGE);

		defaultLogger.exception(MESSAGE); String origin1 = this.getOrigin();
		defaultLogger.exception(FORMAT, ARGUMENT); String origin2 = this.getOrigin();
		defaultLogger.exception(exception); String origin3 = this.getOrigin();
		defaultLogger.exception(exception, MESSAGE); String origin4 = this.getOrigin();
		defaultLogger.exception(exception, FORMAT, ARGUMENT); String origin5 = this.getOrigin();
		defaultLogger.trace(exception); String origin6 = this.getOrigin();

		try {
			defaultLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Queue<String> lines = this.readLoggerOutput();

		assertEquals(lines.poll(), "");
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s", DATE, origin1, MESSAGE));
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s", DATE, origin2, MESSAGE_FORMATTED));
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s: %s", DATE, origin3, nameOf(exception), exception.getMessage()));
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin4, MESSAGE, nameOf(exception), exception.getMessage()));
		assertEquals(lines.poll(), String.format("[EXCEPT] %s | %s - %s; %s: %s", DATE, origin5, MESSAGE_FORMATTED, nameOf(exception), exception.getMessage()));

		String exceptionTrace = String.format("[EXCEPT] %s | %s - %s", DATE, origin6, StringUtils.getStackTrace(exception));

		for (String line : exceptionTrace.split("\\r?\\n"))
			assertEquals(lines.poll(), line);

		this.deleteTestFile();
	}

	private DefaultLoggerFile newDefaultLoggerFile() {
		return new DefaultLoggerFile(LOGGER_NAME, LOGGER_PATHNAME) {
			@Override
			public String getCurrentDateFormatted() {
				return DATE;
			}
		};
	}

	private Queue<String> readLoggerOutput() {
		try {

			Queue<String> lines = new LinkedList<>();
			InputStream inputStream = new FileInputStream(LOGGER_PATHNAME);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			while (bufferedReader.ready())
				lines.offer(bufferedReader.readLine());

			bufferedReader.close();

			return lines;

		} catch (IOException e) {
			return null;
		}
	}

	private void deleteTestFile() {
		new File(LOGGER_PATHNAME).delete();
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
