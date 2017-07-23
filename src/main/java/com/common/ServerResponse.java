package com.common;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@JsonSerialize
public class ServerResponse<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private T data;
	
	public static <T>ServerResponse<T> response(int status, String message, T data){
		return new ServerResponse<T>(status, message, data);
	}
	
	public ServerResponse(int status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
