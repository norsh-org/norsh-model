package org.norsh.model.dtos.elements;

import org.norsh.model.dtos.crypto.CryptoSignedAbstractDto;
import org.norsh.model.dtos.transactions.TransactionCreateDto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO representing metadata for a Smart Element with cryptographic validation.
 * <p>
 * This class ensures the integrity of metadata associated with a Smart Element by validating essential fields
 * and enforcing cryptographic signature validation.
 * </p>
 *
 * <h2>Update Behavior:</h2>
 * <ul>
 *   <li>{@code null} → Does not modify the field in the database.</li>
 *   <li>{@code ""} (empty string) → Clears the field by saving {@code null} in the database.</li>
 *   <li>Any other value → Updates the field with the provided value.</li>
 * </ul>
 *
 *
 * <h2>Example Usage:</h2>
 * <pre>
 * ElementMetadataDto metadata = new ElementMetadataDto();
 * metadata.setName("");  // This will clear the name field (set it to null)
 * metadata.setLogo("https://example.com/logo.png"); // Updates the logo field
 * metadata.validate();
 * </pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see CryptoSignedAbstractDto
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */

@Getter
@Setter
public class ElementMetadataDto extends CryptoSignedAbstractDto {
	/** Id of the Smart Element. */
	private String id;
	
	/** Name of the Smart Element. If provided, must be between 2 and 32 characters. */
	private String name;

	/** URL of the Smart Element's logo. If provided, must be a valid URL with a maximum of 256 characters. */
	private String logo;

	/** Description of the Smart Element. If provided, must be at most 4096 characters. */
	private String about;

	/** Official website URL of the Smart Element. If provided, must be a valid URL with a maximum of 256 characters. */
	private String site;

	/** URL to the Smart Element's policy document. If provided, must be a valid URL with a maximum of 256 characters. */
	private String policy;
	
	private TransactionCreateDto transaction;

//	/**
//	 * Validates the fields of the ElementMetadataDto based on predefined business rules.
//	 *
//	 * <h2>Validation Process:</h2>
//	 * <ul>
//	 * <li>Ensures that the hash is properly formatted if provided.</li>
//	 * <li>Trims empty values and sets them to {@code null}.</li>
//	 * <li>Validates name length constraints.</li>
//	 * <li>Validates URLs for logo, site, and policy fields.</li>
//	 * <li>Ensures at least one metadata field is provided.</li>
//	 * <li>Generates a hash to verify data integrity.</li>
//	 * <li>Performs cryptographic signature validation.</li>
//	 * </ul>
//	 *
//	 * @throws OperationException if any validation rule is violated.
//	 */
//	public void validate() throws OperationException {
//		List<String> details = new LinkedList<>();
//
//		// Validate Hash
//		if (getId() == null || !getId().matches("^[a-fA-F0-9]{64}$")) {
//			details.add("Invalid hash: The 'hash' field must be a valid 64-character hexadecimal hash.");
//		}
//
//		// Validate Name
//		if (name != null && !name.isBlank() && (name.trim().length() < 2 || name.trim().length() > 32)) {
//			details.add("Invalid name: The 'name' field must contain between 2 and 32 characters.");
//		}
//
//		// Validate Logo
//		if (logo != null && !logo.isBlank()) {
//			if (logo.trim().length() > 256) {
//				details.add("Invalid logo: The 'logo' field must contain at most 256 characters.");
//			} else if (!Strings.isValidUrl(logo)) {
//				details.add("Invalid logo: The 'logo' field must be a valid URL.");
//			}
//		}
//
//		// Validate About
//		if (about != null && !about.isBlank() && about.trim().length() > 4096) {
//			details.add("Invalid about: The 'about' field must contain at most 4096 characters.");
//		}
//
//		// Validate Site
//		if (site != null && !site.isBlank()) {
//			if (site.trim().length() > 256) {
//				details.add("Invalid site: The 'site' field must contain at most 256 characters.");
//			} else if (!Strings.isValidUrl(site)) {
//				details.add("Invalid site: The 'site' field must be a valid URL.");
//			}
//		}
//
//		// Validate Policy
//		if (policy != null && !policy.isBlank()) {
//			if (policy.trim().length() > 256) {
//				details.add("Invalid policy: The 'policy' field must contain at most 256 characters.");
//			} else if (!Strings.isValidUrl(policy)) {
//				details.add("Invalid policy: The 'policy' field must be a valid URL.");
//			}
//		}
//
//		// Ensure at least one metadata field is provided
//		if (name == null && logo == null && about == null && site == null && policy == null) {
//			details.add("Invalid metadata: At least one field ('name', 'logo', 'about', 'site', or 'policy') must be provided.");
//		}
//
//		super.validate(details);
//
//		// Generate hash for integrity verification
//		this.setHash(Hasher.sha256Hex(Strings.concatenate(getId(), name, logo, about, site, policy, getPublicKey())));
//
//		// Perform cryptographic signature validation
//		super.validate();
//		
//		// Validate Transaction
//        if (transaction == null) {
//            details.add("A valid transaction is required for element update.");
//        } else {
//            BigDecimal requiredFee = NorshFeePolicy.getElementUpdateAmountWithTax();
//            
//            if (id.equals(NorshConstants.getNorshElement())) {
//				requiredFee = BigDecimal.ZERO;
//			}
//
//            BigDecimal transactionVolume = new BigDecimal(transaction.getVolume());
//
//            if (transactionVolume.compareTo(requiredFee) < 0) {
//                details.add("Transaction volume is insufficient. Minimum required: " + requiredFee);
//            }
//
//            if (!transaction.getTo().equals(NorshConstants.getNorshWallet())) {
//                details.add("Invalid transaction destination: The recipient must be the official Norsh wallet. Use " + NorshConstants.getNorshWallet());
//            }
//
//            if (!transaction.getElement().equals(NorshConstants.getNorshElement())) {
//                details.add("Invalid transaction token: The transaction must use the Norsh native token. Use " + NorshConstants.getNorshElement());
//            }
//        }
//
//        super.validate(details);
//        transaction.validate();
//	}
	
	
}
