package org.norsh.model.transport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.norsh.rest.RestMethod;

/**
 * Annotation for marking service methods that process specific DTOs.
 * <p>
 * This annotation can be applied multiple times on the same method to indicate 
 * that it processes multiple HTTP methods.
 * </p>
 *
 * @since 1.0.0
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Processables.class) 
public @interface Processable {
    RestMethod method();
}
