package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum StringUtilsLanguage implements Language {

	VAR_LOWER_CASE("invalid varname on upper case to lower case (string: %s)"),
	VAR_UPPER_CASE("invalid varname on lower case to upper case (string: %s)"),

	TRIM_STRING("trim null String"),
	TRIM_SEQUENCE_NULL("trim null String sequence"),
	TRIM_SEQUENCE_EMPTY("trim with empty String sequence"),

	INDEX_OF_STRING("index of null String"),
	INDEX_OF_SEQUENCE_NULL("index of null String sequence"),
	INDEX_OF_SEQUENCE_EMPTY("index of empty String sequence"),
	INDEX_OF_TIMES("index of invalid times"),

	PAD_TYPE("unknown pad type (padType: %d)"),
	PAD_STRING_NULL("null string"),
	PAD_PATTERN_NULL("null pad pattern"),
	PAD_PATTERN_EMPTY("empty pad pattern"),
	PAD_PATTERN_COUNT("invalid pattern count (patternCount: %d)"),

	SPLIT_LENGTH_NULL_STRING("split null string"),
	SPLIT_LENGTH_LENGTH("split invalid length (length: %d)"),

	CAP_NULL("can't cap a null String"),
	CAP_LENGTH("invalid string cap length (length: %d)"),
	CAP_MOD_LENGTH("invalid string cap mod (mod: %d)"),

	PARSE_EMPTY_NULL("null String parsed"),
	PARSE_EMPTY_EMPTY("empty string parsed"),

	IS_FLOAT_NULL("null String parsed"),

	INSERT_OUT_OF_BOUNDS("index %d out of bounds"),

	BACKSPACE_OUT_OF_BOUNDS("index %d out of bounds"),

	INDENT_LENGTH("indent length invalid (length: %d)"),

	STRING_UTILS_MAX_ERROR("undefined error")

	;

	@Getter
	@Setter
	private String format;

	StringUtilsLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
