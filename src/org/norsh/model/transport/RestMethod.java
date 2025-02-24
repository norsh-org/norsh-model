package org.norsh.model.transport;

/**
 * Enum representing REST methods for processing requests in the Norsh ecosystem.
 * <p>
 * This enum is used to decouple REST method representation from frameworks like Spring,
 * ensuring compatibility across different projects.
 * </p>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 */
public enum RestMethod {
    GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD;

    /**
     * Converts a string to a {@link RestMethod}.
     *
     * @param method The string representation of the REST method.
     * @return The corresponding {@link RestMethod}, or {@code null} if not found.
     */
    public static RestMethod fromString(String method) {
        try {
            return RestMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
