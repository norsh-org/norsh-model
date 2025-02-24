package org.norsh.model.dtos.elements;

import java.util.regex.Pattern;

import org.norsh.constants.Constants;
import org.norsh.constants.FeePolicy;
import org.norsh.exceptions.OperationException;
import org.norsh.model.dtos.crypto.CryptoSignedAbstractDto;
import org.norsh.model.types.ElementType;
import org.norsh.security.Hasher;
import org.norsh.util.Converter;
import org.norsh.util.Strings;

/**
 * Data Transfer Object (DTO) for creating an Element.
 * <p>
 * This class encapsulates the required information for creating a new Element in the system.
 * It validates critical fields such as the owner, element type, symbol, decimals, supply, public key,
 * the Two-Factor Ownership (TFO) data, and the cryptographic signature to ensure data integrity and compliance with business rules.
 * </p>
 *
 * <h2>Validation and Special Rules:</h2>
 * <ul>
 *   <li>If the {@code type} is {@link ElementType#PROXY}, the {@code symbol} must end with '{@value Constants#getProxySuffix()}'.</li>
 *   <li>A minimum transaction fee is required for creation, which must be at least {@link FeePolicy#getElementCreateAmountWithTax()}.</li>
 *   <li>The transaction must be directed to the official Norsh wallet and use the Norsh native token.</li>
 *   <li>The provided public key must be in a valid Base64 or Hex format, and the owner is verified against its SHA3 hash.</li>
 *   <li>A cryptographic hash is generated using the symbol, decimals, supply, the TFO data, and the public key for integrity verification.</li>
 *   <li>Cryptographic signature validation is performed to ensure authenticity.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @see ElementType
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
public class ElementCreateDto extends CryptoSignedAbstractDto {
    /** Type of the Element. Cannot be null and must correspond to a valid {@link ElementType}. */
    private ElementType type;

    /** Token symbol. Cannot be null and must contain 2 to 8 uppercase letters (A-Z). */
    private String symbol;

    /** Number of decimals. Cannot be null and must be between 0 and 18. */
    private Integer decimals;

    /** Number of tokens. Cannot be null and must be between 1 and 1,000,000,000 (1 billion). */
    private Long initialSupply;

    /**
     * Two-Factor Ownership (TFO) data used as proof that the token owner possesses not only the keys but also secret information.
     * This field is optional and serves solely as evidence in case of authority disputes.
     */
    private String tfo;

    /**
     * Validates the fields of the ElementCreateDto based on predefined business rules.
     * <p>
     * Validation Process:
     * <ul>
     *   <li>Ensures that the public key is present, not blank, and in a valid Base64 or Hex format.</li>
     *   <li>Verifies that the owner matches the SHA3 hash of the provided public key.</li>
     *   <li>Checks that the {@code type}, {@code symbol}, {@code decimals}, and {@code supply} meet the required constraints.</li>
     *   <li>For {@link ElementType#PROXY} elements, validates that the {@code symbol} ends with '{@value Constants#getProxySuffix()}'.</li>
     *   <li>Confirms that a valid transaction is provided, with a fee at least equal to {@link FeePolicy#getElementCreateAmountWithTax()}.</li>
     *   <li>Validates that the transaction destination is the official Norsh wallet and that the token used is the Norsh native token.</li>
     *   <li>Generates a cryptographic hash using the symbol, decimals, supply, TFO data, and public key for integrity verification.</li>
     *   <li>Performs cryptographic signature validation.</li>
     * </ul>
     * </p>
     *
     * @throws OperationException if any validation rule is violated.
     */
    public void validate() throws OperationException {
        // Validate Token Symbol
        if (type == ElementType.PROXY) {
            if (symbol == null || symbol.isBlank() || !Pattern.matches("^[A-Z]{2,8}(" + Constants.getProxySuffix() + ")$", symbol)) {
                throw new OperationException(String.format("Invalid 'symbol' for Proxy Element: The symbol is required, must contain 2 to 8 uppercase letters (A-Z), and must end with '%s'.", Constants.getProxySuffix()));
            }
        } else if (symbol == null || symbol.isBlank() || !Pattern.matches("^[A-Z]{2,8}$", symbol)) {
            throw new OperationException("Invalid symbol: The 'symbol' field is required, cannot be null, and must contain 2 to 8 uppercase letters (A-Z).");
        }

        // Validate Decimals
        if (decimals == null || decimals < 0 || decimals > 18) {
            throw new OperationException("Invalid decimals: The 'decimals' field is required, cannot be null, and must be between 0 and 18.");
        }

        // Validate Supply
        if (type == ElementType.PROXY) {
            if (initialSupply != null) {
                throw new OperationException("Invalid supply: For PROXY, supply should not be provided.");
            }
        } else if (initialSupply == null || initialSupply < 0 || initialSupply > 1_000_000_000) {
            throw new OperationException("Invalid supply: The 'supply' field is required, cannot be null, and must be between 0 and 1,000,000,000.");
        }

        // Validate Two-Factor Ownership (TFO) data
        if (tfo != null && !Converter.isBase64OrHex(tfo)) {
            throw new OperationException("Invalid Two-Factor Ownership: The 'tfo' must be in Hex format or null if not used.");
        }

        // Validate Public Key using parent method
        super.validatePublicKey();

        // Generate hash for integrity verification using symbol, decimals, supply, TFO data, and public key
        this.setHash(Hasher.sha256Hex(Strings.concatenate(symbol, decimals, initialSupply, tfo, getPublicKey())));
        
        // Validate signature using parent method
        super.validateSignature();
        
        // If no requestId is set, use the generated hash as the requestId
        if (this.getRequestId() == null) {
            super.setRequestId(this.getHash());
        }
    }

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getDecimals() {
		return decimals;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public Long getInitialSupply() {
		return initialSupply;
	}

	public void setInitialSupply(Long initialSupply) {
		this.initialSupply = initialSupply;
	}

	public String getTfo() {
		return tfo;
	}

	public void setTfo(String tfo) {
		this.tfo = tfo;
	}
    
    
}
