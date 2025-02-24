package org.norsh.model.transport;

import org.norsh.exceptions.OperationStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public RestMethod getMethod() {
		return method;
	}

	public void setMethod(RestMethod method) {
		this.method = method;
	}

	public String getRequestClassName() {
		return requestClassName;
	}

	public void setRequestClassName(String requestClassName) {
		this.requestClassName = requestClassName;
	}

	public Object getRequestData() {
		return requestData;
	}

	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}
}
