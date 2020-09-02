package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum HexadecimalUtilsLanguage implements Language
{
	TO_HEX_NEGATIVE_BYTE("cannot parse negative byte on hex (byte: %d)"),
	TO_HEX_NEGATIVE_SHORT("cannot parse negative short on hex (short: %d)"),
	TO_HEX_NEGATIVE_INT("cannot parse negative int on hex (int: %d)"),
	TO_HEX_NEGATIVE_LONG("cannot parse negative long on hex (long: %d)"),

	PARSE_HEX_BYTE("invalid hexadecimal byte (hex: %s)"),
	PARSE_HEX_SHORT("invalid hexadecimal short (hex: %s)"),
	PARSE_HEX_INT("invalid hexadecimal int (hex: %s)"),
	PARSE_HEX_LONG("invalid hexadecimal long (hex: %s)"),
	PARSE_HEX_BYTE_FORMAT("invalid hexadecimal byte format (hex: %s)"),
	PARSE_HEX_SHORT_FORMAT("invalid hexadecimal short format (hex: %s)"),
	PARSE_HEX_INT_FORMAT("invalid hexadecimal int format (hex: %s)"),
	PARSE_HEX_LONG_FORMAT("invalid hexadecimal long format (hex: %s)"),

	UNKNOWN("unknown hexadecimal utils error")

	;

	@Getter
	@Setter
	private String format;

	HexadecimalUtilsLanguage(String format)
	{
		this.setFormat(format);
	}

	@Override
	public int getCode()
	{
		return this.ordinal() + 1;
	}
}
