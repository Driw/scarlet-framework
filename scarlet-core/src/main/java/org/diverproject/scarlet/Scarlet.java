package org.diverproject.scarlet;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.util.NumberUtils;

public class Scarlet {

	private static volatile Scarlet instance;

	@Getter
	@Setter
	private int floatType;

	private Scarlet() {
		floatType = NumberUtils.DECIMAL_ANY_TYPE;
	}

	public static Scarlet getInstance() {
		if (instance == null) {
			synchronized (Scarlet.class) {
				if (instance == null)
					instance = new Scarlet();
			}
		}

		return instance;
	}
}
