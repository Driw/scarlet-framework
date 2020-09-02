package org.diverproject.scarlet.language;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class LanguageMapper {

	private String classpath;
	private Map<String, String> messages;

	public LanguageMapper() {
		this.messages = new TreeMap<>();
	}
}
