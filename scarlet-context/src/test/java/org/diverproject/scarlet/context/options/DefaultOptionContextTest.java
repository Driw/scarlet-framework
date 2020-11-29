package org.diverproject.scarlet.context.options;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.context.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultOptionContextTest {

	private DefaultOptionContext optionContext;

	@BeforeEach
	public void beforeEach() {
		this.optionContext = new DefaultOptionContext();
	}

	@Test
	public void testParseArguments() {
		List<Pair<String, String>> pairs = this.optionContext.parseArguments(new String[] { "var=textOne", "v=\"text two\"", "enable" });
		assertEquals(3, pairs.size());
		assertEquals("var", pairs.get(0).getFirstValue());
		assertEquals("textOne", pairs.get(0).getSecondValue());
		assertEquals("v", pairs.get(1).getFirstValue());
		assertEquals("text two", pairs.get(1).getSecondValue());
		assertEquals("enable", pairs.get(2).getFirstValue());
		assertEquals("true", pairs.get(2).getSecondValue());

		this.optionContext.setPrefix("prefix_");
		pairs = this.optionContext.parseArguments(new String[] { "prefix_var=textOne", "prefix_v=\"text two\"" });
		assertEquals(2, pairs.size());
		assertEquals("var", pairs.get(0).getFirstValue());
		assertEquals("textOne", pairs.get(0).getSecondValue());
		assertEquals("v", pairs.get(1).getFirstValue());
		assertEquals("text two", pairs.get(1).getSecondValue());
	}

	@Test
	public void testGetString() {
		this.optionContext.initialize(new String[] { "var=textOne", "v=\"textTwo\"" });
		assertEquals("textOne", this.optionContext.getString("var"));
		assertEquals("textOne", this.optionContext.getString("var", "defaultValue"));
		assertEquals("defaultValue", this.optionContext.getString("variable", "defaultValue"));
		assertEquals("textTwo", this.optionContext.getString('v'));
		assertEquals("textTwo", this.optionContext.getString('v', "defaultValue"));
		assertEquals("defaultValue", this.optionContext.getString('t', "defaultValue"));
	}

	@Test
	public void testGetInt() {
		this.optionContext.initialize(new String[] { "var=1", "v=\"3\"" });
		assertEquals(1, this.optionContext.getInt("var"));
		assertEquals(1, this.optionContext.getInt("var", 2));
		assertEquals(2, this.optionContext.getInt("variable", 2));
		assertEquals(3, this.optionContext.getInt('v'));
		assertEquals(3, this.optionContext.getInt('v', 2));
		assertEquals(2, this.optionContext.getInt('t', 2));
	}

	@Test
	public void testGetFloat() {
		this.optionContext.initialize(new String[] { "var=1.1", "v=\"3.1\"" });
		assertEquals(1.1F, this.optionContext.getFloat("var"));
		assertEquals(1.1F, this.optionContext.getFloat("var", 2.1F));
		assertEquals(2.1F, this.optionContext.getFloat("variable", 2.1F));
		assertEquals(3.1F, this.optionContext.getFloat('v'));
		assertEquals(3.1F, this.optionContext.getFloat('v', 2.1F));
		assertEquals(2.1F, this.optionContext.getFloat('t', 2.1F));
	}

	@Test
	public void testHasBoolean() {
		this.optionContext.initialize(new String[] { "var=true", "v=\"no\"" });
		assertTrue(this.optionContext.hasBoolean("var"));
		assertTrue(this.optionContext.hasBoolean("var", false));
		assertFalse(this.optionContext.hasBoolean("variable", false));
		assertFalse(this.optionContext.hasBoolean('v'));
		assertFalse(this.optionContext.hasBoolean('v', true));
		assertTrue(this.optionContext.hasBoolean('t', true));
		assertFalse(this.optionContext.hasBoolean("enabled"));

		this.optionContext.initialize(new String[] { "enabled" });
		assertTrue(this.optionContext.hasBoolean("enabled"));
	}

}
