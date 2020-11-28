package org.slf4j.scarlet.logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ScarletMarkerFactoryTest {

	private ScarletMarkerFactory markerFactory = ScarletMarkerFactory.getInstance();

	@Test
	void testGetMarker() {
		assertNull(this.markerFactory.getMarker("any"));
	}

	@Test
	void testExists() {
		assertFalse(this.markerFactory.exists("any"));
	}

	@Test
	void testDetachMarker() {
		assertFalse(this.markerFactory.detachMarker("any"));
	}

	@Test
	void testGetDetachedMarker() {
		assertNull(this.markerFactory.getDetachedMarker("any"));
	}

}
