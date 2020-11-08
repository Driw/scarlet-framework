package org.diverproject.scarlet.context;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.logger.abstraction.DefaultFileLoggerSubject;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerSubject;
import org.junit.jupiter.api.Test;

import java.io.File;

public class DefaultLoggerContextTest {

	@Test
	public void testConstructor() throws Exception {
		Logger logger = new DefaultLoggerContext("name");
		assertNotNull(logger);

		DefaultLoggerContext defaultLoggerContext = new DefaultLoggerContext("name", "pathname");
		assertNotNull(defaultLoggerContext);

		File file = ((DefaultFileLoggerSubject) defaultLoggerContext.getLoggerOutputSubject()).getFile();
		assertTrue(file.exists());
		defaultLoggerContext.close();
		assertTrue(file.delete());

		logger = new DefaultLoggerContext("name", new DefaultLoggerSubject());
		assertNotNull(logger);
	}

}
