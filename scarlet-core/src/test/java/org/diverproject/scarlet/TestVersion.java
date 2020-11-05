
package org.diverproject.scarlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Version")
public class TestVersion {

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		Version version = new Version();
		assertEquals(0, version.major());
		assertEquals(0, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version = new Version(1);
		assertEquals(1, version.major());
		assertEquals(0, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version = new Version(1, 2);
		assertEquals(1, version.major());
		assertEquals(2, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version = new Version(1, 2, 3);
		assertEquals(1, version.major());
		assertEquals(2, version.minor());
		assertEquals(3, version.fix());
		assertEquals(0, version.build());

		version = new Version(1, 2, 3, 4);
		assertEquals(1, version.major());
		assertEquals(2, version.minor());
		assertEquals(3, version.fix());
		assertEquals(4, version.build());
	}

	@Test
	@DisplayName("To set")
	public void testSet() {
		int major = 1;
		int minor = 2;
		int fix = 3;
		int build = 4;

		Version version = new Version();
		assertEquals(0, version.major());
		assertEquals(0, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version.set(major);
		assertEquals(major, version.major());
		assertEquals(0, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version.set(major, minor);
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version.set(major, minor, fix);
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(fix, version.fix());
		assertEquals(0, version.build());

		version.set(major, minor, fix, build);
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(fix, version.fix());
		assertEquals(build, version.build());

		version.set(String.format("%d", major));
		assertEquals(major, version.major());
		assertEquals(0, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version.set(String.format("%d.%d", major, minor));
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(0, version.fix());
		assertEquals(0, version.build());

		version.set(String.format("%d.%d.%d", major, minor, fix));
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(fix, version.fix());
		assertEquals(0, version.build());

		version.set(String.format("%d.%d.%d.%d", major, minor, fix, build));
		assertEquals(major, version.major());
		assertEquals(minor, version.minor());
		assertEquals(fix, version.fix());
		assertEquals(build, version.build());

		assertThrows(NumberFormatException.class, () -> version.set("a"));
	}

	@Test
	@DisplayName("Major version")
	public void testMajorVersion() {
		Version version = new Version();

		assertEquals(0, version.major());

		version.major(1);
		assertEquals(1, version.major());

		version.major(-1);
		assertEquals(0, version.major());
	}

	@Test
	@DisplayName("Minor version")
	public void testMinorVersion() {
		Version version = new Version();

		assertEquals(0, version.minor());

		version.minor(1);
		assertEquals(1, version.minor());

		version.minor(-1);
		assertEquals(0, version.minor());
	}

	@Test
	@DisplayName("Fix version")
	public void testFixVersion() {
		Version version = new Version();

		assertEquals(0, version.fix());

		version.fix(1);
		assertEquals(1, version.fix());

		version.fix(-1);
		assertEquals(0, version.fix());
	}

	@Test
	@DisplayName("Build version")
	public void testBuildVersion() {
		Version version = new Version();

		assertEquals(0, version.build());

		version.build(1);
		assertEquals(1, version.build());

		version.build(-1);
		assertEquals(0, version.build());
	}

	@Test
	@DisplayName("Format")
	public void testFormat() {
		Version version = new Version();
		assertEquals("0.0", version.format());

		version.major(1);
		assertEquals("1.0", version.format());

		version.minor(2);
		assertEquals("1.2", version.format());

		version.build(4);
		assertEquals("1.2.0.4", version.format());

		version.fix(3);
		assertEquals("1.2.3.4", version.format());

		version.build(0);
		assertEquals("1.2.3", version.format());
	}

	@Test
	@DisplayName("To int")
	public void testToInt() {
		Version version = new Version();
		assertEquals(0x000000, version.toInt());

		version.major(1);
		assertEquals(0x010000, version.toInt());

		version.minor(2);
		assertEquals(0x010200, version.toInt());

		version.fix(3);
		assertEquals(0x010203, version.toInt());
	}

	@Test
	@DisplayName("Equals")
	@SuppressWarnings("unlikely-arg-type")
	public void testEquals() {
		Version versionA = new Version();
		Version versionB = new Version();

		assertFalse(versionA.equals("a"));
		assertTrue(versionA.equals(versionA));
		assertTrue(versionA.equals(versionB));

		versionA.set(1, 2, 3, 4);
		versionB.set(1, 2, 3, 4);
		assertTrue(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010203));
		assertTrue(versionB.equals(0x010203));

		versionA.set(1, 2, 3, 3);
		versionB.set(1, 2, 3, 0);
		assertFalse(versionA.equals(versionB));

		versionA.set(1, 2, 3, 4);
		versionB.set(1, 2, 3, 0);
		assertFalse(versionA.equals(versionB));

		versionA.set(1, 2, 0, 4);
		versionB.set(1, 2, 3, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010200));
		assertTrue(versionB.equals(0x010203));

		versionA.set(1, 2, 3, 4);
		versionB.set(1, 2, 0, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010203));
		assertTrue(versionB.equals(0x010200));

		versionA.set(1, 0, 3, 4);
		versionB.set(1, 2, 3, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010003));
		assertTrue(versionB.equals(0x010203));

		versionA.set(1, 2, 3, 4);
		versionB.set(1, 0, 3, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010203));
		assertTrue(versionB.equals(0x010003));

		versionA.set(0, 2, 3, 4);
		versionB.set(1, 2, 3, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x000203));
		assertTrue(versionB.equals(0x010203));

		versionA.set(1, 2, 3, 4);
		versionB.set(0, 2, 3, 4);
		assertFalse(versionA.equals(versionB));
		assertTrue(versionA.equals(0x010203));
		assertTrue(versionB.equals(0x000203));
	}

	@Test
	@DisplayName("Compare to")
	public void testCompareTo() {
		Version version = new Version(2, 2, 2, 2);

		assertEquals(Version.COMPARE_NEWER, version.compareTo(null));
		assertEquals(Version.COMPARE_NEWER, version.compareTo(new Version(2, 2, 2, 1)));
		assertEquals(Version.COMPARE_EQUALS, version.compareTo(new Version(2, 2, 2, 2)));
		assertEquals(Version.COMPARE_OLDER, version.compareTo(new Version(2, 2, 2, 3)));
		assertEquals(Version.COMPARE_NEWER, version.compareTo(new Version(2, 2, 1, 2)));
		assertEquals(Version.COMPARE_EQUALS, version.compareTo(new Version(2, 2, 2, 2)));
		assertEquals(Version.COMPARE_OLDER, version.compareTo(new Version(2, 2, 3, 2)));
		assertEquals(Version.COMPARE_NEWER, version.compareTo(new Version(2, 1, 2, 2)));
		assertEquals(Version.COMPARE_EQUALS, version.compareTo(new Version(2, 2, 2, 2)));
		assertEquals(Version.COMPARE_OLDER, version.compareTo(new Version(2, 3, 2, 2)));
		assertEquals(Version.COMPARE_NEWER, version.compareTo(new Version(1, 2, 2, 2)));
		assertEquals(Version.COMPARE_EQUALS, version.compareTo(new Version(2, 2, 2, 2)));
		assertEquals(Version.COMPARE_OLDER, version.compareTo(new Version(3, 2, 2, 2)));
	}

	@Test
	@DisplayName("To string")
	public void testToString() {
		assertEquals("1.0", new Version(1).toString());
		assertEquals("1.2", new Version(1, 2).toString());
		assertEquals("1.2.3", new Version(1, 2, 3).toString());
		assertEquals("1.2.0.3", new Version(1, 2).build(3).toString());
	}

}
