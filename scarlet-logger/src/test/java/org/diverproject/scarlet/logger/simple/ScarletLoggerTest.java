package org.diverproject.scarlet.logger.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.MarkerAdapter;
import org.diverproject.scarlet.logger.message.LoggerMessage;
import org.diverproject.scarlet.logger.message.SimpleLoggerMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class ScarletLoggerTest {

	private static final String SOCKET_HOST = "localhost";
	private static final int SOCKET_PORT = 65535;

	private static final String MESSAGE = "A test message";
	private static final String MESSAGE_FORMAT = "A test {}";
	private static final String MESSAGE_ARGUMENT = "message";
	private static final String MESSAGE_FORMAT2 = "A {} {}";
	private static final String MESSAGE_ARGUMENT21 = "test";
	private static final String MESSAGE_ARGUMENT22 = "message";
	private static final String MESSAGE_FORMAT3 = "{} {} {}";
	private static final String MESSAGE_ARGUMENT31 = "A";
	private static final String MESSAGE_ARGUMENT32 = "test";
	private static final String MESSAGE_ARGUMENT33 = "message";
	private static final Language LANGUAGE = ScarletLoggerTest.language();
	private static final String THROWABLE_MESSAGE_VALUE = "A test throwable";
	private static final Exception THROWABLE = new Exception(THROWABLE_MESSAGE_VALUE);
	private static final Marker MARKER = buildMarker();
	private static final Object[] NO_ARGUMENT = new Object[0];

	private static final String THROWABLE_MESSAGE = ExceptionUtils.getStackTrace(THROWABLE);

	private static final String BREAK_LINE = System.lineSeparator();
	private ByteArrayOutputStream output;
	private SimpleLogger simpleLogger;

	@BeforeEach
	void setUp() {
		this.output = new ByteArrayOutputStream();

		WriterAppender writerAppender = new WriterAppender(new SimpleLayout(), this.output);
		Logger logger = Logger.getLogger("test");
		logger.addAppender(writerAppender);
		logger.setLevel(Level.ALL);
		logger.getLoggerRepository().setThreshold(Level.ALL);

		this.simpleLogger = new SimpleLogger("test", logger);
	}

	@AfterEach
	void tearDown() throws IOException {
		this.output.close();
	}

	@Test
	void testCanLog() {
		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.SYSTEM.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.SYSTEM.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.NONE.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.TRACE.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.TRACE.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.SYSTEM.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.DEBUG.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.DEBUG.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.TRACE.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.NOTICE.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.NOTICE.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.DEBUG.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.INFO.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.INFO.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.NOTICE.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.PACKET.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.PACKET.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.NOTICE.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.WARN.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.WARN.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.PACKET.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.ERROR.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.ERROR.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.WARN.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.FATAL.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.FATAL.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.ERROR.level()));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.EXCEPTION.level());
		assertTrue(this.simpleLogger.canLog(ScarletLoggerLevel.EXCEPTION.level()));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.FATAL.level()));

		this.simpleLogger.getLogger().setLevel(Level.OFF);
		assertTrue(this.simpleLogger.canLog(Level.OFF));
		assertFalse(this.simpleLogger.canLog(ScarletLoggerLevel.EXCEPTION.level()));
	}

	@Test
	void testHandle() {
		LoggerMessage loggerMessage = this.simpleLogger.message(ScarletLoggerLevel.ERROR, THROWABLE, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		this.simpleLogger.handle(loggerMessage);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.EXCEPTION.level());
		this.simpleLogger.handle(loggerMessage);
		assertEquals("", this.lastLogMessage());

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.ERROR.level());
		this.simpleLogger.close();
		this.simpleLogger.handle(loggerMessage);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testFeedLine() {
		this.simpleLogger.feedLine();
		assertEquals(" - ".concat(BREAK_LINE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testLog() {
		this.simpleLogger.log(MESSAGE);
		assertEquals(" - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals(" - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(LANGUAGE);
		assertEquals(" - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(LANGUAGE, NO_ARGUMENT);
		assertEquals(" - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(ScarletLoggerLevel.SYSTEM, MESSAGE);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(ScarletLoggerLevel.SYSTEM, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.log(ScarletLoggerLevel.SYSTEM, LANGUAGE, NO_ARGUMENT);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testTrace() {
		this.simpleLogger.trace(MESSAGE);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MESSAGE, THROWABLE);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.trace(MARKER, MESSAGE);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MARKER, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MARKER, MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MARKER, MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(MARKER, MESSAGE, THROWABLE);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.trace(THROWABLE);
		assertEquals("TRACE - ".concat(THROWABLE_MESSAGE_VALUE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.trace(LANGUAGE);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.trace(LANGUAGE, MESSAGE_ARGUMENT);
		assertEquals("TRACE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testIsTraceEnabled() {
		assertTrue(this.simpleLogger.isTraceEnabled());
		assertTrue(this.simpleLogger.isTraceEnabled(MARKER));

		this.simpleLogger.getLogger().setLevel(Level.OFF);
		assertFalse(this.simpleLogger.isTraceEnabled());
		assertFalse(this.simpleLogger.isTraceEnabled(MARKER));

		this.simpleLogger.trace(MESSAGE);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testDebug() {
		this.simpleLogger.debug(MESSAGE);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MESSAGE, THROWABLE);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.debug(MARKER, MESSAGE);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MARKER, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MARKER, MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MARKER, MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(MARKER, MESSAGE, THROWABLE);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.debug(LANGUAGE);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.debug(LANGUAGE, NO_ARGUMENT);
		assertEquals("DEBUG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testIsDebugEnabled() {
		assertTrue(this.simpleLogger.isDebugEnabled());
		assertTrue(this.simpleLogger.isDebugEnabled(MARKER));

		this.simpleLogger.getLogger().setLevel(Level.OFF);
		assertFalse(this.simpleLogger.isDebugEnabled());
		assertFalse(this.simpleLogger.isDebugEnabled(MARKER));

		this.simpleLogger.debug(MESSAGE);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testSystem() {
		this.simpleLogger.system(MESSAGE);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.system(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.system(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.system(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.system(MESSAGE, THROWABLE);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.system(LANGUAGE);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.system(LANGUAGE, NO_ARGUMENT);
		assertEquals("SYSTEM - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testInfo() {
		this.simpleLogger.info(MESSAGE);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MESSAGE, THROWABLE);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.info(MARKER, MESSAGE);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MARKER, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MARKER, MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MARKER, MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(MARKER, MESSAGE, THROWABLE);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.info(LANGUAGE);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.info(LANGUAGE, NO_ARGUMENT);
		assertEquals("INFO - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testIsInfoEnabled() {
		assertTrue(this.simpleLogger.isInfoEnabled());
		assertTrue(this.simpleLogger.isInfoEnabled(MARKER));

		this.simpleLogger.getLogger().setLevel(Level.OFF);
		assertFalse(this.simpleLogger.isInfoEnabled());
		assertFalse(this.simpleLogger.isInfoEnabled(MARKER));

		this.simpleLogger.info(MESSAGE);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testNotice() {
		this.simpleLogger.notice(MESSAGE);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.notice(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.notice(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.notice(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.notice(MESSAGE, THROWABLE);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.notice(LANGUAGE);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.notice(LANGUAGE, NO_ARGUMENT);
		assertEquals("NOTICE - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testPacket() {
		this.simpleLogger.packet(MESSAGE);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.packet(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.packet(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.packet(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.packet(MESSAGE, THROWABLE);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.packet(LANGUAGE);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.packet(LANGUAGE, NO_ARGUMENT);
		assertEquals("PACKET - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testWarn() {
		this.simpleLogger.warn(MESSAGE);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MESSAGE, THROWABLE);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.warn(MARKER, MESSAGE);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MARKER, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MARKER, MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MARKER, MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(MARKER, MESSAGE, THROWABLE);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.warn(LANGUAGE);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.warn(LANGUAGE, NO_ARGUMENT);
		assertEquals("WARN - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testIsWarnEnabled() {
		assertTrue(this.simpleLogger.isWarnEnabled());
		assertTrue(this.simpleLogger.isWarnEnabled(MARKER));

		this.simpleLogger.getLogger().setLevel(ScarletLoggerLevel.ERROR.level());
		this.simpleLogger.getLogger().getLoggerRepository().setThreshold(ScarletLoggerLevel.ERROR.level());
		assertFalse(this.simpleLogger.isWarnEnabled());
		assertFalse(this.simpleLogger.isWarnEnabled(MARKER));

		this.simpleLogger.warn(MESSAGE);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testError() {
		this.simpleLogger.error(MESSAGE);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MESSAGE, THROWABLE);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.error(MARKER, MESSAGE);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MARKER, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MARKER, MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MARKER, MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(MARKER, MESSAGE, THROWABLE);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.error(LANGUAGE);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.error(LANGUAGE, NO_ARGUMENT);
		assertEquals("ERROR - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testIsErrorEnabled() {
		assertTrue(this.simpleLogger.isErrorEnabled());
		assertTrue(this.simpleLogger.isErrorEnabled(MARKER));

		this.simpleLogger.getLogger().setLevel(Level.OFF);
		assertFalse(this.simpleLogger.isErrorEnabled());
		assertFalse(this.simpleLogger.isErrorEnabled(MARKER));

		this.simpleLogger.warn(MESSAGE);
		assertEquals("", this.lastLogMessage());
	}

	@Test
	void testFatal() {
		this.simpleLogger.fatal(MESSAGE);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.fatal(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.fatal(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.fatal(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.fatal(MESSAGE, THROWABLE);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.fatal(LANGUAGE);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.fatal(LANGUAGE, NO_ARGUMENT);
		assertEquals("FATAL - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());
	}

	@Test
	void testException() {
		this.simpleLogger.exception(MESSAGE);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(MESSAGE_FORMAT2, MESSAGE_ARGUMENT21, MESSAGE_ARGUMENT22);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(MESSAGE_FORMAT3, MESSAGE_ARGUMENT31, MESSAGE_ARGUMENT32, MESSAGE_ARGUMENT33);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(MESSAGE, THROWABLE);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.exception(LANGUAGE);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(LANGUAGE, NO_ARGUMENT);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE), this.lastLogMessage());

		this.simpleLogger.exception(THROWABLE);
		assertEquals("EMERG - ".concat(THROWABLE_MESSAGE_VALUE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.exception(THROWABLE, MESSAGE);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.exception(THROWABLE, MESSAGE_FORMAT, MESSAGE_ARGUMENT);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.exception(THROWABLE, LANGUAGE);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());

		this.simpleLogger.exception(THROWABLE, LANGUAGE, NO_ARGUMENT);
		assertEquals("EMERG - ".concat(MESSAGE).concat(BREAK_LINE).concat(THROWABLE_MESSAGE), this.lastLogMessage());
	}

	@Test
	void testClose() {
		assertFalse(this.simpleLogger.isClosed());
		this.simpleLogger.close();

		this.simpleLogger.handle(new SimpleLoggerMessage().setLoggerLevel(ScarletLoggerLevel.INFO).setMessage(MESSAGE));
		assertTrue(this.simpleLogger.isClosed());
		assertEquals("", this.lastLogMessage());
	}

	private String lastLogMessage() {
		byte[] data = this.output.toByteArray();
		this.output.reset();

		return new String(data);
	}

	private static Language language() {
		return new Language() {
			@Override
			public int getCode() {
				return 0;
			}

			@Override
			public String getFormat() {
				return ScarletLoggerTest.MESSAGE;
			}

			@Override
			public void setFormat(String format) {
			}
		};
	}

	private static Marker buildMarker() {
		return new MarkerAdapter("test-marker");
	}

}
