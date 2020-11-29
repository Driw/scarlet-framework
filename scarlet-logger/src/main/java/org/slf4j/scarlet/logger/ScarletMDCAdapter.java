package org.slf4j.scarlet.logger;

import lombok.Data;
import lombok.Getter;
import org.slf4j.helpers.BasicMDCAdapter;

@Data
class ScarletMDCAdapter extends BasicMDCAdapter {

	@Getter
	private static final ScarletMDCAdapter instance = new ScarletMDCAdapter();

	private ScarletMDCAdapter() { }

}
