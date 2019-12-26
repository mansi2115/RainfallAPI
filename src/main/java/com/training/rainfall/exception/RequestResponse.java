package com.training.rainfall.exception;

import java.util.Date;

public class RequestResponse {
	
	  private Date timestamp;

	  private Object result;

	  private boolean success;
	  
	  private String errors;
	  
	  private String httpCodeMessage;

	  public RequestResponse(boolean success,Object message,String httpCodeMessage) {

	    super();
	    this.success=success;
	    
	    this.timestamp = new Date();

	    this.result = message;

	    this.httpCodeMessage=httpCodeMessage;

	  }

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errors
	 */
	public String getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(String errors) {
		this.errors = errors;
	}

	/**
	 * @return the httpCodeMessage
	 */
	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	/**
	 * @param httpCodeMessage the httpCodeMessage to set
	 */
	public void setHttpCodeMessage(String httpCodeMessage) {
		this.httpCodeMessage = httpCodeMessage;
	}

	
	}