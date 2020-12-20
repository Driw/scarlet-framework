package org.diverproject.scarlet.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
	ElementType.TYPE,
	ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Injectable {
	String value() default "";
}
