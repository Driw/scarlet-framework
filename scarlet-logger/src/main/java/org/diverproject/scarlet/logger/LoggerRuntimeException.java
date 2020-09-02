package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class LoggerRuntimeException extends ScarletRuntimeException
{
	private static final long serialVersionUID = -7146072104980564179L;

	public LoggerRuntimeException(Language language)
	{
		super(language);
	}

	public LoggerRuntimeException(Language language, Object... args)
	{
		super(language, args);
	}

	public LoggerRuntimeException(Exception e)
	{
		super(e);
	}

	public LoggerRuntimeException(Exception e, Language language)
	{
		super(e, language);
	}

	public LoggerRuntimeException(Exception e, Language language, Object... args)
	{
		super(e, language, args);
	}
}
