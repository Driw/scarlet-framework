package org.diverproject.scarlet.util;

import org.diverproject.scarlet.ScarletRuntimeException;

public class ScarletUtilsError {

	private ScarletUtilsError() { }

	public static ScarletRuntimeException sleep(InterruptedException e) {
		return new ScarletRuntimeException(e);
	}

}
