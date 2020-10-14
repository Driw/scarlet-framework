package org.diverproject.scarlet.context.manager;

public interface ManagerQueue extends Iterable<Manager> {
	boolean add(Manager manager);
}
