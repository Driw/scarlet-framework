package org.diverproject.scarlet.logger;

import lombok.Getter;
import org.diverproject.scarlet.util.Bitwise;

public class ScarletLoggerConfig {

	public static final int LOG_FILE_IN_SAME_FILE = 0x0001;
	public static final int LOG_FILE_BY_PACKAGE = 0x0002;
	public static final int LOG_FILE_BY_PACKAGE_CLASS = 0x0004;

	@Getter
	private static final ScarletLoggerConfig instance = new ScarletLoggerConfig();

	private @Getter Bitwise logFileOption;

	private ScarletLoggerConfig() {
		this.logFileOption = new Bitwise(LOG_FILE_IN_SAME_FILE);
	}

}
