package org.diverproject.scarlet.logger.simple;

import org.apache.log4j.Level;
import org.diverproject.scarlet.logger.LoggerLevel;

public class ScarletLoggerLevel extends Level implements LoggerLevel {

	private static final int NONE_INT = 0;
	public static final int SYSTEM_INT = 1000;
	public static final int NOTICE_INT = 15000;
	public static final int PACKET_INT = 25000;
	public static final int EXCEPTION_INT = 60000;

	public static final org.diverproject.scarlet.logger.LoggerLevel NONE		= new ScarletLoggerLevel(NONE_INT, "LOG", 7);
	public static final org.diverproject.scarlet.logger.LoggerLevel SYSTEM		= new ScarletLoggerLevel(SYSTEM_INT, "SYSTEM", 7);
	public static final org.diverproject.scarlet.logger.LoggerLevel TRACE		= new ScarletLoggerLevel(TRACE_INT, "TRACE", 7);
	public static final org.diverproject.scarlet.logger.LoggerLevel DEBUG		= new ScarletLoggerLevel(DEBUG_INT, "DEBUG", 7);
	public static final org.diverproject.scarlet.logger.LoggerLevel NOTICE		= new ScarletLoggerLevel(NOTICE_INT, "NOTICE", 5);
	public static final org.diverproject.scarlet.logger.LoggerLevel INFO		= new ScarletLoggerLevel(INFO_INT, "INFO", 6);
	public static final org.diverproject.scarlet.logger.LoggerLevel PACKET		= new ScarletLoggerLevel(PACKET_INT, "PACKET", 5);
	public static final org.diverproject.scarlet.logger.LoggerLevel WARN		= new ScarletLoggerLevel(WARN_INT, "WARN", 4);
	public static final org.diverproject.scarlet.logger.LoggerLevel ERROR		= new ScarletLoggerLevel(ERROR_INT, "ERROR", 3);
	public static final org.diverproject.scarlet.logger.LoggerLevel FATAL		= new ScarletLoggerLevel(FATAL_INT, "FATAL", 2);
	public static final org.diverproject.scarlet.logger.LoggerLevel EXCEPTION	= new ScarletLoggerLevel(EXCEPTION_INT, "EMERG", 0);

	public ScarletLoggerLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}

	@Override
	public Level level() {
		return this;
	}

	@Override
	public String label() {
		return super.toString();
	}

}
