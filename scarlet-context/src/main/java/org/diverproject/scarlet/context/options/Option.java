package org.diverproject.scarlet.context.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.diverproject.scarlet.context.utils.Pair;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Option<V> extends Pair<String, V> {
	private V defaultValue;
}
