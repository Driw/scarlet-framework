package org.diverproject.scarlet.util;

import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_EXPONENT;
import static org.diverproject.scarlet.util.language.NumberUtilsLanguage.FLOAT_PARSER_PRECISION;

import org.diverproject.scarlet.util.exceptions.NumberUtilsRuntimeException;

public class FloatParser
{
	public static final int FLOAT_MAX_PRECISION = 6;

	private boolean isExpression;
	private boolean positive;
	private String value;
	private Integer expoent;
	private boolean expoentPositive;
	private String raw;

	public FloatParser()
	{
		this.isExpression = false;
		this.positive = false;
		this.value = "0";
		this.expoent = null;
		this.expoentPositive = true;
		this.raw = "0";
	}

	public boolean isExpression()
	{
		return this.isExpression;
	}

	public void setExpression(boolean isExpression)
	{
		this.isExpression = isExpression;
	}

	public boolean isPositive()
	{
		return this.positive;
	}

	public void setPositive(boolean positive)
	{
		this.positive = positive;
	}

	public boolean isNegative()
	{
		return !this.isPositive();
	}

	public String getValue()
	{
		return this.value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Integer getExpoent()
	{
		return this.expoent;
	}

	public void setExpoent(Integer expoent)
	{
		this.expoent = expoent;
	}

	public boolean isExpoentPositive()
	{
		return this.expoentPositive;
	}

	public void setExpoentPositive(boolean expoentPositive)
	{
		this.expoentPositive = expoentPositive;
	}

	public String getRaw()
	{
		return this.raw;
	}

	public void setRaw(String raw)
	{
		this.raw = raw;
	}

	public int getPrecision()
	{
		String str = this.value.replaceAll("[.,]{1}", "");
		str = StringUtils.trim(str, "0");
		int length = str.length();

		return length + ScarletUtils.nvl(this.expoent, 0);
	}

	public float parseFloat()
	{
		return this.parseFloat(true);
	}

	public float parseFloat(boolean safe)
	{
		if (this.expoent != null)
			if (this.expoent < Float.MIN_EXPONENT || this.expoent > Float.MAX_EXPONENT)
				throw new NumberUtilsRuntimeException(FLOAT_PARSER_EXPONENT, this.expoent, Float.MIN_EXPONENT, Float.MAX_EXPONENT);

		if (safe)
		{
			int precision = this.getPrecision();

			if (precision > FLOAT_MAX_PRECISION)
				throw new NumberUtilsRuntimeException(FLOAT_PARSER_PRECISION, precision, FLOAT_MAX_PRECISION);
		}

		return Float.parseFloat(this.raw.replace(",", "."));
	}

	@Override
	public String toString()
	{
		return	String.format("%s[isExpression: %s, positive: %s, expoentPositive: %s, expoent: %s, value: %s, raw: %s]",
				this.getClass().getSimpleName(), this.isExpression, this.positive, this.expoentPositive, this.expoent, this.value, this.raw);
	}
}
