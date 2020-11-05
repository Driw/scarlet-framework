package org.diverproject.scarlet.util;

import lombok.Data;

@Data
public class Bitwise16 {

	public static final String[] DEFAULT_PROPERTIES = new String[] {
		"0x00000001", "0x00000002", "0x00000004", "0x00000008", "0x00000010", "0x00000020", "0x00000040", "0x00000080",
		"0x00000100", "0x00000200", "0x00000400", "0x00000800", "0x00001000", "0x00002000", "0x00004000", "0x00008000",
	};

	private short value;
	private String[] properties;

	public Bitwise16() {
		this((short) 0, DEFAULT_PROPERTIES);
	}

	public Bitwise16(short value) {
		this(value, DEFAULT_PROPERTIES);
	}

	public Bitwise16(String... properties) {
		this((short) 0, properties);
	}

	public Bitwise16(short value, String... properties) {
		this.setValue(value);
		this.setProperties(properties);
	}

	public void setProperties(String... properties) {
		this.properties = ScarletUtils.nvl(properties, DEFAULT_PROPERTIES);
	}

	public boolean has(short property) {
		return BitwiseUtils.has(this.value, property);
	}

	public void set(short property) {
		this.value = BitwiseUtils.set(this.value, property);
	}

	public void remove(short property) {
		this.value = BitwiseUtils.remove(this.value, property);
	}
}
