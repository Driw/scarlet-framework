package org.diverproject.scarlet.context;

public interface InstanceEntry<K, V> {
	K getKey();
	V getValue();
}
