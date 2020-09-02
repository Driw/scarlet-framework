package org.diverproject.scarlet.logger.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.diverproject.scarlet.logger.MessageOutput;

@Data
@AllArgsConstructor
public class SimpleMessage implements MessageOutput {
	private String output;
}
