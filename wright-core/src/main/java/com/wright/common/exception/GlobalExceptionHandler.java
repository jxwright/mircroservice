package com.wright.common.exception;

import java.net.ConnectException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.wright.utils.RestResult;





@RestControllerAdvice(annotations={RestController.class,Controller.class})
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 请求参数类型错误异常的捕获
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={BindException.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public RestResult<String> badRequest(BindException e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new RestResult<>(false, ErrorCode.BAD_REQUEST);
	}
 
	/**
	 * 404错误异常的捕获
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={NoHandlerFoundException.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public RestResult<String> badRequestNotFound(BindException e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new RestResult<>(false, ErrorCode.NOT_FOUND);
	}
 
	/**
	 * 自定义异常的捕获
	 * 自定义抛出异常。统一的在这里捕获返回JSON格式的友好提示。
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value={LogicException.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> RestResult<T> sendError(LogicException exception,HttpServletRequest request){
		String requestURI = request.getRequestURI();
		logger.error("occurs error when execute url ={} ,message {}",requestURI,exception.getMessage());
		return new RestResult<>(false,exception.getCode(),exception.getMessage());
	}
	/**
	 * 数据库操作出现异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={SQLException.class,DataAccessException.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public RestResult<String> systemError(Exception e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new RestResult<>(false, ErrorCode.DATABASE_ERROR);
	}
	/**
	 * 网络连接失败！
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={ConnectException.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public RestResult<String> connect(Exception e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new RestResult<>(false, ErrorCode.CONNECTION_ERROR);
	}
 
	@ExceptionHandler(value={Exception.class})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public RestResult<String> notAllowed(Exception e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new RestResult<>(false,-1,e.toString());
	}

}
