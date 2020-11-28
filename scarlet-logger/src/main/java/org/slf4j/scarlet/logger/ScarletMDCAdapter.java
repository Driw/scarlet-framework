package org.slf4j.scarlet.logger;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
class ScarletMDCAdapter implements org.slf4j.spi.MDCAdapter {

	@Getter
	private static final ScarletMDCAdapter instance = new ScarletMDCAdapter();

	private ScarletMDCAdapter() { }

	@Override
	public void put(String key, String val) {
	}

	@Override
	public String get(String key) {
		return null;
	}

	@Override
	public void remove(String key) {
	}

	@Override
	public void clear() {
	}

	@Override
	public Map<String, String> getCopyOfContextMap() {
		return null;
	}

	@Override
	public void setContextMap(Map<String, String> contextMap) {
	}

}
