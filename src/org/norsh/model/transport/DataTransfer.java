package org.norsh.model.transport;

import org.norsh.exceptions.OperationStatus;
import org.norsh.rest.RestMethod;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing the status of a request in the Norsh ecosystem.
 * <p>
 * This class encapsulates the request ID, its status, and optional response data.
 * </p>
 *
 * <h2>Fields:</h2>
 * <ul>
 *   <li>{@link #requestId} - Unique identifier of the request.</li>
 *   <li>{@link #status} - The current status of the request.</li>
 *   <li>{@link #requestData} - Optional data related to the request response.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Danthur Lice
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DataTransfer {
	/** Unique identifier of the request. */
	private String requestId;

	private RestMethod method;

	private String requestClassName;

	private Object requestData;

	private Object responseData;
	
	private String message;
	
	/** The current status of the request. */
	private OperationStatus status;

	/**
	 * Converts this DTO to a response format by removing internal fields 
	 * that should not be exposed in API responses.
	 *
	 * @return A new {@link DataTransfer} instance without the `className` field.
	 */
	public DataTransfer toResponse() {
		return new DataTransfer(this.requestId, this.status, this.responseData);
	}

	public DataTransfer(String requestId, RestMethod method, Object requestData) {
		this.requestId = requestId;
		this.method = method;
		this.requestData = requestData;
		this.requestClassName = requestData.getClass().getCanonicalName();
		this.status = OperationStatus.CREATED;
	}

	public DataTransfer(String requestId, OperationStatus status) {
		this.requestId = requestId;
		this.status = status;
	}

	public DataTransfer(String requestId, OperationStatus status, Object responseData) {
		this.requestId = requestId;
		this.status = status;
		this.responseData = responseData;
	}
}
