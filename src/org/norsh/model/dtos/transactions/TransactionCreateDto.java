package org.norsh.model.dtos.transactions;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.norsh.exceptions.OperationException;
import org.norsh.model.dtos.crypto.CryptoSignedAbstractDto;
import org.norsh.security.Hasher;
import org.norsh.util.Strings;

/**
 * DTO for creating a payment transaction.
 * <p>
 * This DTO ensures integrity and correctness of payment creation requests by validating essential fields
 * such as recipient, token, amount, and nonce.
 * </p>
 *
 * <h2>Validation Rules:</h2>
 * <ul>
 *   <li>{@code to}: Must be a valid 64-character hexadecimal hash.</li>
 *   <li>{@code token}: Must be a valid 64-character hexadecimal hash.</li>
 *   <li>{@code amount}: Must be a valid decimal number greater than 0.</li>
 *   <li>{@code nonce}: Must be a non-negative integer.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 1.0.0
 */
public class TransactionCreateDto extends CryptoSignedAbstractDto {
	private String to;      // Recipient's identifier
	private String element; // Smart Element
	private String volume;  // Transaction volume
	private Long nonce = 0l;

	/**
	 * Validates the fields of the PaymentCreateDto based on predefined business rules.
	 *
	 * @throws OperationException if any validation rule is violated.
	 */
	public void validate() throws OperationException {
		List<String> details = new LinkedList<>();

		// Validate recipient (to)
		if (to == null || !to.matches("^[a-f0-9]{64}$")) {
			details.add("Invalid to: The 'to' field must be a valid 64-character hexadecimal hash.");
		}
		
		if (element == null || !element.matches("^[a-f0-9]{64}$")) {
			details.add("Invalid element: The 'element' field must be a valid 64-character hexadecimal hash.");
		}

		// Validate volume
		try {
			if (volume == null || volume.isBlank()) {
				details.add("Invalid volume: The 'volume' field is required and cannot be null or empty.");
			} else {
				BigDecimal bdAmount = new BigDecimal(volume);
				if (bdAmount.compareTo(BigDecimal.ZERO) < 0) {
					details.add("Invalid volume: The 'volume' field must be greater than zero.");
				}
			}
		} catch (NumberFormatException ex) {
			details.add("Invalid amount: The 'volume' field must be a valid decimal number.");
		}

		this.setHash(Hasher.sha256Hex(Strings.concatenate(to, element, volume, nonce, getPublicKey())));
		
		super.validate();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Long getNonce() {
		return nonce;
	}

	public void setNonce(Long nonce) {
		this.nonce = nonce;
	}
}
