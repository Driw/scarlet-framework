package org.diverproject.scarlet.context.options;

import static org.diverproject.scarlet.util.StringUtils.nvl;
import static org.diverproject.scarlet.util.StringUtils.varUpperCase;

import lombok.Data;
import org.diverproject.scarlet.context.utils.Pair;
import org.diverproject.scarlet.util.BooleanUtils;
import org.diverproject.scarlet.util.FloatUtils;
import org.diverproject.scarlet.util.IntegerUtils;
import org.diverproject.scarlet.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class DefaultOptionContext implements OptionContext {

	private static final String EMPTY = "";
	private static final String SEPARATOR = "=";

	private String prefix;
	private Map<String, String> rawOptions;

	public DefaultOptionContext() {
		this.setRawOptions(new TreeMap<>());
	}

	@Override
	public void initialize(String[] args) {
		List<Pair<String, String>> pairs = parseArguments(args);
		pairs.forEach(pair -> this.getRawOptions().put(pair.getFirstValue(), pair.getSecondValue()));
	}

	public List<Pair<String, String>> parseArguments(String[] args) {
		if (Objects.nonNull(this.getPrefix())) {
			args = Arrays.stream(args)
				.filter(option -> option.startsWith(prefix))
				.map(option -> option.substring(prefix.length()))
				.toArray(String[]::new);
		}

		return this.streamArguments(args);
	}

	private List<Pair<String, String>> streamArguments(String[] args) {
		return Arrays.stream(args)
			.map(this::parseArgument)
			.collect(Collectors.toList());
	}

	private Pair<String, String> parseArgument(String argument) {
		if (!argument.contains(SEPARATOR))
			return new Pair<>(argument, "true");

		String[] split = argument.split(SEPARATOR);
		String key = split[0];
		String value = this.parseValue(split[1]);

		return new Pair<>(key, value);
	}

	private String parseValue(String argument) {
		if (argument.startsWith("\"") && argument.endsWith("\""))
			return argument.substring(1, argument.length() - 1);

		return argument;
	}

	@Override
	public String getString(char option) {
		return this.getRawOptions().get(Character.toString(option));
	}

	@Override
	public String getString(String option) {
		return this.getRawOptions().get(option);
	}

	@Override
	public String getString(char option, String defaultValue) {
		return nvl(this.getRawOptions().get(Character.toString(option)), defaultValue);
	}

	@Override
	public String getString(String option, String defaultValue) {
		return nvl(this.getRawOptions().get(option), defaultValue);
	}

	@Override
	public Integer getInt(char option) {
		return IntegerUtils.parseInt(this.getString(option));
	}

	@Override
	public Integer getInt(String option) {
		return IntegerUtils.parseInt(this.getString(option));
	}

	@Override
	public int getInt(char option, int defaultValue) {
		return IntegerUtils.parseInt(this.getString(option), defaultValue);
	}

	@Override
	public int getInt(String option, int defaultValue) {
		return IntegerUtils.parseInt(this.getString(option), defaultValue);
	}

	@Override
	public Float getFloat(char option) {
		return FloatUtils.parseFloat(nvl(this.getString(option), EMPTY));
	}

	@Override
	public Float getFloat(String option) {
		return FloatUtils.parseFloat(nvl(this.getString(option), EMPTY));
	}

	@Override
	public float getFloat(char option, float defaultValue) {
		return FloatUtils.parseFloat(nvl(this.getString(option), EMPTY), defaultValue);
	}

	@Override
	public float getFloat(String option, float defaultValue) {
		return FloatUtils.parseFloat(nvl(this.getString(option), EMPTY), defaultValue);
	}

	@Override
	public Boolean hasBoolean(char option) {
		return BooleanUtils.parseBoolean(this.getString(option));
	}

	@Override
	public Boolean hasBoolean(String option) {
		try {
			return BooleanUtils.parseBoolean(this.getString(option));
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean hasBoolean(char option, boolean defaultValue) {
		return BooleanUtils.parseBoolean(this.getString(option), defaultValue);
	}

	@Override
	public boolean hasBoolean(String option, boolean defaultValue) {
		return BooleanUtils.parseBoolean(this.getString(option), defaultValue);
	}
}
