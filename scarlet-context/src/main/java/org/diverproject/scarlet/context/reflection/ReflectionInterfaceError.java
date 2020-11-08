package org.diverproject.scarlet.context.reflection;

import lombok.Getter;
import lombok.Setter;
import org.diverproject.scarlet.language.Language;

public enum ReflectionInterfaceError implements Language {

	CLASS_IS_NOT_A_INTERFACE                			    ("the class informed is not a interface type (className: %s)"),
	GET_INSTANCE_OF_UNIMPLEMENTED_INTERFACE					("cannot create a instance of a interface that is not implemented (interfaceClass: %s)")
	;

	@Getter
	@Setter
	private String format;

	ReflectionInterfaceError(String format) {
		this.format = format;
	}

	@Override
	public int getCode() {
		return this.ordinal() + 1;
	}

}
