package org.diverproject.scarlet.logger;

import java.util.Set;

public interface MapLogger<L extends Logger> {
	Set<String> names();
	boolean isInstanceNewLoggers();
	Class<? extends L> getDefaultClassLogger();
	L get(String name) throws LoggerException;
	boolean add(L logger);
	boolean remove(L logger);
	boolean remove(String name);
	boolean contains(L logger);
	boolean contains(String name);
	boolean hasAvailableName(String name);
	void clear();
	int size();
}
