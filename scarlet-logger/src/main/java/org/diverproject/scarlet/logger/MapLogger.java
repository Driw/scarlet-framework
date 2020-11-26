package org.diverproject.scarlet.logger;

public interface MapLogger<L extends Logger> {
	L get(String name);
	boolean add(L logger);
	boolean contains(L logger);
	boolean contains(String name);
	boolean hasAvailableName(String name);
	void clear();
	int size();
}
