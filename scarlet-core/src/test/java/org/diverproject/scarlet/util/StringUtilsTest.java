package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.diverproject.scarlet.util.exceptions.StringUtilsRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("String Utils")
public class StringUtilsTest {

	@Test
	@DisplayName("Trait null values")
	public void testNvl() {
		assertEquals("", StringUtils.nvl(null));
		assertEquals("", StringUtils.nvl(""));
		assertEquals("a", StringUtils.nvl("a"));
		assertEquals("aaa", StringUtils.nvl(null, "aaa"));
		assertEquals("bbb", StringUtils.nvl("bbb", "aaa"));
		assertEquals("", StringUtils.nvl("", "aaa"));
	}

	@Test
	@DisplayName("Unaccent Strings")
	public void testUnaccent() {
		assertEquals(StringUtils.UNACCENTS, StringUtils.unaccent(StringUtils.ACCENTS));
	}

	@Test
	@DisplayName("Contains only alphabetic characters")
	public void testIsAlpha() {
		assertTrue(StringUtils.isAlpha(StringUtils.LETTERS));
		assertTrue(StringUtils.isAlpha(StringUtils.ACCENTS));

		for (char c = 0; c < 255; c++)
			if (StringUtils.LETTERS.contains(Character.toString(c)))
				assertTrue(StringUtils.isAlpha(Character.toString(c)));
			else
				assertFalse(StringUtils.isAlpha(Character.toString(c)));
	}

	@Test
	@DisplayName("Substring")
	public void testSubstring() {
		String str = "abcdefghij";

		assertEquals("cdefghij", StringUtils.substr(str, 2));
		assertEquals("ij", StringUtils.substr(str, -2));

		assertNull(StringUtils.substr(null, 0, 0));
		assertEquals("ab", StringUtils.substr(str, 0, 2));
		assertEquals("abcdefgh", StringUtils.substr(str, 0, -2));

		assertEquals("ab", StringUtils.substrAt(str, 2));
		assertEquals("abcdefgh", StringUtils.substrAt(str, -2));

		assertEquals("bcdefghij", StringUtils.substr(str, 'a'));
		assertEquals("efghij", StringUtils.substr(str, 'd'));
		assertEquals("", StringUtils.substr(str, 'j'));
		assertEquals(str, StringUtils.substr(str, 'x'));

		assertEquals("bcdefghij", StringUtils.substr(str, "a"));
		assertEquals("efghij", StringUtils.substr(str, "d"));
		assertEquals("", StringUtils.substr(str, "j"));
		assertEquals("cdefghij", StringUtils.substr(str, "ab"));
		assertEquals("fghij", StringUtils.substr(str, "de"));
		assertEquals("", StringUtils.substr(str, "ij"));
		assertEquals(str, StringUtils.substr(str, "x"));

		assertEquals("", StringUtils.substrAt(str, 'a'));
		assertEquals("abc", StringUtils.substrAt(str, 'd'));
		assertEquals("abcdefghi", StringUtils.substrAt(str, 'j'));
		assertEquals(str, StringUtils.substrAt(str, 'x'));

		assertEquals("", StringUtils.substrAt(str, "a"));
		assertEquals("abc", StringUtils.substrAt(str, "d"));
		assertEquals("abcdefghi", StringUtils.substrAt(str, "j"));
		assertEquals("", StringUtils.substrAt(str, "ab"));
		assertEquals("abc", StringUtils.substrAt(str, "de"));
		assertEquals("abcdefgh", StringUtils.substrAt(str, "ij"));
		assertEquals(str, StringUtils.substrAt(str, "x"));

		// -- Throws

		int indexOut = str.length() + 1;

		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substr(str, indexOut));
		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substr(str, -indexOut));
		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substr(str, 0, indexOut));
		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substr(str, 0, -indexOut));
		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substrAt(str, indexOut));
		assertThrows(StringIndexOutOfBoundsException.class, () -> StringUtils.substrAt(str, -indexOut));
	}

	@Test
	@DisplayName("Trim Right String sequence")
	public void testTrimRight() {
		assertEquals("", StringUtils.trimRight("a", "a"));
		assertEquals("a", StringUtils.trimRight("a", "b"));
		assertEquals("a", StringUtils.trimRight("a", "aa"));
		assertEquals("aaaa", StringUtils.trimRight("aaaa", "bb"));
		assertEquals("", StringUtils.trimRight("aaaa", "aa"));
		assertEquals("aaaaabbbbb", StringUtils.trimRight("aaaaabbbbbaaaaa", "aa"));
		assertEquals("aaaaaabbbbb", StringUtils.trimRight("aaaaaabbbbbaaaaaa", "aa"));
		assertEquals("aaaaaabbbbbaaaaaa", StringUtils.trimRight("aaaaaabbbbbaaaaaa", "c"));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimRight(null, "a"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimRight("a", null));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimRight("a", ""));
	}

	@Test
	@DisplayName("Trim Left String sequence")
	public void testTrimLeft() {
		assertEquals("", StringUtils.trimLeft("a", "a"));
		assertEquals("a", StringUtils.trimLeft("a", "b"));
		assertEquals("a", StringUtils.trimLeft("a", "aa"));
		assertEquals("aaaa", StringUtils.trimLeft("aaaa", "bb"));
		assertEquals("", StringUtils.trimLeft("aaaa", "aa"));
		assertEquals("bbbbbaaaaa", StringUtils.trimLeft("aaaaabbbbbaaaaa", "aa"));
		assertEquals("bbbbbaaaaaa", StringUtils.trimLeft("aaaaaabbbbbaaaaaa", "aa"));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimLeft(null, "a"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimLeft("a", null));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trimLeft("a", ""));
	}

	@Test
	@DisplayName("Trim String sequence")
	public void testTrim() {
		assertEquals("", StringUtils.trim("a", "a"));
		assertEquals("", StringUtils.trim("aaaa", "aa"));
		assertEquals("bbbbb", StringUtils.trim("aaaaabbbbbaaaaa", "aa"));
		assertEquals("bbbbb", StringUtils.trim("aaaaaabbbbbaaaaaa", "aa"));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trim(null, "a"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trim("a", null));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.trim("a", ""));
	}

	@Test
	@DisplayName("Add pattern")
	public void testPad() {
		// -- String length minor then pad length - need pad

		// Length Pad
		assertEquals("bba", StringUtils.leftPadLength("a", "b", 3));
		assertEquals("abb", StringUtils.rightPadLength("a", "b", 3));
		assertEquals("bba", StringUtils.leftPad("a", "b", 3, StringUtils.PAD_LENGTH));
		assertEquals("abb", StringUtils.rightPad("a", "b", 3, StringUtils.PAD_LENGTH));
		assertEquals("bba", StringUtils.pad("a", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_LEFT));
		assertEquals("abb", StringUtils.pad("a", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_RIGHT));
		// MOD Pad
		assertEquals("bbbaa", StringUtils.leftPadMod("aa", "b", 5));
		assertEquals("aabbb", StringUtils.rightPadMod("aa", "b", 5));
		assertEquals("bbbaa", StringUtils.leftPad("aa", "b", 5, StringUtils.PAD_MOD));
		assertEquals("aabbb", StringUtils.rightPad("aa", "b", 5, StringUtils.PAD_MOD));
		assertEquals("bbbaa", StringUtils.pad("aa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_LEFT));
		assertEquals("aabbb", StringUtils.pad("aa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_RIGHT));

		// -- String length major or equals (equals) then pad length - don't need pad

		// Length Pad
		assertEquals("aaa", StringUtils.leftPadLength("aaa", "b", 3));
		assertEquals("aaa", StringUtils.rightPadLength("aaa", "b", 3));
		assertEquals("aaa", StringUtils.leftPad("aaa", "b", 3, StringUtils.PAD_LENGTH));
		assertEquals("aaa", StringUtils.rightPad("aaa", "b", 3, StringUtils.PAD_LENGTH));
		assertEquals("aaa", StringUtils.pad("aaa", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_LEFT));
		assertEquals("aaa", StringUtils.pad("aaa", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_RIGHT));
		// MOD Pad
		assertEquals("aaaaa", StringUtils.leftPadMod("aaaaa", "b", 5));
		assertEquals("aaaaa", StringUtils.rightPadMod("aaaaa", "b", 5));
		assertEquals("aaaaa", StringUtils.leftPad("aaaaa", "b", 5, StringUtils.PAD_MOD));
		assertEquals("aaaaa", StringUtils.rightPad("aaaaa", "b", 5, StringUtils.PAD_MOD));
		assertEquals("aaaaa", StringUtils.pad("aaaaa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_LEFT));
		assertEquals("aaaaa", StringUtils.pad("aaaaa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_RIGHT));

		// -- Throws

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad(null, "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", null, StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH - 1, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE - 1, StringUtils.MIN_PAD_ORIENTATION));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MAX_PAD_TYPE + 1, StringUtils.MIN_PAD_ORIENTATION));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION - 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MAX_PAD_ORIENTATION + 2));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.rightPad("aaaaa", "a", 1, 3));
	}

	@Test
	@DisplayName("Split String by Length")
	public void testSplitLength() {
		assertArrayEquals(new String[] { "" }, StringUtils.splitLength("", 2));
		assertArrayEquals(new String[] { "ab", "cd", "ef", "gh", "ij" }, StringUtils.splitLength("abcdefghij", 2));
		assertArrayEquals(new String[] { "ab", "cd", "ef", "gh", "i" }, StringUtils.splitLength("abcdefghi", 2));
		assertArrayEquals(new String[] { "abcdefghij" }, StringUtils.splitLength("abcdefghij", 11));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.splitLength(null, 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.splitLength("my string", 0));
	}

	@Test
	@DisplayName("Split String")
	public void testSplitInto() {
		assertArrayEquals(new String[] { "abcde", "fghij" }, StringUtils.splitInto("abcdefghij", 2));
		assertArrayEquals(new String[] { "abcde", "fghi" }, StringUtils.splitInto("abcdefghi", 2));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.splitInto(null, 1));
	}

	@Test
	@DisplayName("Cap the String length")
	public void testCap() {
		assertEquals("a", StringUtils.cap("abcdefghij", 1));
		assertEquals("abcdefghi", StringUtils.cap("abcdefghij", 9));
		assertEquals("abcdefghij", StringUtils.cap("abcdefghij", 10));
		assertEquals("abcdefghij", StringUtils.cap("abcdefghij", 11));
		assertEquals("abcdefghij", StringUtils.capMod("abcdefghij", 2));
		assertEquals("abcdefghi", StringUtils.capMod("abcdefghij", 3));
		assertEquals("abcdefgh", StringUtils.capMod("abcdefghij", 4));
		assertEquals("abcdefghij", StringUtils.capMod("abcdefghij", 5));

		// -- Throws

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.cap(null, 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.cap("ab", 0));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.capMod(null, 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.capMod("ab", 0));
	}

	@Test
	@DisplayName("Parse empty String")
	public void testParseEmpty() {
		StringUtils.parseEmpty("A");

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.parseEmpty(""));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.parseEmpty(null));
	}

	@DisplayName("Generate random Code")
	@RepeatedTest(value = 100)
	public void testGenCode() {
		String code = StringUtils.genCode(5);
		assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(code.charAt(0))));
		assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(code.charAt(1))));
		assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(code.charAt(2))));
		assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(code.charAt(3))));
		assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(code.charAt(4))));

		String pattern = StringUtils.genCode(10);
		code = StringUtils.genCode(20, pattern);
		assertTrue(pattern.contains(Character.toString(code.charAt(0))));
		assertTrue(pattern.contains(Character.toString(code.charAt(1))));
		assertTrue(pattern.contains(Character.toString(code.charAt(2))));
		assertTrue(pattern.contains(Character.toString(code.charAt(3))));
		assertTrue(pattern.contains(Character.toString(code.charAt(4))));
		assertTrue(pattern.contains(Character.toString(code.charAt(5))));
		assertTrue(pattern.contains(Character.toString(code.charAt(6))));
		assertTrue(pattern.contains(Character.toString(code.charAt(7))));
		assertTrue(pattern.contains(Character.toString(code.charAt(8))));
		assertTrue(pattern.contains(Character.toString(code.charAt(9))));
		assertTrue(pattern.contains(Character.toString(code.charAt(10))));
		assertTrue(pattern.contains(Character.toString(code.charAt(11))));
		assertTrue(pattern.contains(Character.toString(code.charAt(12))));
		assertTrue(pattern.contains(Character.toString(code.charAt(13))));
		assertTrue(pattern.contains(Character.toString(code.charAt(14))));
		assertTrue(pattern.contains(Character.toString(code.charAt(15))));
		assertTrue(pattern.contains(Character.toString(code.charAt(16))));
		assertTrue(pattern.contains(Character.toString(code.charAt(17))));
		assertTrue(pattern.contains(Character.toString(code.charAt(18))));
		assertTrue(pattern.contains(Character.toString(code.charAt(19))));
	}

	@Test
	@DisplayName("Capitalize")
	public void testCapitalize() {
		assertNull(StringUtils.capitalize(null));
		assertEquals("", StringUtils.capitalize(""));
		assertEquals("A", StringUtils.capitalize("a"));
		assertEquals("Ab", StringUtils.capitalize("ab"));
		assertEquals("Ab", StringUtils.capitalize("Ab"));
		assertEquals("AB", StringUtils.capitalize("aB"));
		assertEquals("AB", StringUtils.capitalize("AB"));
	}

	@Test
	@DisplayName("Uncapitalize")
	public void testUncapitalize() {
		assertNull(StringUtils.uncapitalize(null));
		assertEquals("", StringUtils.uncapitalize(""));
		assertEquals("a", StringUtils.uncapitalize("A"));
		assertEquals("ab", StringUtils.uncapitalize("ab"));
		assertEquals("ab", StringUtils.uncapitalize("Ab"));
		assertEquals("aB", StringUtils.uncapitalize("aB"));
		assertEquals("aB", StringUtils.uncapitalize("AB"));
	}

	@Test
	@DisplayName("Var lower case")
	public void testVarLowerCase() {
		assertNull(StringUtils.varLowerCase(null));
		assertEquals("", StringUtils.varLowerCase(""));
		assertEquals("var", StringUtils.varLowerCase("VAR"));
		assertEquals("varName", StringUtils.varLowerCase("VAR_NAME"));
		assertEquals("varName05", StringUtils.varLowerCase("VAR_NAME05"));
		assertEquals("varName05", StringUtils.varLowerCase("VAR_NAME_05"));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varLowerCase("var"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varLowerCase("varName"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varLowerCase("var@"));
	}

	@Test
	@DisplayName("Var upper case")
	public void testVarUpperCase() {
		assertNull(StringUtils.varUpperCase(null));
		assertEquals("", StringUtils.varUpperCase(""));
		assertEquals("VAR", StringUtils.varUpperCase("var"));
		assertEquals("VAR_NAME", StringUtils.varUpperCase("varName"));
		assertEquals("VAR_NAME_05", StringUtils.varUpperCase("varName05"));
		assertEquals("VAR_NAME_05", StringUtils.varUpperCase("varName_05"));
		assertEquals("VAR_NAME_05", StringUtils.varUpperCase("varName__05"));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varUpperCase("@"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varUpperCase("var@"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varUpperCase("VAR@"));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.varUpperCase("05@"));
	}

	@Test
	@DisplayName("Insert char inside")
	public void testInsert() {
		assertEquals("xabcd", StringUtils.insert("abcd", 'x', 0));
		assertEquals("abxcd", StringUtils.insert("abcd", 'x', 2));
		assertEquals("abcdx", StringUtils.insert("abcd", 'x', 4));
		assertEquals("xabcde", StringUtils.insert("abcde", 'x', 0));
		assertEquals("abxcde", StringUtils.insert("abcde", 'x', 2));
		assertEquals("abcdex", StringUtils.insert("abcde", 'x', 5));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.insert("abcde", 'x', -1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.insert("abcde", 'x', 6));

		assertEquals("xxxabcd", StringUtils.insert("abcd", "xxx", 0));
		assertEquals("abxxxcd", StringUtils.insert("abcd", "xxx", 2));
		assertEquals("abcdxxx", StringUtils.insert("abcd", "xxx", 4));
		assertEquals("xxxabcde", StringUtils.insert("abcde", "xxx", 0));
		assertEquals("abxxxcde", StringUtils.insert("abcde", "xxx", 2));
		assertEquals("abcdexxx", StringUtils.insert("abcde", "xxx", 5));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.insert("abcde", "xxx", -1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.insert("abcde", "xxx", 6));
	}

	@Test
	@DisplayName("Backspace action on String")
	public void testBackspace() {
		assertEquals("abcd", StringUtils.backspace("abcd", 0));
		assertEquals("bcd", StringUtils.backspace("abcd", 1));
		assertEquals("acd", StringUtils.backspace("abcd", 2));
		assertEquals("abc", StringUtils.backspace("abcd", 4));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.backspace("abcd", 5));
	}

	@Test
	@DisplayName("Delete action on String")
	public void testDelete() {
		assertEquals("bcd", StringUtils.delete("abcd", 0));
		assertEquals("acd", StringUtils.delete("abcd", 1));
		assertEquals("abd", StringUtils.delete("abcd", 2));
		assertEquals("abcd", StringUtils.delete("abcd", 4));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.delete("abcd", 5));
	}

	@Test
	@DisplayName("Parse String at NULL char")
	public void testParseString() {
		assertEquals("abcde", StringUtils.parseString(new byte[] {'a', 'b', 'c', 'd', 'e'}));
		assertEquals("abcde", StringUtils.parseString(new byte[] {'a', 'b', 'c', 'd', 'e', 0x00}));
		assertEquals("abc", StringUtils.parseString(new byte[] {'a', 'b', 'c', 0x00, 'd', 'e'}));
		assertEquals("", StringUtils.parseString(new byte[] {0x00, 'a', 'b', 'c', 'd', 'e'}));
		assertEquals("", StringUtils.parseString(new byte[] {}));
	}

	@Test
	@DisplayName("Create ident String tab")
	public void testIndent() {
		assertEquals("", StringUtils.indent(0));
		assertEquals("	", StringUtils.indent(1));
		assertEquals("		", StringUtils.indent(2));
		assertEquals("			", StringUtils.indent(3));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indent(-1));
	}

	@Test
	@DisplayName("Parse parameters with arguments")
	public void testParseParameters() {
		assertEquals("param1: value1", StringUtils.parseParameters("param1", "value1"));
		assertEquals("param1: value1, param2: value2", StringUtils.parseParameters("param1", "value1", "param2", "value2"));
		assertEquals("param1: value1, param2: value2", StringUtils.parseParameters("param1", "value1", "param2", "value2", "param3"));
	}

	@Test
	@DisplayName("Parse parameters with format")
	public void testParseParametersFormat() {
		assertEquals("param1=value1", StringUtils.parseParametersFormat(";", "=", "param1", "value1"));
		assertEquals("param1=value1;param2=value2", StringUtils.parseParametersFormat(";", "=", "param1", "value1", "param2", "value2"));
		assertEquals("param1=value1;param2=value2", StringUtils.parseParametersFormat(";", "=", "param1", "value1", "param2", "value2", "param3"));
	}

	@Test
	@DisplayName("Extend String data parameters")
	public void testExtendParameters() {
		assertEquals("A random message without parameters (my parametesr)", StringUtils.extendParameters("A random message without parameters", "my parametesr"));
		assertEquals("A random message without parameters (my parametesr)", StringUtils.extendParameters("A random message without parameters ()", "my parametesr"));
		assertEquals("A random message (param1: valu1, my parametesr)", StringUtils.extendParameters("A random message (param1: valu1)", "my parametesr"));
		assertEquals("A random message (param1: valu1, param2: value2)", StringUtils.extendParameters("A random message (param1: valu1)", "param2", "value2"));
		assertEquals("A random message (param1: valu1, param2: value2)", StringUtils.extendParameters("A random message (param1: valu1)", "param2", "value2", "param3"));
	}

	@Test
	@DisplayName("Format money")
	public void testFormatMoney() {
		assertEquals("0", StringUtils.formatMoney(0L));
		assertEquals("10", StringUtils.formatMoney(10L));
		assertEquals("100", StringUtils.formatMoney(100L));
		assertEquals("1,000", StringUtils.formatMoney(1000L));
		assertEquals("10,000", StringUtils.formatMoney(10000L));
		assertEquals("100,000", StringUtils.formatMoney(100000L));
		assertEquals("1,000,000", StringUtils.formatMoney(1000000L));
		assertEquals("0", StringUtils.formatMoney(-0L));
		assertEquals("-10", StringUtils.formatMoney(-10L));
		assertEquals("-100", StringUtils.formatMoney(-100L));
		assertEquals("-1,000", StringUtils.formatMoney(-1000L));
		assertEquals("-10,000", StringUtils.formatMoney(-10000L));
		assertEquals("-100,000", StringUtils.formatMoney(-100000L));
		assertEquals("-1,000,000", StringUtils.formatMoney(-1000000L));

		assertEquals("0", StringUtils.formatMoney(".", 0L));
		assertEquals("10", StringUtils.formatMoney(".", 10L));
		assertEquals("100", StringUtils.formatMoney(".", 100L));
		assertEquals("1.000", StringUtils.formatMoney(".", 1000L));
		assertEquals("10.000", StringUtils.formatMoney(".", 10000L));
		assertEquals("100.000", StringUtils.formatMoney(".", 100000L));
		assertEquals("1.000.000", StringUtils.formatMoney(".", 1000000L));
		assertEquals("0", StringUtils.formatMoney(".", -0L));
		assertEquals("-10", StringUtils.formatMoney(".", -10L));
		assertEquals("-100", StringUtils.formatMoney(".", -100L));
		assertEquals("-1.000", StringUtils.formatMoney(".", -1000L));
		assertEquals("-10.000", StringUtils.formatMoney(".", -10000L));
		assertEquals("-100.000", StringUtils.formatMoney(".", -100000L));
		assertEquals("-1.000.000", StringUtils.formatMoney(".", -1000000L));
	}

	@Test
	@DisplayName("Count of a character")
	public void testCountOf() {
		assertEquals(0, StringUtils.countOf("Count a character", 'z'));
		assertEquals(1, StringUtils.countOf("Count a character", 'C'));
		assertEquals(2, StringUtils.countOf("Count a character", ' '));
		assertEquals(2, StringUtils.countOf("Count a character", 'c'));
		assertEquals(3, StringUtils.countOf("Count a character", 'a'));

		assertEquals(0, StringUtils.countOf("Count a character repeat Count", "  "));
		assertEquals(4, StringUtils.countOf("Count a character repeat Count", " "));
		assertEquals(2, StringUtils.countOf("Count a character repeat Count", "Count"));
		assertEquals(0, StringUtils.countOf("Count a character repeat Count", "Counter"));
	}

	@Test
	@DisplayName("Replace multiple values")
	public void testReplace() {
		assertEquals("REplacE multiplE valuEs tEst", StringUtils.replace("Replace multiple values test", "E", "e"));
		assertEquals("Replace multiple values test", StringUtils.replace("Replacex multipley valuesz test", "", "x", "y", "z"));
		assertEquals("Reace multie values test", StringUtils.replace("Replace multiple values test", "", "pl"));
	}

	@Test
	@DisplayName("Index of N time")
	public void testIndexOf() {
		assertEquals(-1, StringUtils.indexOf("Get N times of character index on sequence", 'y', 1));
		assertEquals(0, StringUtils.indexOf("Get N times of character index on sequence", 'G', 1));
		assertEquals(1, StringUtils.indexOf("Get N times of character index on sequence", 'e', 1));
		assertEquals(9, StringUtils.indexOf("Get N times of character index on sequence", 'e', 2));
		assertEquals(22, StringUtils.indexOf("Get N times of character index on sequence", 'e', 3));

		assertEquals(-1, StringUtils.indexOf("Get N times of character index on sequence", "y", 1));
		assertEquals(0, StringUtils.indexOf("Get N times of character index on sequence", "Get", 1));
		assertEquals(30, StringUtils.indexOf("Get N times of character index on sequence", " o", 2));
		assertEquals(33, StringUtils.indexOf("Get N times of character index on sequence", " ", 7));

		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf(null, ' ', 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf(null, " ", 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf("my string", null, 1));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf("my string", ' ', 0));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf("my string", " ", 0));
		assertThrows(StringUtilsRuntimeException.class, () -> StringUtils.indexOf("my string", "", 1));
	}

	@Test
	@DisplayName("First Upper Case")
	public void testFirstUpperCase() {
		assertNull(StringUtils.firstUppercase(null));
		assertEquals("", StringUtils.firstUppercase(""));
		assertEquals("A", StringUtils.firstUppercase("a"));
		assertEquals("Abcd", StringUtils.firstUppercase("abcd"));
		assertEquals("A", StringUtils.firstUppercase("A"));
		assertEquals("Abcd", StringUtils.firstUppercase("Abcd"));
		assertEquals("ABCD", StringUtils.firstUppercase("aBCD"));
	}

	@Test
	@DisplayName("Single Line")
	public void testSingleLine() {
		assertNull(StringUtils.singleLine(null));
		assertEquals("", StringUtils.singleLine(""));
		assertEquals("a a", StringUtils.singleLine("a\na"));
		assertEquals("a  a", StringUtils.singleLine("a \na"));
		assertEquals("a  a", StringUtils.singleLine("a\n a"));
		assertEquals("a   a", StringUtils.singleLine("a \r a"));
		assertEquals("a a", StringUtils.singleLine("a\ra"));
		assertEquals("a  a", StringUtils.singleLine("a \ra"));
		assertEquals("a  a", StringUtils.singleLine("a\r a"));
		assertEquals("a   a", StringUtils.singleLine("a \r a"));
		assertEquals("a   a", StringUtils.singleLine("a \r\n a"));
		assertEquals("a a", StringUtils.singleLine("a\r\na"));
		assertEquals("a  a", StringUtils.singleLine("a \r\na"));
		assertEquals("a  a", StringUtils.singleLine("a\r\n a"));
		assertEquals("a   a", StringUtils.singleLine("a \r\n a"));
		assertEquals("a   a", StringUtils.singleLine("a \r\n a"));
		assertEquals("a  a", StringUtils.singleLine("a\n\ra"));
		assertEquals("a   a", StringUtils.singleLine("a \n\ra"));
		assertEquals("a   a", StringUtils.singleLine("a\n\r a"));
		assertEquals("a    a", StringUtils.singleLine("a \n\r a"));
	}

	@Test
	@DisplayName("Object to String")
	public void testObjectToString() {
		StringClass object = new StringClass();

		assertEquals("StringClass[attributeInt=1]", StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt
			));

		assertEquals("StringClass[attributeInt=1, attributeFloat=1.1]",
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat
			));

		assertEquals("StringClass[attributeInt=1, attributeFloat=1.1, attributeBoolean=true]",
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat,
				"attributeBoolean", object.attributeBoolean
			));

		assertEquals("StringClass[attributeInt=1, attributeFloat=1.1, attributeBoolean=true, attributeString=A String]",
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat,
				"attributeBoolean", object.attributeBoolean,
				"attributeString", object.attributeString
			));
	}

	@Test
	@DisplayName("Get simple name of")
	public void testGetSimpleNameOf() {
		assertEquals(StringClass.class.getSimpleName(), StringUtils.getSimpleNameOf(StringClass.class.getName()));
		assertEquals(StringClass.class.getSimpleName(), StringUtils.getSimpleNameOf(StringClass.class.getSimpleName()));
		assertEquals(StringUtilsTest.class.getSimpleName(), StringUtils.getSimpleNameOf(StringUtilsTest.class.getName()));
		assertEquals(StringUtilsTest.class.getSimpleName(), StringUtils.getSimpleNameOf(StringUtilsTest.class.getSimpleName()));
	}

	@Test
	@DisplayName("String has min length")
	public void testHasMinLength() {
		assertTrue(StringUtils.hasMinLength("abcde", 4));
		assertTrue(StringUtils.hasMinLength("abcde", 5));
		assertFalse(StringUtils.hasMinLength("abcde", 6));
		assertFalse(StringUtils.hasMinLength(null, 6));
	}

	@Test
	@DisplayName("String has max length")
	public void testHasMaxLength() {
		assertTrue(StringUtils.hasMaxLength("abcde", 6));
		assertTrue(StringUtils.hasMaxLength("abcde", 5));
		assertFalse(StringUtils.hasMaxLength("abcde", 4));
		assertFalse(StringUtils.hasMaxLength(null, 4));
	}

	@Test
	@DisplayName("String has length between")
	public void testHasBetween() {
		assertTrue(StringUtils.hasBetween("abcde", 5, 5));
		assertTrue(StringUtils.hasBetween("abcde", 4, 5));
		assertTrue(StringUtils.hasBetween("abcde", 5, 6));

		assertFalse(StringUtils.hasBetween("abcde", 6, 5));
		assertFalse(StringUtils.hasBetween("abcde", 5, 4));
		assertFalse(StringUtils.hasBetween("abcde", 4, 4));
		assertFalse(StringUtils.hasBetween("abcde", 6, 6));
		assertFalse(StringUtils.hasBetween(null, 5, 5));
	}

	@Test
	@DisplayName("Qualified name")
	public void testQualifiedName() {
		assertEquals("org", StringUtils.qualifiedName("org"));
		assertEquals("o.diverproject", StringUtils.qualifiedName("org.diverproject"));
		assertEquals("o.d.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet"));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet"));

		assertEquals("o", StringUtils.qualifiedName("org", 2));
		assertEquals("org", StringUtils.qualifiedName("org", 3));
		assertEquals("org", StringUtils.qualifiedName("org", 4));
		assertEquals("o.d", StringUtils.qualifiedName("org.diverproject", 13));
		assertEquals("o.diverproject", StringUtils.qualifiedName("org.diverproject", 14));
		assertEquals("o.diverproject", StringUtils.qualifiedName("org.diverproject", 15));
		assertEquals("o.d.S", StringUtils.qualifiedName("org.diverproject.Scarlet", 10));
		assertEquals("o.d.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 11));
		assertEquals("o.d.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 12));
		assertEquals("o.d.u.S", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 12));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 13));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 14));

		assertEquals("org", StringUtils.qualifiedName("org", 3, false));
		assertEquals("o.diverproject", StringUtils.qualifiedName("org.diverproject", 15, false));
		assertEquals("org.diverproject", StringUtils.qualifiedName("org.diverproject", 16, false));
		assertEquals("org.diverproject", StringUtils.qualifiedName("org.diverproject", 17, false));
		assertEquals("o.d.S", StringUtils.qualifiedName("org.diverproject.Scarlet", 10, false));
		assertEquals("o.d.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 11, false));
		assertEquals("o.d.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 12, false));
		assertEquals("o.diverproject.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 23, false));
		assertEquals("org.diverproject.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 24, false));
		assertEquals("org.diverproject.Scarlet", StringUtils.qualifiedName("org.diverproject.Scarlet", 25, false));
		assertEquals("o.d.u.S", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 11, false));
		assertEquals("o.d.u.S", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 12, false));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 13, false));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 16, false));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 17, false));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 18, false));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 27, false));
		assertEquals("o.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 28, false));
		assertEquals("o.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 29, false));
		assertEquals("org.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 30, false));

		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 30, false, 1));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 30, false, 2));
		assertEquals("o.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 30, false, 3));
		assertEquals("org.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 30, false, 4));
		assertEquals("o.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 29, false, 4));
		assertEquals("o.diverproject.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 28, false, 4));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 27, false, 4));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 18, false, 4));
		assertEquals("o.d.utils.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 17, false, 4));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 16, false, 4));
		assertEquals("o.d.u.Scarlet", StringUtils.qualifiedName("org.diverproject.utils.Scarlet", 13, false, 4));
	}

	private static class StringClass {
		public int attributeInt = 1;
		public float attributeFloat = 1.1f;
		public boolean attributeBoolean = true;
		public String attributeString = "A String";
	}
}
