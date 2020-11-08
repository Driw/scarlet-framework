package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_EXPONENT;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PRECISION;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.Objects;

public class FloatParser extends AbstractFloatParser {

	public static final int FLOAT_MAX_PRECISION = 6;

	public float parseFloat() {
		return this.parseFloat(true);
	}

	public float parseFloat(boolean safe) {
		if (Objects.nonNull(this.getExponent()) && (this.getExponent() < Float.MIN_EXPONENT || this.getExponent() > Float.MAX_EXPONENT))
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_EXPONENT, this.getExponent(), Float.MIN_EXPONENT, Float.MAX_EXPONENT);

		if (safe) {
			int precision = this.getPrecision();

			if (precision > FLOAT_MAX_PRECISION)
				throw new NumberUtilsRuntimeException(FLOAT_PARSER_PRECISION, precision, FLOAT_MAX_PRECISION);
		}

		return Float.parseFloat(this.getRaw().replace(",", "."));
	}

}
