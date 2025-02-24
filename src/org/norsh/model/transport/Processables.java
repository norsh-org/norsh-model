package org.norsh.model.transport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container annotation for allowing multiple {@link Processable} annotations on a method.
 * <p>
 * This annotation is automatically used when multiple `@Processable` are applied to the same method.
 * </p>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Processables {
	Processable[] value();
}
