package org.diverproject.scarlet.context;

import org.diverproject.scarlet.context.reflection.ReflectionInterfaceUtils;

public interface ScarletContext {

	void initialize(String[] args);

	public static ScarletContext start(String[] args) {
		ScarletContext scarletContext = ReflectionInterfaceUtils.getInstanceOf(ScarletContext.class);
		scarletContext.initialize(args);

		return scarletContext;
	}
}
