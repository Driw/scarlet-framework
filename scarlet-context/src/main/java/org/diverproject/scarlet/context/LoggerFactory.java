package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.ScarletLoggerParser;

public class LoggerFactory {

	@Getter
	@Setter
	private static LoggerBuilder loggerBuilder = new DefaultLoggerBuilder();

	private LoggerFactory() { }

	public static Logger get(Class<?> aClass) {
		return ScarletLoggerParser.get(aClass);
	}

}
