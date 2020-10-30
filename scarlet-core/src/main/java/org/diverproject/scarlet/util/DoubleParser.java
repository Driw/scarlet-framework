package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_EXPONENT;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.DOUBLE_PARSER_PRECISION;

import lombok.Data;
import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

@Data
public class DoubleParser {

	public static final int DOUBLE_MAX_PRECISION = 15;

	private boolean isExpression;
	private boolean positive;
	private String value;
	private Integer exponent;
	private boolean exponentPositive;
	private String raw;

	public DoubleParser() {
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

	public double parseDouble() {
		return this.parseDouble(true);
	}

	public double parseDouble(boolean safe) {
		if (this.exponent != null && (this.exponent < Double.MIN_EXPONENT || this.exponent > Double.MAX_EXPONENT))
			throw new NumberUtilsRuntimeException(DOUBLE_PARSER_EXPONENT, this.exponent, Double.MIN_EXPONENT, Double.MAX_EXPONENT);

		if (safe) {
			int precision = this.getPrecision();

			if (precision > DOUBLE_MAX_PRECISION)
				throw new NumberUtilsRuntimeException(DOUBLE_PARSER_PRECISION, precision, DOUBLE_MAX_PRECISION);
		}

		return Double.parseDouble(this.raw.replace(",", "."));
	}

}
