package org.norsh.model.dtos.elements;

import org.norsh.model.dtos.crypto.CryptoSignedAbstractDto;
import org.norsh.model.dtos.transactions.TransactionCreateDto;
import org.norsh.model.types.Networks;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for registering external network addresses in a Smart Element proxy.
 * <p>
 * This DTO is used to link an external blockchain address to a Smart Element, allowing the proxy to 
 * track updates and retrieve balance information from the specified network.
 * </p>
 *
 * <h2>Fields:</h2>
 * <ul>
 *   <li>{@link #id} - The unique identifier (hash) of the Smart Element.</li>
 *   <li>{@link #network} - The blockchain network associated with the address (e.g., Ethereum, BSC, etc.).</li>
 *   <li>{@link #address} - The external blockchain address linked to the Smart Element.</li>
 * </ul>
 *
 * <h2>Validation Rules:</h2>
 * <ul>
 *   <li>{@code id} - Must be a valid 64-character hexadecimal hash.</li>
 *   <li>{@code network} - Must be a valid network defined in {@link Networks}.</li>
 *   <li>{@code address} - Must follow the format specific to the selected network.</li>
 *   <li>For Ethereum addresses, must start with {@code 0x} and be a 40-character hexadecimal hash.</li>
 * </ul>
 *
 * <h2>Example Usage:</h2>
 * <pre>
 * ElementNetworkDto networkDto = new ElementNetworkDto();
 * networkDto.setId("a3f2b1c9...");
 * networkDto.setNetwork(Networks.ETHEREUM);
 * networkDto.setAddress("0x123abc456def789...");
 * networkDto.validate();
 * </pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see Networks
 * @see CryptoSignedAbstractDto
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
@Getter
@Setter
public class ElementNetworkDto extends CryptoSignedAbstractDto {
    /** The unique identifier (hash) of the Smart Element. */
    private String id;

    /** The blockchain network associated with the address. */
    private Networks network;

    /** The external blockchain address linked to the Smart Element. */
    private String address;

    private TransactionCreateDto transaction;
    
//    /**
//     * Validates the fields of the {@link ElementNetworkDto} based on predefined business rules.
//     * <p>
//     * Ensures the ID is a valid hash, the network is correctly specified, and the address 
//     * follows the correct format based on the network type.
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
//        // Validate network-specific address formats
//        if (network == Networks.ETHEREUM) {
//            if (getAddress() == null || !getAddress().matches("^0x[a-fA-F0-9]{40}$")) {
//                details.add("Invalid address: Ethereum addresses must start with '0x' and contain a 40-character hexadecimal hash.");
//            }
//        }
//
//        // Perform additional cryptographic validation
//        super.validate(details);
//
//        // Generate hash for integrity verification
//        this.setHash(Hasher.sha256Hex(Strings.concatenate(getId(), network, address, getPublicKey())));
//
//        // Validate cryptographic signature
//        super.validate();
//        
//    }
//    
//    public void validateTransaction() throws OperationException {
//		List<String> details = new LinkedList<>();
//
//		// Validate Transaction
//        if (transaction == null) {
//            details.add("A valid transaction is required for element update.");
//        } else {
//            BigDecimal requiredFee = NorshFeePolicy.getElementUpdateAmountWithTax();
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
