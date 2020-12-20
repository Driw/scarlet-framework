package org.diverproject.scarlet.context.reflection;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class FieldAccess {
	private Field field;
	private boolean accessible;

	public FieldAccess(Field field) {
		this.setField(field);
	}

	public FieldAccess access() {
		this.setAccessible(field.isAccessible());
		field.setAccessible(true);
		return this;
	}

	public FieldAccess set(Object object, Object value) {
		try {
			this.getField().set(object, value);
		} catch (IllegalAccessException e) {
			throw ReflectionExceptionFactory.fieldAccessSet(e, object, this.getField(), value);
		}
		return this;
	}

	public void finish() {
		if (!this.isAccessible())
			field.setAccessible(false);
	}

	public void accessThenSetThenFinish(Object object, Object value) {
		this.access()
			.set(object, value)
			.finish();
	}

}
