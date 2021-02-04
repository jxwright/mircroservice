package com.wright.config.aop;

import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before; 
import org.aspectj.lang.annotation.Pointcut; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Component; 
import org.springframework.web.context.request.RequestContextHolder; 
import org.springframework.web.context.request.ServletRequestAttributes; 
import javax.servlet.http.HttpServletRequest; 
import java.util.Arrays;

@Aspect
@Component
public class RunTimeAopConfig {
	private Logger logger = LoggerFactory.getLogger(RunTimeAopConfig.class);
	
	//定义切点方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。 
	@Pointcut("execution(* com..service.*Service*.*(..))") 
	public void qieru(){		
	} 
	//在切点前执行以下代码
	@Before("qieru()") 
	public void doBefore(JoinPoint joinPoint){ 
		System.out.println("前置通知"); 
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
//		HttpServletRequest request = attributes.getRequest(); 
//		// 记录下请求内容
//		logger.info("URL : " + request.getRequestURL().toString()); 
//		logger.info("HTTP_METHOD : " + request.getMethod()); 
//		logger.info("IP : " + request.getRemoteAddr()); 
//		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()); 
//		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		} 
	@After("qieru()") 
	public void doAfter(JoinPoint joinPoint){ 
		System.out.println("后置通知"+ joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		} 
	@AfterReturning("qieru()") 
	public void doAfterReturning(){ 
		System.out.println("后置返回 "); 
		} 
	@AfterThrowing("qieru()") 
	public void doAfterThrowing(){ 
		System.out.println("后置异常"); 
		} 
//	@Around("qieru()") 
//	public void doAround(ProceedingJoinPoint poin) throws Throwable { 
//		System.out.println("环绕通知"+logger.getClass().getName()); 
//		poin.proceed(); 
//	System.out.println("环绕通知后"+logger.getClass().getName()); 
//		}

}
