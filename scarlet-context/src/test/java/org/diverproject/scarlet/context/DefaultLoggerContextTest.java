package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.diverproject.scarlet.logger.Logger;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerSubject;
import org.junit.jupiter.api.Test;

public class DefaultLoggerContextTest {

	@Test
	public void testConstructor() throws Exception {
		Logger logger = new DefaultLoggerContext("name");
		assertNotNull(logger);

		DefaultLoggerContext defaultLoggerContext = new DefaultLoggerContext("name");
		assertNotNull(defaultLoggerContext);

		logger = new DefaultLoggerContext("name", new DefaultLoggerSubject());
		assertNotNull(logger);
	}

}
