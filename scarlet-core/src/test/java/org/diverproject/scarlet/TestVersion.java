
package org.diverproject.scarlet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Version")
public class TestVersion {

	@Test
	@DisplayName("Constructor")
	public void testConstructor() {
		Version version = new Version();
		assertEquals(version.major(), 0);
		assertEquals(version.minor(), 0);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version = new Version(1);
		assertEquals(version.major(), 1);
		assertEquals(version.minor(), 0);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version = new Version(1, 2);
		assertEquals(version.major(), 1);
		assertEquals(version.minor(), 2);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version = new Version(1, 2, 3);
		assertEquals(version.major(), 1);
		assertEquals(version.minor(), 2);
		assertEquals(version.fix(), 3);
		assertEquals(version.build(), 0);

		version = new Version(1, 2, 3, 4);
		assertEquals(version.major(), 1);
		assertEquals(version.minor(), 2);
		assertEquals(version.fix(), 3);
		assertEquals(version.build(), 4);
	}

	@Test
	@DisplayName("To set")
	public void testSet() {
		int major = 1;
		int minor = 2;
		int fix = 3;
		int build = 4;

		Version version = new Version();
		assertEquals(version.major(), 0);
		assertEquals(version.minor(), 0);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version.set(major);
		assertEquals(version.major(), major);
		assertEquals(version.minor(), 0);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version.set(major, minor);
		assertEquals(version.major(), major);
		assertEquals(version.minor(), minor);
		assertEquals(version.fix(), 0);
		assertEquals(version.build(), 0);

		version.set(major, minor, fix);
		assertEquals(version.major(), major);
		assertEquals(version.minor(), minor);
		assertEquals(version.fix(), fix);
		assertEquals(version.build(), 0);

		version.set(major, minor, fix, build);
		assertEquals(version.major(), major);
		assertEquals(version.minor(), minor);
		assertEquals(version.fix(), fix);
		assertEquals(version.build(), build);
	}

	@Test
	@DisplayName("Major version")
	public void testMajorVersion() {
		Version version = new Version();

		assertEquals(version.major(), 0);

		version.major(1);
		assertEquals(version.major(), 1);

		version.major(-1);
		assertEquals(version.major(), 0);
	}

	@Test
	@DisplayName("Minor version")
	public void testMinorVersion() {
		Version version = new Version();

		assertEquals(version.minor(), 0);

		version.minor(1);
		assertEquals(version.minor(), 1);

		version.minor(-1);
		assertEquals(version.minor(), 0);
	}

	@Test
	@DisplayName("Fix version")
	public void testFixVersion() {
		Version version = new Version();

		assertEquals(version.fix(), 0);

		version.fix(1);
		assertEquals(version.fix(), 1);

		version.fix(-1);
		assertEquals(version.fix(), 0);
	}

	@Test
	@DisplayName("Build version")
	public void testBuildVersion() {
		Version version = new Version();

		assertEquals(version.build(), 0);

		version.build(1);
		assertEquals(version.build(), 1);

		version.build(-1);
		assertEquals(version.build(), 0);
	}

	@Test
	@DisplayName("Format")
	public void testFormat() {
		Version version = new Version();
		assertEquals(version.format(), "0.0");

		version.major(1);
		assertEquals(version.format(), "1.0");

		version.minor(2);
		assertEquals(version.format(), "1.2");

		version.build(4);
		assertEquals(version.format(), "1.2.0.4");

		version.fix(3);
		assertEquals(version.format(), "1.2.3.4");

		version.build(0);
		assertEquals(version.format(), "1.2.3");
	}

	@Test
	@DisplayName("To int")
	public void testToInt() {
		Version version = new Version();
		assertEquals(version.toInt(), 0x000000);

		version.major(1);
		assertEquals(version.toInt(), 0x010000);

		version.minor(2);
		assertEquals(version.toInt(), 0x010200);

		version.fix(3);
		assertEquals(version.toInt(), 0x010203);
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
}
