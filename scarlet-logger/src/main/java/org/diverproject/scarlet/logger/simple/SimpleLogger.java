package org.diverproject.scarlet.logger.simple;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.diverproject.scarlet.logger.message.LoggerMessage;
import org.diverproject.scarlet.util.IntegerUtils;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.NormalizedParameters;

public class SimpleLogger extends ScarletLogger {

	public SimpleLogger(String name, Logger logger) {
		super(name, logger);
	}

	public void handle(LoggerMessage loggerMessage) {
		Level log4jLevel = this.toLog4jLevel(loggerMessage.getLoggerLevel().getLevel().toInt());

		if (!this.canLog(log4jLevel)) {
			return;
		}

		NormalizedParameters np = NormalizedParameters.normalize(loggerMessage.getMessage(), loggerMessage.getArguments(), loggerMessage.getThrowable());
		String formattedMessage = MessageFormatter.basicArrayFormat(np.getMessage(), np.getArguments());

		this.getLogger().log(CALLER_FUNCTION, log4jLevel, formattedMessage, np.getThrowable());
	}

	private Level toLog4jLevel(int slf4jLevelInt) {
		if (slf4jLevelInt < Level.TRACE_INT)
			return this.isTraceEnabled() ? Level.TRACE : Level.DEBUG;

		if (slf4jLevelInt <= Priority.DEBUG_INT)
			return Level.DEBUG;

		if (IntegerUtils.hasBetween(slf4jLevelInt, Priority.INFO_INT, Priority.WARN_INT - 1))
			return Level.INFO;

		if (IntegerUtils.hasBetween(slf4jLevelInt, Priority.WARN_INT, Priority.ERROR_INT - 1))
			return Level.WARN;

		if (IntegerUtils.hasBetween(slf4jLevelInt, Priority.ERROR_INT, Priority.FATAL_INT - 1))
			return Level.ERROR;

		if (IntegerUtils.hasBetween(slf4jLevelInt, Priority.FATAL_INT, Priority.OFF_INT - 1))
			return Level.FATAL;

		throw new IllegalStateException("Level number " +slf4jLevelInt+ " is not recognized.");
	}

}
