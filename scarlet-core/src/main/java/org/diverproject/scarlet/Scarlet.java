package org.diverproject.scarlet;

import lombok.Data;
import org.diverproject.scarlet.util.NumberUtils;

@Data
public class Scarlet {

	private static final Scarlet instance = new Scarlet();

	private int floatType;

	private Scarlet() {
		setFloatType(NumberUtils.DECIMAL_ANY_TYPE);
	}

	public static Scarlet getInstance() {
		return instance;
	}
}
