package org.slf4j.scarlet.logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScarletLoggerProviderTest {

	@Test
	void initialize() {
		ScarletLoggerProvider provider = new ScarletLoggerProvider();
		assertDoesNotThrow(provider::initialize);
		assertNotNull(provider.getRequesteApiVersion());
		assertNotNull(provider.getLoggerFactory());
		assertNotNull(provider.getMarkerFactory());
		assertNotNull(provider.getMDCAdapter());
	}
}