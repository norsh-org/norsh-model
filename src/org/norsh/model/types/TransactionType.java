package org.norsh.model.types;

/**
 * Enum representing subtypes of smart elements with corresponding IDs.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
public enum TransactionType {
	INTERNAL,
	PAYMENT,
	TRANSFER,
	CAPTURE,
	REWARD;
}
