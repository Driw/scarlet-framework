package org.diverproject.scarlet.util;

import lombok.Data;

@Data
public class AbstractFloatParser {

	private boolean isExpression;
	private boolean positive;
	private String value;
	private Integer exponent;
	private boolean exponentPositive;
	private String raw;

	public AbstractFloatParser() {
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

}
