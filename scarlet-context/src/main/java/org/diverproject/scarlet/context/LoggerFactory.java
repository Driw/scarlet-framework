package org.diverproject.scarlet.context;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.ScarletLoggers;

public class LoggerFactory {

	@Getter
	@Setter
	private static LoggerBuilder loggerBuilder = new DefaultLoggerBuilder();

	private LoggerFactory() { }

	public static Logger get(Class<?> aClass) {
		if (ScarletLoggers.getInstance().contains(aClass.getName())) {
			return ScarletLoggers.getInstance().get(aClass.getName());
		}

		return ScarletLoggers.getInstance().add(loggerBuilder.generateKey(aClass), loggerBuilder.generateLoggerClass(aClass));
	}

}
