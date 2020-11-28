package org.diverproject.scarlet.logger.simple;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.diverproject.scarlet.logger.message.LoggerMessage;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.NormalizedParameters;

public class SimpleLogger extends ScarletLogger {

	public SimpleLogger(String name, Logger logger) {
		super(name, logger);
	}

	public void handle(LoggerMessage loggerMessage) {
		if (this.isClosed())
			return;

		Level log4jLevel = loggerMessage.getLoggerLevel().level();

		if (!this.canLog(log4jLevel))
			return;

		NormalizedParameters np = NormalizedParameters.normalize(loggerMessage.getMessage(), loggerMessage.getArguments(), loggerMessage.getThrowable());
		String formattedMessage = MessageFormatter.basicArrayFormat(np.getMessage(), np.getArguments());

		this.getLogger().log(CALLER_FUNCTION, log4jLevel, formattedMessage, np.getThrowable());
	}

}
