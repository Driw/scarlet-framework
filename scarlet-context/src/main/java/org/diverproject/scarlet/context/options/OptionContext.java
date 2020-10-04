package org.diverproject.scarlet.context.options;

public interface OptionContext {
	void initialize(String[] args);
	String getString(char option);
	String getString(String option);
	String getString(char option, String defaultValue);
	String getString(String option, String defaultValue);
	Integer getInt(char option);
	Integer getInt(String option);
	int getInt(char option, int defaultValue);
	int getInt(String option, int defaultValue);
	Float getFloat(char option);
	Float getFloat(String option);
	float getFloat(char option, float defaultValue);
	float getFloat(String option, float defaultValue);
	Boolean hasBoolean(char option);
	Boolean hasBoolean(String option);
	boolean hasBoolean(char option, boolean defaultValue);
	boolean hasBoolean(String option, boolean defaultValue);
}
