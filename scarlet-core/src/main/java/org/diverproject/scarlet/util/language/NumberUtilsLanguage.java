package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum NumberUtilsLanguage implements Language {

	HAS_INTEGER_LENGTH_MIN_MAX("min value is major then max value (min: %d, max: %d)"),

	SHORT_PARSER("failure on parse short (str: %s)"),
	INTEGER_PARSER("failure on parse integer (str: %s)"),
	LONG_PARSER("failure on parse long (str: %s)"),
	FLOAT_PARSER("failure on parse float (str: %s)"),

	FLOAT_PARSER_PARSE("invalid float parse (str: %s)"),
	FLOAT_PARSER_PATTERN("unknown float type (floatType: %s)"),
	FLOAT_PARSER_EXPONENT("invalid float exponent (exponent: %d, min: %d, max: %d)"),
	FLOAT_PARSER_PRECISION("parsing safe float invalid precision (limit: %d, precision: %d)"),

	DOUBLE_PARSER_PARSE("invalid double parse (str: %s)"),
	DOUBLE_PARSER_PATTERN("unknown double type (floatType: %s)"),
	DOUBLE_PARSER_EXPONENT("invalid double exponent (exponent: %d, min: %d, max: %d)"),
	DOUBLE_PARSER_PRECISION("parsing safe double invalid precision (limit: %d, precision: %d)");

	@Getter
	@Setter
	private String format;

	NumberUtilsLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
