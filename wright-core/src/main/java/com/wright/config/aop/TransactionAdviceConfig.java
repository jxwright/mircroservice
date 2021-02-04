package com.wright.config.aop;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
/**
 * @author wright
 * @description 通过AOP切面设置全局事务，拦截service包下面所有方法
 * AOP术语：通知（Advice）、连接点（Joinpoint）、切入点（Pointcut)、切面（Aspect）、目标(Target)、代理(Proxy)、织入（Weaving）
 */
@Aspect
@Configuration
public class TransactionAdviceConfig  {
    
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com..service.*Service*.*(..))";

	@Autowired
	private TransactionManager transactionManager;
	@Bean
	public TransactionInterceptor txAdvice() {
 		/*事务管理规则，声明具备事务管理的方法名*/
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		/* 当前没有事务：只有select，非事务执行；有update，insert，delete操作，自动提交；
		 * 当前有事务：如果有update，insert，delete操作，支持当前事务 
		 * 只读事物、不做更新删除等
		 * 当前存在事务就用当前的事务，当前不存在事务就创建一个新的事务*/
		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setReadOnly(true);
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

		/* 当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务 */
		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
		 /*抛出异常后执行切点回滚*/
		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		/*PROPAGATION_REQUIRED:事务隔离性为1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 */
		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		source.addTransactionalMethod("add*", requiredTx);
		source.addTransactionalMethod("save*", requiredTx);
		source.addTransactionalMethod("insert*", requiredTx);
		source.addTransactionalMethod("delete*", requiredTx);
		source.addTransactionalMethod("update*", requiredTx);
		source.addTransactionalMethod("exec*", requiredTx);
		source.addTransactionalMethod("set*", requiredTx);
		source.addTransactionalMethod("get*", readOnlyTx);
		source.addTransactionalMethod("query*", readOnlyTx);
		source.addTransactionalMethod("find*", readOnlyTx);
		source.addTransactionalMethod("list*", readOnlyTx);
		source.addTransactionalMethod("count*", readOnlyTx);
		source.addTransactionalMethod("is*", readOnlyTx);
		return new TransactionInterceptor(transactionManager, source);
	}

	@Bean
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}

}
