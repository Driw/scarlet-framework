package org.diverproject.scarlet.stream;

public interface InputOutput<S, O> {
	void read(Input input, S source, O object);
	void write(Output output, S source, O object);
}
