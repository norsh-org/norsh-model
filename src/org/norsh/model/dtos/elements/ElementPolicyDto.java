package org.norsh.model.dtos.elements;

import org.norsh.model.dtos.crypto.CryptoSignedAbstractDto;
import org.norsh.model.dtos.transactions.TransactionCreateDto;

/**
 * DTO representing the policy configuration for a Smart Element.
 * <p>
 * This class defines governance rules, transaction taxation, freezing durations, 
 * and execution scripts for a Smart Element, ensuring validation and cryptographic integrity.
 * </p>
 *
 * <h2>Validation Rules:</h2>
 * <ul>
 *   <li>The {@link #id} must be a valid 64-character hexadecimal hash.</li>
 *   <li>The {@link #publicKey} must be in Base64 or Hex format.</li>
 *   <li>The {@link #transactionTax} must be between 0 and 100 (percentage-based).</li>
 *   <li>The {@link #freezeDuration} must be greater than 0.</li>
 *   <li>The {@link #script} must be in Base64 format if provided.</li>
 * </ul>
 *
 * <h2>Example Usage:</h2>
 * <pre>
 * ElementPolicyDto policy = new ElementPolicyDto();
 * policy.setId("a3f2b1c9...");
 * policy.setTransactionTax(2.5);
 * policy.setFreezeDuration(48);
 * policy.setPublicKey("BASE64_ENCODED_PUBLIC_KEY");
 * policy.setSignature("HEX_SIGNATURE");
 * policy.validate();
 * </pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
public class ElementPolicyDto extends CryptoSignedAbstractDto {
    
    /**
     * Unique identifier of the Smart Element.
     * <p>
     * This ID must be a 64-character hexadecimal hash that uniquely identifies the element 
     * for which this policy is applied.
     * </p>
     */
    private String id;

    /**
     * The global transaction tax percentage applied to transfers involving this Smart Element.
     * <p>
     * This value must be between 0 and 100, representing the percentage of the transaction 
     * that will be collected as a fee.
     * </p>
     */
    private Double transactionTax;

    /**
     * The minimum duration (in hours) before an asset can be transferred after being received.
     * <p>
     * This setting enforces time-based transfer restrictions to prevent rapid asset movements.
     * The value must be greater than 0.
     * </p>
     */
    private Integer freezeDuration;

    /**
     * The execution script associated with the Smart Element.
     * <p>
     * This script defines custom logic for the element, such as additional validation rules 
     * or contract execution policies. If provided, it must be encoded in Base64 format.
     * </p>
     */
    private String script;
    
    private TransactionCreateDto transaction;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getTransactionTax() {
		return transactionTax;
	}

	public void setTransactionTax(Double transactionTax) {
		this.transactionTax = transactionTax;
	}

	public Integer getFreezeDuration() {
		return freezeDuration;
	}

	public void setFreezeDuration(Integer freezeDuration) {
		this.freezeDuration = freezeDuration;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public TransactionCreateDto getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionCreateDto transaction) {
		this.transaction = transaction;
	}
    
    

//    /**
//     * Validates the policy attributes according to predefined business rules.
//     * <p>
//     * Ensures all required fields meet their constraints and performs cryptographic validation.
//     * </p>
//     *
//     * @throws OperationException if any validation rule is violated.
//     */
//    public void validate() throws OperationException {
//        List<String> details = new LinkedList<>();
//
//        // Validate Smart Element ID
//        if (getId() == null || !getId().matches("^[a-fA-F0-9]{64}$")) {
//            details.add("Invalid id: The 'id' field must be a valid 64-character hexadecimal hash.");
//        }
//        
//        // Validate Public Key
//        if (getPublicKey() == null || getPublicKey().isBlank() || !Converter.isBase64OrHex(getPublicKey())) {
//            details.add("Invalid public key: The 'publicKey' field is required, cannot be null, and must be in Base64 or Hex format.");
//        }
//
//        // Validate Freeze Duration
//        if (freezeDuration == null || freezeDuration < 0) {
//        	details.add("Invalid freezeDuration: The 'freezeDuration' field must be 0 or greater.");
//        }
//
//        // Validate Transaction Tax
//        if (transactionTax == null || transactionTax < 0 || transactionTax > 100) {
//            details.add("Invalid transactionTax: The 'transactionTax' field must be between 0 and 100.");
//        }
//        
//        // Validate Script
//        if (script != null && !script.isBlank() && !Converter.isBase64(script)) {
//            details.add("Invalid script: The 'script' field must be in Base64 format if provided.");
//        }
//
//        // Perform generic validation from CryptoSignedAbstractDto
//        super.validate(details);
//
//        // Generate hash for integrity verification
//        this.setHash(Hasher.sha256Hex(Strings.concatenate(id, transactionTax, freezeDuration, script, getPublicKey())));
//
//        // Perform cryptographic signature validation
//        super.validate();
//	}
//
//	public void validateTransaction() throws OperationException {
//		List<String> details = new LinkedList<>();
//
//		// Validate Transaction
//		if (transaction == null) {
//			details.add("A valid transaction is required for element update.");
//		} else {
//			BigDecimal requiredFee = NorshFeePolicy.getElementUpdateAmountWithTax();
//			BigDecimal transactionVolume = new BigDecimal(transaction.getVolume());
//
//			if (transactionVolume.compareTo(requiredFee) < 0) {
//				details.add("Transaction volume is insufficient. Minimum required: " + requiredFee);
//			}
//
//			if (!transaction.getTo().equals(NorshConstants.getNorshWallet())) {
//				details.add("Invalid transaction destination: The recipient must be the official Norsh wallet. Use " + NorshConstants.getNorshWallet());
//			}
//
//			if (!transaction.getElement().equals(NorshConstants.getNorshElement())) {
//				details.add("Invalid transaction token: The transaction must use the Norsh native token. Use " + NorshConstants.getNorshElement());
//			}
//		}
//
//		super.validate(details);
//		transaction.validate();
//	}
}
