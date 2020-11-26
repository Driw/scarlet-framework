package org.slf4j.scarlet.logger;

import lombok.Data;
import lombok.Getter;
import org.diverproject.scarlet.logger.DefaultMapLogger;
import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.MapLogger;
import org.diverproject.scarlet.logger.simple.SimpleLogger;
import org.slf4j.ILoggerFactory;

import java.util.Objects;
import java.util.function.Function;

@Data
class ScarletLoggerFactory implements ILoggerFactory {

	@Getter
	private static final ScarletLoggerFactory instance = new ScarletLoggerFactory();

	private MapLogger<Logger> cache = new DefaultMapLogger<>();
	private Function<String, Logger> loggerGenerator;

	@Override
	public Logger getLogger(String loggerName) {
		Logger logger = this.getCache().get(loggerName);

		if (Objects.isNull(logger)) {
			if (Objects.isNull(this.getLoggerGenerator()))
				this.setLoggerGenerator(this::defaultLoggerGenerator);

			logger = this.getLoggerGenerator().apply(loggerName);
		}

		return logger;
	}

	private Logger defaultLoggerGenerator(String loggerName) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(loggerName);
		return new SimpleLogger(loggerName, logger);
	}

}
