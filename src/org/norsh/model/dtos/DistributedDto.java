package org.norsh.model.dtos;

import org.norsh.exceptions.OperationException;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract DTO for message serialization and identification in distributed systems.
 * <p>
 * This class provides a unique identifier for DTOs, ensuring correct serialization and deserialization within
 * distributed architectures such as Kafka, message queues, or event-driven microservices.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 * <li>Identifies each DTO type with a unique class name.</li>
 * <li>Facilitates proper serialization and deserialization in distributed environments.</li>
 * <li>Ensures accurate message classification in Kafka and other event-driven systems.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <p>
 * Any DTO intended for processing in distributed queues should extend this class.
 * </p>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 * @see <a href="https://docs.norsh.org">Norsh Documentation</a>
 */

@Getter
@Setter
public class DistributedDto {
	private String requestId;
	
	public void validate() throws OperationException {}
	
	
}