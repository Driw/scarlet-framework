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
public class TestStringUtils {

	@Test
	@DisplayName("Trait null values")
	public void testNvl() {
		assertEquals(StringUtils.nvl(null), "");
		assertEquals(StringUtils.nvl(""), "");
		assertEquals(StringUtils.nvl("a"), "a");
		assertEquals(StringUtils.nvl(null, "aaa"), "aaa");
		assertEquals(StringUtils.nvl("bbb", "aaa"), "bbb");
		assertEquals(StringUtils.nvl("", "aaa"), "");
	}

	@Test
	@DisplayName("Unaccent Strings")
	public void testUnaccent() {
		assertEquals(StringUtils.unaccent(StringUtils.ACCENTS), StringUtils.UNACCENTS);
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

		assertEquals(StringUtils.substr(str, 2), "cdefghij");
		assertEquals(StringUtils.substr(str, -2), "ij");
		assertEquals(StringUtils.substr(str, 0, 2), "ab");
		assertEquals(StringUtils.substr(str, 0, -2), "abcdefgh");
		assertEquals(StringUtils.substrAt(str, 2), "ab");
		assertEquals(StringUtils.substrAt(str, -2), "abcdefgh");

		assertEquals(StringUtils.substr(str, 'a'), "bcdefghij");
		assertEquals(StringUtils.substr(str, 'd'), "efghij");
		assertEquals(StringUtils.substr(str, 'j'), "");
		assertEquals(StringUtils.substr(str, 'x'), str);
		assertEquals(StringUtils.substr(str, "a"), "bcdefghij");
		assertEquals(StringUtils.substr(str, "d"), "efghij");
		assertEquals(StringUtils.substr(str, "j"), "");
		assertEquals(StringUtils.substr(str, "ab"), "cdefghij");
		assertEquals(StringUtils.substr(str, "de"), "fghij");
		assertEquals(StringUtils.substr(str, "ij"), "");
		assertEquals(StringUtils.substr(str, "x"), str);

		assertEquals(StringUtils.substrAt(str, 'a'), "");
		assertEquals(StringUtils.substrAt(str, 'd'), "abc");
		assertEquals(StringUtils.substrAt(str, 'j'), "abcdefghi");
		assertEquals(StringUtils.substrAt(str, 'x'), str);
		assertEquals(StringUtils.substrAt(str, "a"), "");
		assertEquals(StringUtils.substrAt(str, "d"), "abc");
		assertEquals(StringUtils.substrAt(str, "j"), "abcdefghi");
		assertEquals(StringUtils.substrAt(str, "ab"), "");
		assertEquals(StringUtils.substrAt(str, "de"), "abc");
		assertEquals(StringUtils.substrAt(str, "ij"), "abcdefgh");
		assertEquals(StringUtils.substrAt(str, "x"), str);

		// -- Throws

		int indexOut = str.length() + 1;

		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substr(str, indexOut);
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substr(str, -indexOut);
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substr(str, 0, indexOut);
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substr(str, 0, -indexOut);
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substrAt(str, indexOut);
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			StringUtils.substrAt(str, -indexOut);
		});
	}

	@Test
	@DisplayName("Trim String sequence")
	public void testTrim() {
		assertEquals(StringUtils.trim("a", "a"), "");
		assertEquals(StringUtils.trim("aaaa", "aa"), "");
		assertEquals(StringUtils.trim("aaaaabbbbbaaaaa", "aa"), "abbbbba");
		assertEquals(StringUtils.trim("aaaaaabbbbbaaaaaa", "aa"), "bbbbb");
		assertEquals(StringUtils.trimLeft("a", "a"), "");
		assertEquals(StringUtils.trimLeft("aaaa", "bb"), "aaaa");
		assertEquals(StringUtils.trimLeft("aaaa", "aa"), "");
		assertEquals(StringUtils.trimLeft("aaaaabbbbbaaaaa", "aa"), "abbbbbaaaaa");
		assertEquals(StringUtils.trimLeft("aaaaaabbbbbaaaaaa", "aa"), "bbbbbaaaaaa");
		assertEquals(StringUtils.trimRight("a", "a"), "");
		assertEquals(StringUtils.trimRight("aaaa", "bb"), "aaaa");
		assertEquals(StringUtils.trimRight("aaaa", "aa"), "");
		assertEquals(StringUtils.trimRight("aaaaabbbbbaaaaa", "aa"), "aaaaabbbbba");
		assertEquals(StringUtils.trimRight("aaaaaabbbbbaaaaaa", "aa"), "aaaaaabbbbb");
		assertEquals(StringUtils.trimRight("aaaaaabbbbbaaaaaa", "c"), "aaaaaabbbbbaaaaaa");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.trimRight(null, "a");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.trimRight("a", null);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.trimRight("a", "");
		});
	}

	@Test
	@DisplayName("Add pattern")
	public void testPad() {
		// -- String length minor then pad length - need pad

		// Length Pad
		assertEquals(StringUtils.leftPadLength("a", "b", 3), "bba");
		assertEquals(StringUtils.rightPadLength("a", "b", 3), "abb");
		assertEquals(StringUtils.leftPad("a", "b", 3, StringUtils.PAD_LENGTH), "bba");
		assertEquals(StringUtils.rightPad("a", "b", 3, StringUtils.PAD_LENGTH), "abb");
		assertEquals(StringUtils.pad("a", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_LEFT), "bba");
		assertEquals(StringUtils.pad("a", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_RIGHT), "abb");
		// MOD Pad
		assertEquals(StringUtils.leftPadMod("aa", "b", 5), "bbbaa");
		assertEquals(StringUtils.rightPadMod("aa", "b", 5), "aabbb");
		assertEquals(StringUtils.leftPad("aa", "b", 5, StringUtils.PAD_MOD), "bbbaa");
		assertEquals(StringUtils.rightPad("aa", "b", 5, StringUtils.PAD_MOD), "aabbb");
		assertEquals(StringUtils.pad("aa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_LEFT), "bbbaa");
		assertEquals(StringUtils.pad("aa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_RIGHT), "aabbb");

		// -- String length major or equals (equals) then pad length - don't need pad

		// Length Pad
		assertEquals(StringUtils.leftPadLength("aaa", "b", 3), "aaa");
		assertEquals(StringUtils.rightPadLength("aaa", "b", 3), "aaa");
		assertEquals(StringUtils.leftPad("aaa", "b", 3, StringUtils.PAD_LENGTH), "aaa");
		assertEquals(StringUtils.rightPad("aaa", "b", 3, StringUtils.PAD_LENGTH), "aaa");
		assertEquals(StringUtils.pad("aaa", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_LEFT), "aaa");
		assertEquals(StringUtils.pad("aaa", "b", 3, StringUtils.PAD_LENGTH, StringUtils.PAD_RIGHT), "aaa");
		// MOD Pad
		assertEquals(StringUtils.leftPadMod("aaaaa", "b", 5), "aaaaa");
		assertEquals(StringUtils.rightPadMod("aaaaa", "b", 5), "aaaaa");
		assertEquals(StringUtils.leftPad("aaaaa", "b", 5, StringUtils.PAD_MOD), "aaaaa");
		assertEquals(StringUtils.rightPad("aaaaa", "b", 5, StringUtils.PAD_MOD), "aaaaa");
		assertEquals(StringUtils.pad("aaaaa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_LEFT), "aaaaa");
		assertEquals(StringUtils.pad("aaaaa", "b", 5, StringUtils.PAD_MOD, StringUtils.PAD_RIGHT), "aaaaa");

		// -- Throws

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad(null, "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", null, StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH - 1, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE - 1, StringUtils.MIN_PAD_ORIENTATION);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MAX_PAD_TYPE + 1, StringUtils.MIN_PAD_ORIENTATION);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MIN_PAD_ORIENTATION - 1);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.pad("a", "a", StringUtils.MIN_PAD_PATTERN_LENGTH, StringUtils.MIN_PAD_TYPE, StringUtils.MAX_PAD_ORIENTATION + 2);
		});
	}

	@Test
	@DisplayName("Split String")
	public void testSplit() {
		assertArrayEquals(StringUtils.splitLength("abcdefghij", 2), new String[]{"ab", "cd", "ef", "gh", "ij"});
		assertArrayEquals(StringUtils.splitLength("abcdefghi", 2), new String[]{"ab", "cd", "ef", "gh", "i"});
		assertArrayEquals(StringUtils.splitInto("abcdefghij", 2), new String[]{"abcde", "fghij"});
		assertArrayEquals(StringUtils.splitInto("abcdefghi", 2), new String[]{"abcde", "fghi"});
	}

	@Test
	@DisplayName("Cap the String length")
	public void testCap() {
		assertEquals(StringUtils.cap("abcdefghij", 1), "a");
		assertEquals(StringUtils.cap("abcdefghij", 9), "abcdefghi");
		assertEquals(StringUtils.cap("abcdefghij", 10), "abcdefghij");
		assertEquals(StringUtils.cap("abcdefghij", 11), "abcdefghij");
		assertEquals(StringUtils.capMod("abcdefghij", 2), "abcdefghij");
		assertEquals(StringUtils.capMod("abcdefghij", 3), "abcdefghi");
		assertEquals(StringUtils.capMod("abcdefghij", 4), "abcdefgh");
		assertEquals(StringUtils.capMod("abcdefghij", 5), "abcdefghij");

		// -- Throws

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.cap(null, 1);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.cap("ab", 0);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.capMod(null, 1);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.capMod("ab", 0);
		});
	}

	@Test
	@DisplayName("Parse empty String")
	public void testParseEmpty() {
		StringUtils.parseEmpty("A");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.parseEmpty("");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.parseEmpty(null);
		});
	}

	@Test
	@DisplayName("Generate random Code")
	@RepeatedTest(value = 100)
	public void testGenCode() {
		for (char c : StringUtils.genCode(5).toCharArray())
			assertTrue(StringUtils.LETTERS_NUMBERS.contains(Character.toString(c)));

		String pattern = StringUtils.genCode(10);

		for (char c : StringUtils.genCode(20, pattern).toCharArray())
			assertTrue(pattern.contains(Character.toString(c)));
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

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varLowerCase("var");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varLowerCase("varName");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varLowerCase("var@");
		});
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

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varUpperCase("@");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varUpperCase("var@");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varUpperCase("VAR@");
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.varUpperCase("05@");
		});
	}

	@Test
	@DisplayName("Insert char inside")
	public void testInsert() {
		assertEquals(StringUtils.insert("abcd", 'x', 0), "xabcd");
		assertEquals(StringUtils.insert("abcd", 'x', 2), "abxcd");
		assertEquals(StringUtils.insert("abcd", 'x', 4), "abcdx");
		assertEquals(StringUtils.insert("abcde", 'x', 0), "xabcde");
		assertEquals(StringUtils.insert("abcde", 'x', 2), "abxcde");
		assertEquals(StringUtils.insert("abcde", 'x', 5), "abcdex");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.insert("abcde", 'x', -1);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.insert("abcde", 'x', 6);
		});

		assertEquals(StringUtils.insert("abcd", "xxx", 0), "xxxabcd");
		assertEquals(StringUtils.insert("abcd", "xxx", 2), "abxxxcd");
		assertEquals(StringUtils.insert("abcd", "xxx", 4), "abcdxxx");
		assertEquals(StringUtils.insert("abcde", "xxx", 0), "xxxabcde");
		assertEquals(StringUtils.insert("abcde", "xxx", 2), "abxxxcde");
		assertEquals(StringUtils.insert("abcde", "xxx", 5), "abcdexxx");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.insert("abcde", "xxx", -1);
		});
		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.insert("abcde", "xxx", 6);
		});
	}

	@Test
	@DisplayName("Backspace action on String")
	public void testBackspace() {
		assertEquals(StringUtils.backspace("abcd", 0), "abcd");
		assertEquals(StringUtils.backspace("abcd", 1), "bcd");
		assertEquals(StringUtils.backspace("abcd", 2), "acd");
		assertEquals(StringUtils.backspace("abcd", 4), "abc");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.backspace("abcd", 5);
		});
	}

	@Test
	@DisplayName("Delete action on String")
	public void testDelete() {
		assertEquals(StringUtils.delete("abcd", 0), "bcd");
		assertEquals(StringUtils.delete("abcd", 1), "acd");
		assertEquals(StringUtils.delete("abcd", 2), "abd");
		assertEquals(StringUtils.delete("abcd", 4), "abcd");

		assertThrows(StringUtilsRuntimeException.class, () -> {
			StringUtils.delete("abcd", 5);
		});
	}

	@Test
	@DisplayName("Parse String at NULL char")
	public void testParseString() {
		assertEquals(StringUtils.parseString(new byte[]{'a', 'b', 'c', 'd', 'e'}), "abcde");
		assertEquals(StringUtils.parseString(new byte[]{'a', 'b', 'c', 'd', 'e', 0x00}), "abcde");
		assertEquals(StringUtils.parseString(new byte[]{'a', 'b', 'c', 0x00, 'd', 'e'}), "abc");
		assertEquals(StringUtils.parseString(new byte[]{0x00, 'a', 'b', 'c', 'd', 'e'}), "");
		assertEquals(StringUtils.parseString(new byte[]{}), "");
	}

	@Test
	@DisplayName("Create ident String tab")
	public void testIdent() {
		assertEquals(StringUtils.indent(0), "");
		assertEquals(StringUtils.indent(1), "	");
		assertEquals(StringUtils.indent(2), "		");
		assertEquals(StringUtils.indent(3), "			");
	}

	@Test
	@DisplayName("Parse parameters with arguments")
	public void testParseParameters() {
		assertEquals(StringUtils.parseParameters("param1", "value1"), "param1: value1");
		assertEquals(StringUtils.parseParameters("param1", "value1", "param2", "value2"), "param1: value1, param2: value2");
		assertEquals(StringUtils.parseParameters("param1", "value1", "param2", "value2", "param3"), "param1: value1, param2: value2");
	}

	@Test
	@DisplayName("Parse parameters with format")
	public void testParseParametersFormat() {
		assertEquals(StringUtils.parseParametersFormat(";", "=", "param1", "value1"), "param1=value1");
		assertEquals(StringUtils.parseParametersFormat(";", "=", "param1", "value1", "param2", "value2"), "param1=value1;param2=value2");
		assertEquals(StringUtils.parseParametersFormat(";", "=", "param1", "value1", "param2", "value2", "param3"), "param1=value1;param2=value2");
	}

	@Test
	@DisplayName("Extend String data parameters")
	public void testExtendParameters() {
		assertEquals(StringUtils.extendParameters("A random message without parameters", "my parametesr"), "A random message without parameters (my parametesr)");
		assertEquals(StringUtils.extendParameters("A random message without parameters ()", "my parametesr"), "A random message without parameters (my parametesr)");
		assertEquals(StringUtils.extendParameters("A random message (param1: valu1)", "my parametesr"), "A random message (param1: valu1, my parametesr)");
		assertEquals(StringUtils.extendParameters("A random message (param1: valu1)", "param2", "value2"), "A random message (param1: valu1, param2: value2)");
		assertEquals(StringUtils.extendParameters("A random message (param1: valu1)", "param2", "value2", "param3"), "A random message (param1: valu1, param2: value2)");
	}

	@Test
	@DisplayName("Format money")
	public void testFormatMoney() {
		assertEquals(StringUtils.formatMoney(0L), "0");
		assertEquals(StringUtils.formatMoney(10L), "10");
		assertEquals(StringUtils.formatMoney(100L), "100");
		assertEquals(StringUtils.formatMoney(1000L), "1,000");
		assertEquals(StringUtils.formatMoney(10000L), "10,000");
		assertEquals(StringUtils.formatMoney(100000L), "100,000");
		assertEquals(StringUtils.formatMoney(1000000L), "1,000,000");
		assertEquals(StringUtils.formatMoney(-0L), "0");
		assertEquals(StringUtils.formatMoney(-10L), "-10");
		assertEquals(StringUtils.formatMoney(-100L), "-100");
		assertEquals(StringUtils.formatMoney(-1000L), "-1,000");
		assertEquals(StringUtils.formatMoney(-10000L), "-10,000");
		assertEquals(StringUtils.formatMoney(-100000L), "-100,000");
		assertEquals(StringUtils.formatMoney(-1000000L), "-1,000,000");

		assertEquals(StringUtils.formatMoney(".", 0L), "0");
		assertEquals(StringUtils.formatMoney(".", 10L), "10");
		assertEquals(StringUtils.formatMoney(".", 100L), "100");
		assertEquals(StringUtils.formatMoney(".", 1000L), "1.000");
		assertEquals(StringUtils.formatMoney(".", 10000L), "10.000");
		assertEquals(StringUtils.formatMoney(".", 100000L), "100.000");
		assertEquals(StringUtils.formatMoney(".", 1000000L), "1.000.000");
		assertEquals(StringUtils.formatMoney(".", -0L), "0");
		assertEquals(StringUtils.formatMoney(".", -10L), "-10");
		assertEquals(StringUtils.formatMoney(".", -100L), "-100");
		assertEquals(StringUtils.formatMoney(".", -1000L), "-1.000");
		assertEquals(StringUtils.formatMoney(".", -10000L), "-10.000");
		assertEquals(StringUtils.formatMoney(".", -100000L), "-100.000");
		assertEquals(StringUtils.formatMoney(".", -1000000L), "-1.000.000");
	}

	@Test
	@DisplayName("Count of a character")
	public void testCountOf() {
		assertEquals(StringUtils.countOf("Count a character", 'z'), 0);
		assertEquals(StringUtils.countOf("Count a character", 'C'), 1);
		assertEquals(StringUtils.countOf("Count a character", ' '), 2);
		assertEquals(StringUtils.countOf("Count a character", 'c'), 2);
		assertEquals(StringUtils.countOf("Count a character", 'a'), 3);

		assertEquals(StringUtils.countOf("Count a character repeat Count", "  "), 0);
		assertEquals(StringUtils.countOf("Count a character repeat Count", " "), 4);
		assertEquals(StringUtils.countOf("Count a character repeat Count", "Count"), 2);
	}

	@Test
	@DisplayName("Replace multiple values")
	public void testReplace() {
		assertEquals(StringUtils.replace("Replace multiple values test", "E", "e"), "REplacE multiplE valuEs tEst");
		assertEquals(StringUtils.replace("Replacex multipley valuesz test", "", "x", "y", "z"), "Replace multiple values test");
		assertEquals(StringUtils.replace("Replace multiple values test", "", "pl"), "Reace multie values test");
	}

	@Test
	@DisplayName("Index of N time")
	public void testIndexOf() {
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", 'y', 1), -1);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", 'G', 1), 0);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", 'e', 1), 1);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", 'e', 2), 9);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", 'e', 3), 22);

		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", "y", 1), -1);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", "Get", 1), 0);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", " o", 2), 30);
		assertEquals(StringUtils.indexOf("Get N times of character index on sequence", " ", 7), 33);
	}

	@Test
	@DisplayName("First Upper Case")
	public void testFirstUpperCase() {
		assertEquals(StringUtils.firstUppercase(null), null);
		assertEquals(StringUtils.firstUppercase(""), "");
		assertEquals(StringUtils.firstUppercase("a"), "A");
		assertEquals(StringUtils.firstUppercase("abcd"), "Abcd");
		assertEquals(StringUtils.firstUppercase("A"), "A");
		assertEquals(StringUtils.firstUppercase("Abcd"), "Abcd");
		assertEquals(StringUtils.firstUppercase("aBCD"), "ABCD");
	}

	@Test
	@DisplayName("Single Line")
	public void testSingleLine() {
		assertEquals(StringUtils.singleLine(null), null);
		assertEquals(StringUtils.singleLine(""), "");
		assertEquals(StringUtils.singleLine("a\na"), "a a");
		assertEquals(StringUtils.singleLine("a \na"), "a  a");
		assertEquals(StringUtils.singleLine("a\n a"), "a  a");
		assertEquals(StringUtils.singleLine("a \r a"), "a   a");
		assertEquals(StringUtils.singleLine("a\ra"), "a a");
		assertEquals(StringUtils.singleLine("a \ra"), "a  a");
		assertEquals(StringUtils.singleLine("a\r a"), "a  a");
		assertEquals(StringUtils.singleLine("a \r a"), "a   a");
		assertEquals(StringUtils.singleLine("a \r\n a"), "a   a");
		assertEquals(StringUtils.singleLine("a\r\na"), "a a");
		assertEquals(StringUtils.singleLine("a \r\na"), "a  a");
		assertEquals(StringUtils.singleLine("a\r\n a"), "a  a");
		assertEquals(StringUtils.singleLine("a \r\n a"), "a   a");
		assertEquals(StringUtils.singleLine("a \r\n a"), "a   a");
		assertEquals(StringUtils.singleLine("a\n\ra"), "a  a");
		assertEquals(StringUtils.singleLine("a \n\ra"), "a   a");
		assertEquals(StringUtils.singleLine("a\n\r a"), "a   a");
		assertEquals(StringUtils.singleLine("a \n\r a"), "a    a");
	}

	@Test
	@DisplayName("Object to String")
	public void testObjectToString() {
		StringClass object = new StringClass();

		assertEquals(
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt
			), "StringClass[attributeInt=1]");

		assertEquals(
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat
			), "StringClass[attributeInt=1, attributeFloat=1.1]");

		assertEquals(
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat,
				"attributeBoolean", object.attributeBoolean
			), "StringClass[attributeInt=1, attributeFloat=1.1, attributeBoolean=true]");

		assertEquals(
			StringUtils.objectToString(
				object,
				"attributeInt", object.attributeInt,
				"attributeFloat", object.attributeFloat,
				"attributeBoolean", object.attributeBoolean,
				"attributeString", object.attributeString
			), "StringClass[attributeInt=1, attributeFloat=1.1, attributeBoolean=true, attributeString=A String]");
	}

	@Test
	@DisplayName("Get simple name of")
	public void testGetSimpleNameOf() {
		assertEquals(StringUtils.getSimpleNameOf(StringClass.class.getName()), StringClass.class.getSimpleName());
		assertEquals(StringUtils.getSimpleNameOf(StringClass.class.getSimpleName()), StringClass.class.getSimpleName());
		assertEquals(StringUtils.getSimpleNameOf(TestStringUtils.class.getName()), TestStringUtils.class.getSimpleName());
		assertEquals(StringUtils.getSimpleNameOf(TestStringUtils.class.getSimpleName()), TestStringUtils.class.getSimpleName());
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

	private class StringClass {
		public int attributeInt = 1;
		public float attributeFloat = 1.1f;
		public boolean attributeBoolean = true;
		public String attributeString = "A String";
	}
}
