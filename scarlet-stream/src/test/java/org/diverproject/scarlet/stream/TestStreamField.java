package org.diverproject.scarlet.stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.function.Function;

@DisplayName("Stream Field")
public class TestStreamField {

	private final AllFields ALL_FIELDS = new AllFields();

	@Test
	public void testThenDoWithSupplier() throws IllegalAccessException {
		AllFields allFields = new AllFields();
		assertEquals(0, allFields.getByteValue());
		assertEquals(0, allFields.getShortValue());
		assertEquals(0, allFields.getIntValue());
		assertEquals(0L, allFields.getLongValue());
		assertEquals(0F, allFields.getFloatValue());
		assertEquals(0D, allFields.getDoubleValue());
		assertFalse(allFields.isBooleanValue());
		assertNull(allFields.getByteObjectValue());
		assertNull(allFields.getShortObjectValue());
		assertNull(allFields.getIntObjectValue());
		assertNull(allFields.getLongObjectValue());
		assertNull(allFields.getFloatObjectValue());
		assertNull(allFields.getDoubleObjectValue());
		assertNull(allFields.getBooleanObjectValue());
		assertEquals(0, allFields.getCharValue());
		assertNull(allFields.getStringValue());
		assertNull(allFields.getAllFields());

		Function<String, Field> allFieldsArrayField = (fieldName) -> {
			try {
				Field field = allFields.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		StreamField streamField = new StreamField();
		streamField.reset().setField(allFieldsArrayField.apply("byteValue")).of(allFields).is(Byte.TYPE).thenDo(this::getByte);
		streamField.reset().setField(allFieldsArrayField.apply("shortValue")).of(allFields).is(Short.TYPE).thenDo(this::getShort);
		streamField.reset().setField(allFieldsArrayField.apply("intValue")).of(allFields).is(Integer.TYPE).thenDo(this::getInt);
		streamField.reset().setField(allFieldsArrayField.apply("longValue")).of(allFields).is(Long.TYPE).thenDo(this::getLong);
		streamField.reset().setField(allFieldsArrayField.apply("floatValue")).of(allFields).is(Float.TYPE).thenDo(this::getFloat);
		streamField.reset().setField(allFieldsArrayField.apply("doubleValue")).of(allFields).is(Double.TYPE).thenDo(this::getDouble);
		streamField.reset().setField(allFieldsArrayField.apply("booleanValue")).of(allFields).is(Boolean.TYPE).thenDo(this::getBoolean);
		streamField.reset().setField(allFieldsArrayField.apply("charValue")).of(allFields).is(Character.TYPE).thenDo(this::getChar);
		streamField.reset().setField(allFieldsArrayField.apply("byteObjectValue")).of(allFields).is(Byte.class).thenDo(this::getByteObject);
		streamField.reset().setField(allFieldsArrayField.apply("shortObjectValue")).of(allFields).is(Short.class).thenDo(this::getShortObject);
		streamField.reset().setField(allFieldsArrayField.apply("intObjectValue")).of(allFields).is(Integer.class).thenDo(this::getIntObject);
		streamField.reset().setField(allFieldsArrayField.apply("longObjectValue")).of(allFields).is(Long.class).thenDo(this::getLongObject);
		streamField.reset().setField(allFieldsArrayField.apply("floatObjectValue")).of(allFields).is(Float.class).thenDo(this::getFloatObject);
		streamField.reset().setField(allFieldsArrayField.apply("doubleObjectValue")).of(allFields).is(Double.class).thenDo(this::getDoubleObject);
		streamField.reset().setField(allFieldsArrayField.apply("booleanObjectValue")).of(allFields).is(Boolean.class).thenDo(this::getBooleanObject);
		streamField.reset().setField(allFieldsArrayField.apply("charObjectValue")).of(allFields).is(Character.class).thenDo(this::getCharObject);
		streamField.reset().setField(allFieldsArrayField.apply("stringValue")).of(allFields).is(String.class).thenDo(this::getString);
		streamField.reset().setField(allFieldsArrayField.apply("allFields")).of(allFields).is(Object.class).thenDo(this::getObject);

		assertEquals(Byte.MAX_VALUE, allFields.getByteValue());
		assertEquals(Short.MAX_VALUE, allFields.getShortValue());
		assertEquals(Integer.MAX_VALUE, allFields.getIntValue());
		assertEquals(Long.MAX_VALUE, allFields.getLongValue());
		assertEquals(Float.MAX_VALUE, allFields.getFloatValue());
		assertEquals(Double.MAX_VALUE, allFields.getDoubleValue());
		assertTrue(allFields.isBooleanValue());
		assertEquals(Byte.MAX_VALUE, allFields.getByteObjectValue());
		assertEquals(Short.MAX_VALUE, allFields.getShortObjectValue());
		assertEquals(Integer.MAX_VALUE, allFields.getIntObjectValue());
		assertEquals(Long.MAX_VALUE, allFields.getLongObjectValue());
		assertEquals(Float.MAX_VALUE, allFields.getFloatObjectValue());
		assertEquals(Double.MAX_VALUE, allFields.getDoubleObjectValue());
		assertTrue(allFields.getBooleanObjectValue());
		assertEquals(Character.MAX_VALUE, allFields.getCharValue());
		assertEquals(TestStreamField.class.getName(), allFields.getStringValue());
		assertEquals(ALL_FIELDS, allFields.getAllFields());
	}

	@Test
	public void testThenDoWithConsumer() throws Exception {
		AllFieldsArray allFieldsArray = new AllFieldsArray();
		assertNull(allFieldsArray.getByteValue());
		assertNull(allFieldsArray.getShortValue());
		assertNull(allFieldsArray.getIntValue());
		assertNull(allFieldsArray.getLongValue());
		assertNull(allFieldsArray.getFloatValue());
		assertNull(allFieldsArray.getDoubleValue());
		assertNull(allFieldsArray.getBooleanValue());
		assertNull(allFieldsArray.getCharValue());
		assertNull(allFieldsArray.getStringValue());
		assertNull(allFieldsArray.getAllFields());

		Function<String, Field> allFieldsArrayField = (fieldName) -> {
			try {
				Field field = allFieldsArray.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		StreamField streamField = new StreamField();
		streamField.reset().setField(allFieldsArrayField.apply("byteValue")).of(allFieldsArray).isArray(Byte.TYPE).thenDo(this::getBytes);
		streamField.reset().setField(allFieldsArrayField.apply("shortValue")).of(allFieldsArray).isArray(Short.TYPE).thenDo(this::getShorts);
		streamField.reset().setField(allFieldsArrayField.apply("intValue")).of(allFieldsArray).isArray(Integer.TYPE).thenDo(this::getInts);
		streamField.reset().setField(allFieldsArrayField.apply("longValue")).of(allFieldsArray).isArray(Long.TYPE).thenDo(this::getLongs);
		streamField.reset().setField(allFieldsArrayField.apply("floatValue")).of(allFieldsArray).isArray(Float.TYPE).thenDo(this::getFloats);
		streamField.reset().setField(allFieldsArrayField.apply("doubleValue")).of(allFieldsArray).isArray(Double.TYPE).thenDo(this::getDoubles);
		streamField.reset().setField(allFieldsArrayField.apply("booleanValue")).of(allFieldsArray).isArray(Boolean.TYPE).thenDo(this::getBooleans);
		streamField.reset().setField(allFieldsArrayField.apply("charValue")).of(allFieldsArray).isArray(Character.TYPE).thenDo(this::getChars);
		streamField.reset().setField(allFieldsArrayField.apply("byteObjectValue")).of(allFieldsArray).isArray(Byte.class).thenDo(this::getBytesObject);
		streamField.reset().setField(allFieldsArrayField.apply("shortObjectValue")).of(allFieldsArray).isArray(Short.class).thenDo(this::getShortsObject);
		streamField.reset().setField(allFieldsArrayField.apply("intObjectValue")).of(allFieldsArray).isArray(Integer.class).thenDo(this::getIntsObject);
		streamField.reset().setField(allFieldsArrayField.apply("longObjectValue")).of(allFieldsArray).isArray(Long.class).thenDo(this::getLongsObject);
		streamField.reset().setField(allFieldsArrayField.apply("floatObjectValue")).of(allFieldsArray).isArray(Float.class).thenDo(this::getFloatsObject);
		streamField.reset().setField(allFieldsArrayField.apply("doubleObjectValue")).of(allFieldsArray).isArray(Double.class).thenDo(this::getDoublesObject);
		streamField.reset().setField(allFieldsArrayField.apply("booleanObjectValue")).of(allFieldsArray).isArray(Boolean.class).thenDo(this::getBooleansObject);
		streamField.reset().setField(allFieldsArrayField.apply("charObjectValue")).of(allFieldsArray).isArray(Character.class).thenDo(this::getCharsObject);
		streamField.reset().setField(allFieldsArrayField.apply("stringValue")).of(allFieldsArray).isArray(String.class).thenDo(this::getStrings);
		streamField.reset().setField(allFieldsArrayField.apply("allFields")).of(allFieldsArray).isArray(Object.class).thenDo(this::getObjects);

		assertArrayEquals(this.getBytes(), allFieldsArray.getByteValue());
		assertArrayEquals(this.getShorts(), allFieldsArray.getShortValue());
		assertArrayEquals(this.getInts(), allFieldsArray.getIntValue());
		assertArrayEquals(this.getLongs(), allFieldsArray.getLongValue());
		assertArrayEquals(this.getFloats(), allFieldsArray.getFloatValue());
		assertArrayEquals(this.getDoubles(), allFieldsArray.getDoubleValue());
		assertArrayEquals(this.getBooleans(), allFieldsArray.getBooleanValue());
		assertArrayEquals(this.getChars(), allFieldsArray.getCharValue());
		assertArrayEquals(this.getStrings(), allFieldsArray.getStringValue());
		assertArrayEquals(this.getObjects(), allFieldsArray.getAllFields());
	}

	private byte getByte() {
		return Byte.MAX_VALUE;
	}

	private short getShort() {
		return Short.MAX_VALUE;
	}

	private int getInt() {
		return Integer.MAX_VALUE;
	}

	private long getLong() {
		return Long.MAX_VALUE;
	}

	private float getFloat() {
		return Float.MAX_VALUE;
	}

	private double getDouble() {
		return Double.MAX_VALUE;
	}

	private boolean getBoolean() {
		return true;
	}

	private char getChar() {
		return Character.MAX_VALUE;
	}

	private Byte getByteObject() {
		return Byte.MAX_VALUE;
	}

	private Short getShortObject() {
		return Short.MAX_VALUE;
	}

	private Integer getIntObject() {
		return Integer.MAX_VALUE;
	}

	private Long getLongObject() {
		return Long.MAX_VALUE;
	}

	private Float getFloatObject() {
		return Float.MAX_VALUE;
	}

	private Double getDoubleObject() {
		return Double.MAX_VALUE;
	}

	private Boolean getBooleanObject() {
		return true;
	}

	private Character getCharObject() {
		return Character.MAX_VALUE;
	}

	private String getString() {
		return TestStreamField.class.getName();
	}

	private AllFields getObject() {
		return ALL_FIELDS;
	}

	private byte[] getBytes() {
		return new byte[]{Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE};
	}

	private short[] getShorts() {
		return new short[]{Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE};
	}

	private int[] getInts() {
		return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
	}

	private long[] getLongs() {
		return new long[]{Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE};
	}

	private float[] getFloats() {
		return new float[]{Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE};
	}

	private double[] getDoubles() {
		return new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
	}

	private boolean[] getBooleans() {
		return new boolean[]{true, true, true};
	}

	private char[] getChars() {
		return new char[]{Character.MAX_VALUE, Character.MAX_VALUE, Character.MAX_VALUE};
	}

	private Byte[] getBytesObject() {
		return new Byte[]{Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE};
	}

	private Short[] getShortsObject() {
		return new Short[]{Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE};
	}

	private Integer[] getIntsObject() {
		return new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
	}

	private Long[] getLongsObject() {
		return new Long[]{Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE};
	}

	private Float[] getFloatsObject() {
		return new Float[]{Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE};
	}

	private Double[] getDoublesObject() {
		return new Double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
	}

	private Boolean[] getBooleansObject() {
		return new Boolean[]{true, true, true};
	}

	private Character[] getCharsObject() {
		return new Character[]{Character.MAX_VALUE, Character.MAX_VALUE, Character.MAX_VALUE};
	}

	private String[] getStrings() {
		return new String[]{TestStreamField.class.getName(), TestStreamField.class.getName(), TestStreamField.class.getName()};
	}

	private AllFields[] getObjects() {
		return new AllFields[]{ALL_FIELDS, ALL_FIELDS, ALL_FIELDS};
	}

	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	@AllArgsConstructor
	private static class AllFields {
		private byte byteValue;
		private short shortValue;
		private int intValue;
		private long longValue;
		private float floatValue;
		private double doubleValue;
		private boolean booleanValue;
		private char charValue;
		private Byte byteObjectValue;
		private Short shortObjectValue;
		private Integer intObjectValue;
		private Long longObjectValue;
		private Float floatObjectValue;
		private Double doubleObjectValue;
		private Boolean booleanObjectValue;
		private Character charObjectValue;
		private String stringValue;
		private AllFields allFields;
	}

	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	@AllArgsConstructor
	private static class AllFieldsArray {
		private byte[] byteValue;
		private short[] shortValue;
		private int[] intValue;
		private long[] longValue;
		private float[] floatValue;
		private double[] doubleValue;
		private boolean[] booleanValue;
		private char[] charValue;
		private Byte[] byteObjectValue;
		private Short[] shortObjectValue;
		private Integer[] intObjectValue;
		private Long[] longObjectValue;
		private Float[] floatObjectValue;
		private Double[] doubleObjectValue;
		private Boolean[] booleanObjectValue;
		private Character[] charObjectValue;
		private String[] stringValue;
		private AllFields[] allFields;
	}
}
