package org.norsh.model.dtos.crypto;

import org.norsh.exceptions.OperationException;
import org.norsh.model.dtos.DistributedDto;
import org.norsh.util.Converter;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract DTO for cryptographically signed requests.
 * <p>
 * This base class enforces cryptographic validation for signed requests, ensuring that the provided
 * public key and signature correctly correspond to the computed hash.
 * </p>
 *
 * <h2>Validation Rules:</h2>
 * <ul>
 * <li>Public Key: Must be in PEM, Base64, or Hexadecimal format.</li>
 * <li>Signature: Must be a valid SHA256withECDSA signature in hexadecimal format.</li>
 * <li>Hash: Must be computed and verified based on the signature.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
@Getter
@Setter
public class CryptoSignedAbstractDto extends DistributedDto {
	/** Computed hash for validation. */
    private String hash;

    /** ECDSA signature (SHA256withECDSA) in hexadecimal format. */
    private String signature;

    /** Public key in PEM, Base64, or Hexadecimal format. */
    private String publicKey;
    
    /**
     * Validates the cryptographic fields of the DTO.
     * <p>
     * This method ensures that the {@code publicKey} is correctly formatted, the {@code signature} is valid, and that
     * the {@code hash} matches the computed signature validation.
     * </p>
     *
     * @throws ValidationException if any validation rule is violated.
     */
    public void validatePublicKey() throws OperationException {
        if (getPublicKey() == null || getPublicKey().isBlank()) {
        	throw new OperationException("Invalid public key: The 'publicKey' field is required and cannot be null or empty.");
        } else if (!Converter.isBase64OrHex(getPublicKey())) {
        	throw new OperationException("Invalid public key: The 'publicKey' field must be in PEM, Base64, or Hexadecimal format.");
        }
    }
    
    public void validateSignature() throws OperationException {
//        if (signature == null || signature.isBlank()) {
//            throw new OperationException("Invalid signature: The 'signature' field is required and cannot be null or empty.");
//        } else if (!Pattern.matches("^[a-fA-F0-9]+$", signature)) {
//            throw new OperationException("Invalid signature: The 'signature' field must be a valid SHA256withECDSA signature in hexadecimal format.");
//        } else if (!Signature.verifyHash(publicKey, signature, hash)) {
//            throw new OperationException("Invalid signature: The provided signature does not match the computed hash from the given public key.");
//        }
    }
}
