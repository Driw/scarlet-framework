package org.diverproject.scarlet.util.language;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum BinaryUtilsLanguage implements Language {

	SHIFT_OFFSET("data bytes need be major then offset (offset: %d, dataBytes: %d)"),

	NEW_SHORT_NULL("cannot make new short from none bytes"),
	NEW_SHORT_SIZE("a short number need 1-%d bytes (receive: %d)"),

	NEW_INT_NULL("cannot make new int from none bytes"),
	NEW_INT_SIZE("a int number need 1-%d bytes (receive: %d)"),

	NEW_LONG_NULL("cannot make new long from none bytes"),
	NEW_LONG_SIZE("a long number need 1-%d bytes (receive: %d)"),

	UNKNOWN("unknown number utils error")

	;

	@Getter
	@Setter
	private String format;

	BinaryUtilsLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}
}
