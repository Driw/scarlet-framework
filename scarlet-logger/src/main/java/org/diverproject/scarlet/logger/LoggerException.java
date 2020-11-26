package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class LoggerException extends ScarletRuntimeException
{
	private static final long serialVersionUID = -7146072104980564179L;

	public LoggerException(Language language, Object... args)
	{
		super(language, args);
	}

	public LoggerException(Exception e, Language language, Object... args)
	{
		super(e, language, args);
	}

}
