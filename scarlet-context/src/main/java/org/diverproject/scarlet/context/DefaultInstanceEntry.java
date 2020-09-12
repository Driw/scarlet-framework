package org.diverproject.scarlet.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DefaultInstanceEntry<K, V> implements InstanceEntry<K, V> {
	private K key;
	private V value;
}
