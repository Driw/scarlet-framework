package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_EXPONENT;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PRECISION;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

import java.util.Objects;

public class DoubleParser extends AbstractFloatParser {

	public static final int DOUBLE_MAX_PRECISION = 15;

	public double parseDouble() {
		return this.parseDouble(true);
	}

	public double parseDouble(boolean safe) {
		if (Objects.nonNull(this.getExponent()) && (this.getExponent() < Double.MIN_EXPONENT || this.getExponent() > Double.MAX_EXPONENT))
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_EXPONENT, this.getExponent(), Double.MIN_EXPONENT, Double.MAX_EXPONENT);

		if (safe) {
			int precision = this.getPrecision();

			if (precision > DOUBLE_MAX_PRECISION)
				throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PRECISION, precision, DOUBLE_MAX_PRECISION);
		}

		return Double.parseDouble(this.getRaw().replace(",", "."));
	}

}
