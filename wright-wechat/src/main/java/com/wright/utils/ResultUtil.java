package com.wright.utils;

public class ResultUtil {
	public static <T> RestResult<T> success(T data) {
		return commonResult(true, 200, "请求成功", data);
	}

	public static <T> RestResult<T> error(String errorMsg) {
		return error(200, errorMsg);
	}

	public static <T> RestResult<T> error(int code, String errorMsg) {
		return commonResult(false, code, errorMsg, null);
	}

	private static <T> RestResult<T> commonResult(boolean success, int code, String errMsg, T data) {
		RestResult<T> result = new RestResult<>();
		result.setSuccess(success);;
		result.setCode(code);
		result.setMessage(errMsg);
		result.setData(data);
		return result;
	}

}
