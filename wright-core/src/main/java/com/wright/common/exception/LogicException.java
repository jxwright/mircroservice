package com.wright.common.exception;



public class LogicException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	protected int code;

	public LogicException(ErrorCode enums) {
		super(enums.getMsg());
		this.code = enums.getCode();

	}
	public LogicException(String message) {
		super(message);
	}
 
	public int getCode() {
		return code;
	}
 
 
	public void setCode(int code) {
		this.code = code;
	} 
 
	public LogicException() {
		super();
	}
 
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
 

}
