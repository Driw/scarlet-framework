package org.slf4j.scarlet.logger;

import lombok.Data;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

@Data
public class ScarletLoggerProvider implements SLF4JServiceProvider {

	public static final String REQUESTED_API_VERSION = "1.8.99";

	private ILoggerFactory loggerFactory;
	private IMarkerFactory markerFactory;
	private MDCAdapter MDCAdapter;

	@Override
	public String getRequesteApiVersion() {
		return REQUESTED_API_VERSION;
	}

	@Override
	public void initialize() {
		this.setLoggerFactory(ScarletLoggerFactory.getInstance());
		this.setMarkerFactory(ScarletMarkerFactory.getInstance());
		this.setMDCAdapter(ScarletMDCAdapter.getInstance());
	}
}
