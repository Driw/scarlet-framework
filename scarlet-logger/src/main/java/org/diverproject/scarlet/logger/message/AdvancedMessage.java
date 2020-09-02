package org.diverproject.scarlet.logger.message;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;

import lombok.Data;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.MessageOutput;

@Data
public class AdvancedMessage implements MessageOutput {

	private String dateFormatted;
	private LoggerLevel level;
	private String message;
	private StackTraceElement origin;

	public boolean hasLevel() {
		return this.getLevel() != null && this.getLevel() != NONE;
	}

	public boolean hasOrigin() {
		return this.getOrigin() != null;
	}

	@Override
	public String getOutput() {
		if (this.hasOrigin()) {
			if (this.hasLevel())
				return String.format("[%s] %s | %s.%s:%d - %s",
					this.getLevel().getFormat(),
					this.getDateFormatted(),
					this.getOrigin().getClassName(),
					this.getOrigin().getMethodName(),
					this.getOrigin().getLineNumber(),
					this.getMessage()
				);

			return String.format("%s | %s.%s:%d - %s",
				this.getDateFormatted(),
				this.getOrigin().getClassName(),
				this.getOrigin().getMethodName(),
				this.getOrigin().getLineNumber(),
				this.getMessage()
			);
		}

		if (this.hasLevel())
			return String.format(
				"[%s] %s | %s",
				this.getLevel().getFormat(),
				this.getDateFormatted(),
				this.getMessage()
			);

		return String.format(
			"%s | %s",
			this.getDateFormatted(),
			this.getMessage()
		);
	}
}
