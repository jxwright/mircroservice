package com.wright.common.exception;

public enum ErrorCode {
	SYSTEM_ERROR(-1001,"系统异常"),
	BAD_REQUEST(-1002,"错误的请求参数"),
	NOT_FOUND(-1003,"找不到请求路径！"),
	CONNECTION_ERROR(-1004,"网络连接请求失败！"),
	METHOD_NOT_ALLOWED(-1005,"不合法的请求方式"),
	DATABASE_ERROR(-1004,"数据库异常"),
	BOUND_STATEMENT_NOT_FOUNT(-1006,"找不到方法！"),
	NO_PROCESSINSTANCE_FOUND(-1026,"未找到相应的流程实例");
 
	private int code;
	private String msg;
	private ErrorCode(int code, String msg) {
 
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
