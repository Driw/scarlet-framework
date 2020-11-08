package org.diverproject.scarlet;

import org.diverproject.scarlet.util.BinaryUtils;
import org.diverproject.scarlet.util.IntegerUtils;

import java.util.Objects;

public class Version implements Comparable<Version> {

	public static final int COMPARE_OLDER = -1;
	public static final int COMPARE_EQUALS = 0;
	public static final int COMPARE_NEWER = 1;
	public static final int MIN_VERSION_VALUE = 0;

	private int major;
	private int minor;
	private int fix;
	private int build;

	public Version() {
		this(0, 0, 0, 0);
	}

	public Version(int major) {
		this(major, 0, 0, 0);
	}

	public Version(int major, int minor) {
		this(major, minor, 0, 0);
	}

	public Version(int major, int minor, int fix) {
		this(major, minor, fix, 0);
	}

	public Version(int major, int minor, int fix, int build) {
		this.set(major, minor, fix, build);
	}

	public void set(String version) {
		String[] data = version.split("\\.");

		this.major(Integer.parseInt(data[0]));
		if (data.length > 1) this.minor(Integer.parseInt(data[1])); else this.minor(0);
		if (data.length > 2) this.fix(Integer.parseInt(data[2])); else this.fix(0);
		if (data.length > 3) this.build(Integer.parseInt(data[3])); else this.build(0);
	}

	public void set(int major) {
		this.set(major, 0, 0, 0);
	}

	public void set(int major, int minor) {
		this.set(major, minor, 0, 0);
	}

	public void set(int major, int minor, int fix) {
		this.set(major, minor, fix, 0);
	}

	public void set(int major, int minor, int fix, int build) {
		this.major(major);
		this.minor(minor);
		this.fix(fix);
		this.build(build);
	}

	public int major() {
		return this.major;
	}

	public Version major(int major) {
		this.major = IntegerUtils.capMin(major, MIN_VERSION_VALUE);
		return this;
	}

	public int minor() {
		return this.minor;
	}

	public Version minor(int minor) {
		this.minor = IntegerUtils.capMin(minor, MIN_VERSION_VALUE);
		return this;
	}

	public int fix() {
		return this.fix;
	}

	public Version fix(int fix) {
		this.fix = IntegerUtils.capMin(fix, MIN_VERSION_VALUE);
		return this;
	}

	public int build() {
		return this.build;
	}

	public Version build(int build) {
		this.build = IntegerUtils.capMin(build, MIN_VERSION_VALUE);
		return this;
	}

	public String format() {
		if (this.build() > 0)
			return String.format("%d.%d.%d.%d", this.major(), this.minor(), this.fix(), this.build());

		if (this.fix() > 0)
			return String.format("%d.%d.%d", this.major(), this.minor(), this.fix());

		return String.format("%d.%d", this.major(), this.minor());
	}

	public int toInt() {
		return BinaryUtils.newInt((byte) this.major(), (byte) this.minor(), (byte) this.fix());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj instanceof Long) return Objects.equals(this.toInt(), ((Long) obj).intValue());
		if (obj instanceof Integer) return Objects.equals(this.toInt(), obj);

		if (!(obj instanceof Version))
			return false;

		return this.hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(major, minor, fix, build);
	}

	@Override
	public int compareTo(Version version) {
		if (version == null)
			return COMPARE_NEWER;

		int self = this.toInt();
		int compare = version.toInt();

		if (self == compare)
			return Integer.compare(this.build(), version.build());

		return Integer.compare(self, compare);
	}

	@Override
	public String toString() {
		return this.format();
	}

}
