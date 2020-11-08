package org.diverproject.scarlet.util;

import lombok.Data;

@Data
public class Bitwise8 {

	private static final String[] DEFAULT_PROPERTIES = new String[] {
		"0x00000001", "0x00000002", "0x00000004", "0x00000008", "0x00000010", "0x00000020", "0x00000040", "0x00000080",
	};

	private byte value;
	private String[] properties;

	public Bitwise8() {
		this((byte) 0, DEFAULT_PROPERTIES);
	}

	public Bitwise8(byte value) {
		this(value, DEFAULT_PROPERTIES);
	}

	public Bitwise8(String... properties) {
		this((byte) 0, properties);
	}

	public Bitwise8(byte value, String... properties) {
		this.setValue(value);
		this.setProperties(properties);
	}

	public void setProperties(String... properties) {
		this.properties = ScarletUtils.nvl(properties, DEFAULT_PROPERTIES);
	}

	public boolean has(byte property) {
		return BitwiseUtils.has(this.value, property);
	}

	public void set(byte property) {
		this.value = BitwiseUtils.set(this.value, property);
	}

	public void remove(byte property) {
		this.value = BitwiseUtils.remove(this.value, property);
	}

	@Override
	public String toString() {
		return "Bitwise8{value=" +this.getValue()+ ", properties=" +BitwiseUtils.toString(this.getValue(), this.getProperties(), DEFAULT_PROPERTIES)+ "}";
	}

}
