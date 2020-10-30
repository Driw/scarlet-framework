package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_EXPONENT;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PRECISION;

import lombok.Data;
import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

@Data
public class FloatParser {

	public static final int FLOAT_MAX_PRECISION = 6;

	private boolean isExpression;
	private boolean positive;
	private String value;
	private Integer exponent;
	private boolean exponentPositive;
	private String raw;

	public FloatParser() {
		this.isExpression = false;
		this.positive = false;
		this.value = "0";
		this.exponent = null;
		this.exponentPositive = true;
		this.raw = "0";
	}

	public boolean isNegative() {
		return !this.isPositive();
	}

	public int getPrecision() {
		String str = this.value.replaceAll("[.,]", "");
		str = StringUtils.trim(str, "0");
		int length = str.length();

		return length + ScarletUtils.nvl(this.exponent, 0);
	}

	public float parseFloat() {
		return this.parseFloat(true);
	}

	public float parseFloat(boolean safe) {
		if (this.exponent != null && (this.exponent < Float.MIN_EXPONENT || this.exponent > Float.MAX_EXPONENT))
			throw new NumberUtilsRuntimeException(FLOAT_PARSER_EXPONENT, this.exponent, Float.MIN_EXPONENT, Float.MAX_EXPONENT);

		if (safe) {
			int precision = this.getPrecision();

			if (precision > FLOAT_MAX_PRECISION)
				throw new NumberUtilsRuntimeException(FLOAT_PARSER_PRECISION, precision, FLOAT_MAX_PRECISION);
		}

		return Float.parseFloat(this.raw.replace(",", "."));
	}

}
