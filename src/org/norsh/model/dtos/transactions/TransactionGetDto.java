package org.norsh.model.dtos.transactions;

import java.util.LinkedList;
import java.util.List;

import org.norsh.constants.Constants;
import org.norsh.model.dtos.DistributedDto;
import org.norsh.model.types.ElementType;

/**
 * DTO for creating an Element.
 * <p>
 * This class ensures the integrity and correctness of Element creation requests by validating essential fields such as
 * owner, type, symbol, decimals, nonce, public key and cryptographic signature.
 * </p>
 *
 * <h2>Special Cases:</h2>
 * <ul>
 * <li>If {@link #type} is {@link ElementType#PROXY}, the {@link #symbol} must end with
 * '{@value Constants#PROXY_SUFFIX}'.</li>
 * </ul>
 *
 * <h2>Example Usage:</h2>
 * 
 * <pre>
 * ElementCreateDto element = new ElementCreateDto();
 * element.setOwner("123abc...");
 * element.setType(ElementType.COIN);
 * element.setSymbol("NSH");
 * element.setDecimals(6);
 * element.setNonce(123456L);
 * element.setPublicKey("BASE64_ENCODED_PUBLIC_KEY");
 * element.setSignature("HEX_SIGNATURE");
 * element.validate();
 * </pre>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see ElementType
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */
public class TransactionGetDto extends DistributedDto {
	private String id;

	@Override
	public void validate() {
		List<String> details = new LinkedList<>();

		// Validate Hash
		if (getId() == null || !getId().matches("^[a-fA-F0-9]{64}$")) {
			details.add("Invalid id: The 'id' field must be a valid 64-character hexadecimal hash.");
		}
		
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
