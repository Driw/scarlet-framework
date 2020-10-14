package org.diverproject.scarlet.context.manager;

public interface Manager extends Comparable<Manager> {
	void tick();
	void start();
	void restart();
	void stop();
	void finish();

	int getOrder();
	ManagerStatus getStatus();
}
