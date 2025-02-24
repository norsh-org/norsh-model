package org.norsh.model.dtos.crypto;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the request body for the "Get Address" API.
 * <p>
 * This class encapsulates the field required to generate a unique address based on the provided public key.
 * </p>
 *
 * <h3>Field:</h3>
 * <ul>
 *   <li>{@code publicKey}: The Base64 or Hexadecimal encoded public key used for address generation.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {
 *   "publicKey": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE..."
 * }
 * </pre>
 *
 * @license NCL-139
 * @since 2024
 * @version 1.0
 * @author Danthur Lice
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */

@Getter
@Setter
public class AddressApiV1GenerateDto {
    /**
     * The Base64 or Hexadecimal encoded public key used for address generation.
     */
    private String publicKey;
}
