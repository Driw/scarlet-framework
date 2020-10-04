package org.diverproject.scarlet.context.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<A, B> {
	private A firstValue;
	private B secondValue;
}
