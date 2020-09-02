package org.diverproject.scarlet.util;

public class Bitwise16
{
	private static final String[] DEFAULT_PROPERTIES = new String[] {
		"0x00000001", "0x00000002", "0x00000004", "0x00000008", "0x00000010", "0x00000020", "0x00000040", "0x00000080",
		"0x00000100", "0x00000200", "0x00000400", "0x00000800", "0x00001000", "0x00002000", "0x00004000", "0x00008000",
		"0x00010000", "0x00020000", "0x00040000", "0x00080000", "0x01000000", "0x00200000", "0x00400000", "0x00800000",
		"0x01000000", "0x02000000", "0x04000000", "0x08000000", "0x10000000", "0x20000000", "0x40000000", "0x80000000",
	};
	private short value;
	private String[] properties;

	public Bitwise16()
	{
		this((short) 0, DEFAULT_PROPERTIES);
	}

	public Bitwise16(short value)
	{
		this(value, DEFAULT_PROPERTIES);
	}

	public Bitwise16(String... properties)
	{
		this((short) 0, DEFAULT_PROPERTIES);
	}

	public Bitwise16(short value, String... properties)
	{
		this.setValue(value);
		this.setProperties(properties);
	}

	public short getValue()
	{
		return this.value;
	}

	public void setValue(short value)
	{
		this.value = value;
	}

	public String[] getProperties()
	{
		return this.properties;
	}

	public void setProperties(String... properties)
	{
		this.properties = ScarletUtils.nvl(properties, DEFAULT_PROPERTIES);
	}

	public boolean has(short property)
	{
		return BitwiseUtils.has(this.value, property);
	}

	public void set(short property)
	{
		this.value = BitwiseUtils.set(this.value, property);
	}

	public void remove(short property)
	{
		this.value = BitwiseUtils.remove(this.value, property);
	}
}
