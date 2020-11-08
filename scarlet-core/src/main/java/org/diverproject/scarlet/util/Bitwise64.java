package org.diverproject.scarlet.util;

import lombok.Data;

@Data
public class Bitwise64 {

	private static final String[] DEFAULT_PROPERTIES = new String[] {
		"0x0000000000000001", "0x0000000000000002", "0x0000000000000004", "0x0000000000000008",
		"0x0000000000000010", "0x0000000000000020", "0x0000000000000040", "0x0000000000000080",
		"0x0000000000000100", "0x0000000000000200", "0x0000000000000400", "0x0000000000000800",
		"0x0000000000001000", "0x0000000000002000", "0x0000000000004000", "0x0000000000008000",
		"0x0000000000010000", "0x0000000000020000", "0x0000000000040000", "0x0000000000080000",
		"0x0000000000100000", "0x0000000000200000", "0x0000000000400000", "0x0000000000800000",
		"0x0000000001000000", "0x0000000002000000", "0x0000000004000000", "0x0000000008000000",
		"0x0000000010000000", "0x0000000020000000", "0x0000000040000000", "0x0000000080000000",
		"0x0000000100000000", "0x0000000200000000", "0x0000000400000000", "0x0000000800000000",
		"0x0000001000000000", "0x0000002000000000", "0x0000004000000000", "0x0000008000000000",
		"0x0000010000000000", "0x0000020000000000", "0x0000040000000000", "0x0000080000000000",
		"0x0000100000000000", "0x0000200000000000", "0x0000400000000000", "0x0000800000000000",
		"0x0001000000000000", "0x0002000000000000", "0x0004000000000000", "0x0008000000000000",
		"0x0010000000000000", "0x0020000000000000", "0x0040000000000000", "0x0080000000000000",
		"0x0100000000000000", "0x0200000000000000", "0x0400000000000000", "0x0800000000000000",
		"0x1000000000000000", "0x2000000000000000", "0x4000000000000000", "0x8000000000000000",
	};

	private long value;
	private String[] properties;

	public Bitwise64() {
		this(0, DEFAULT_PROPERTIES);
	}

	public Bitwise64(long value) {
		this(value, DEFAULT_PROPERTIES);
	}

	public Bitwise64(String... properties) {
		this(0, properties);
	}

	public Bitwise64(long value, String... properties) {
		this.setValue(value);
		this.setProperties(properties);
	}

	public void setProperties(String... properties) {
		this.properties = ScarletUtils.nvl(properties, DEFAULT_PROPERTIES);
	}

	public boolean has(long property) {
		return BitwiseUtils.has(this.value, property);
	}

	public void set(long property) {
		this.value = BitwiseUtils.set(this.value, property);
	}

	public void remove(long property) {
		this.value = BitwiseUtils.remove(this.value, property);
	}

	@Override
	public String toString() {
		return "Bitwise64{value=" +this.getValue()+ ", properties=" +BitwiseUtils.toString(this.getValue(), this.getProperties(), DEFAULT_PROPERTIES)+ "}";
	}

}
