package org.diverproject.scarlet.logger.abstraction;

import static org.diverproject.scarlet.logger.language.LoggerAbstractionLanguage.CANNOT_REGISTER_LOGGER_OBSERVER;
import static org.diverproject.scarlet.logger.language.LoggerAbstractionLanguage.FEED_LINE_FILE_CHANNEL;
import static org.diverproject.scarlet.logger.language.LoggerAbstractionLanguage.OFFSET_TRACE;
import static org.diverproject.scarlet.logger.language.LoggerAbstractionLanguage.OPEN_FILE_CHANNEL;
import static org.diverproject.scarlet.logger.language.LoggerAbstractionLanguage.WRITE_FILE_CHANNEL;
import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import org.diverproject.scarlet.logger.LoggerObserver;

import java.io.File;
import java.io.IOException;

public class LoggerAbstractionError {

	public static LoggerAbstractionException cannotRegisterLogger(LoggerObserver loggerObserver) {
		return new LoggerAbstractionException(CANNOT_REGISTER_LOGGER_OBSERVER, nameOf(loggerObserver));
	}

	public static LoggerAbstractionException openFileChannel(IOException e, File file) {
		return new LoggerAbstractionException(e, OPEN_FILE_CHANNEL, file.getAbsoluteFile());
	}

	public static LoggerAbstractionException writeFileChannel(IOException e, File file) {
		return new LoggerAbstractionException(e, WRITE_FILE_CHANNEL, file.getAbsoluteFile());
	}

	public static LoggerAbstractionException feedLineFileChannel(IOException e, File file) {
		return new LoggerAbstractionException(e, FEED_LINE_FILE_CHANNEL, file.getAbsolutePath());
	}

	public static LoggerAbstractionException offsetTrace(int offsetTrace) {
		return new LoggerAbstractionException(OFFSET_TRACE, offsetTrace);
	}
}
