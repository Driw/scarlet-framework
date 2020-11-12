package org.diverproject.scarlet.util;

import lombok.Data;

@Data
public class Bitwise16 {

	private static final String[] DEFAULT_PROPERTIES = new String[] {
		"0x0001", "0x0002", "0x0004", "0x0008", "0x0010", "0x0020", "0x0040", "0x0080",
		"0x0100", "0x0200", "0x0400", "0x0800", "0x1000", "0x2000", "0x4000", "0x8000",
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

	@Override
	public String toString() {
		return "Bitwise16{value=" +this.getValue()+ ", properties=" +BitwiseUtils.toString(this.getValue(), this.getProperties(), DEFAULT_PROPERTIES)+ "}";
	}

}
