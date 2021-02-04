package com.wright.utils;

public class RestResultGenerator {
	 
	public static <T> ResponseResult<T> genResult(T data,String message){
		
		
		ResponseResult<T> result = new ResponseResult<T>();
		result.setData(data);
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
}
