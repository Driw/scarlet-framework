package org.slf4j.scarlet.logger;

import lombok.Data;
import lombok.Getter;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

@Data
class ScarletMarkerFactory implements IMarkerFactory {

	@Getter
	private static final ScarletMarkerFactory instance = new ScarletMarkerFactory();

	@Override
	public Marker getMarker(String name) {
		return null;
	}

	@Override
	public boolean exists(String name) {
		return false;
	}

	@Override
	public boolean detachMarker(String name) {
		return false;
	}

	@Override
	public Marker getDetachedMarker(String name) {
		return null;
	}

}
