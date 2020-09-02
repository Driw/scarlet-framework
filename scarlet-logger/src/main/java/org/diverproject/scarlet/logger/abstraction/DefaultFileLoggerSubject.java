package org.diverproject.scarlet.logger.abstraction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.diverproject.scarlet.logger.MessageOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultFileLoggerSubject extends DefaultLoggerSubject {

	private File file;
	private FileOutputStream fileOutputStream;
	private long oldFileLength;
	private long writeBytes;

	public DefaultFileLoggerSubject(String pathname) {
		this(new File(pathname));
	}

	public DefaultFileLoggerSubject(File file) {
		try {

			this.setOldFileLength(file.length());
			this.setFileOutputStream(new FileOutputStream(file, true));
			this.setFile(file);

		} catch (IOException e) {
			throw LoggerAbstractionError.openFileChannel(e, this.getFile());
		}
	}

	@Override
	public void notify(MessageOutput message) {
		super.notify(message);

		try {

			String output = message.getOutput();
			ByteBuffer byteBuffer = ByteBuffer.allocate(output.length());
			byteBuffer.put(output.getBytes());
			byteBuffer.flip();
			{
				this.setWriteBytes(byteBuffer.position());
				this.getFileOutputStream().getChannel().write(byteBuffer);
			}

		} catch (IOException e) {
			throw LoggerAbstractionError.writeFileChannel(e, this.getFile());
		}
	}

	@Override
	public void feedLine() {
		super.feedLine();

		try {

			ByteBuffer byteBuffer = ByteBuffer.allocate(1);
			byteBuffer.put((byte) '\n');
			byteBuffer.flip();
			{
				this.setWriteBytes(byteBuffer.position());
				this.getFileOutputStream().getChannel().write(byteBuffer);
			}

		} catch (IOException e) {
			throw LoggerAbstractionError.feedLineFileChannel(e, this.getFile());
		}
	}

	@Override
	public void close() throws IOException {
		super.close();
		this.getFileOutputStream().close();
	}
}
