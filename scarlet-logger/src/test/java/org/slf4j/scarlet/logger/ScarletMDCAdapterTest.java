package org.slf4j.scarlet.logger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScarletMDCAdapterTest {

	private ScarletMDCAdapter mdcAdapter = ScarletMDCAdapter.getInstance();

	@Test
	void testPut() {
		assertDoesNotThrow(() -> this.mdcAdapter.put("any", "any"));
	}

	@Test
	void testGet() {
		assertNull(this.mdcAdapter.get("any"));
	}

	@Test
	void testRemove() {
		assertDoesNotThrow(() -> this.mdcAdapter.remove("any"));
	}

	@Test
	void testClear() {
		assertDoesNotThrow(() -> this.mdcAdapter.clear());
	}

	@Test
	void testGetCopyOfContextMap() {
		assertNull(this.mdcAdapter.getCopyOfContextMap());
	}

	@Test
	void testSetContextMap() {
		assertDoesNotThrow(() -> this.mdcAdapter.setContextMap(null));
	}

}
