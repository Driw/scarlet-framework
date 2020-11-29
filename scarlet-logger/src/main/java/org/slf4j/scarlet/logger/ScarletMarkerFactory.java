package org.slf4j.scarlet.logger;

import lombok.Data;
import lombok.Getter;
import org.slf4j.helpers.BasicMarkerFactory;

@Data
class ScarletMarkerFactory extends BasicMarkerFactory {

	@Getter
	private static final ScarletMarkerFactory instance = new ScarletMarkerFactory();

	private ScarletMarkerFactory() { }

}
