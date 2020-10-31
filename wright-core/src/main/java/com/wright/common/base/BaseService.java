package com.wright.common.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.wright.common.exception.LogicException;
import com.wright.utils.BeanUtils;
/**
 * 
 * 
 * 	基本服务类，用于各种服务继承，可以在服务中调用 queryFactory用以复杂数据库操作
 *
 * @author WRIGHT
 *
 */
public class BaseService {
	@Autowired
	@PersistenceContext
	protected EntityManager entityManager;

	protected JPAQueryFactory queryFactory;

	@PostConstruct
	public void init() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	public <T> boolean updateRecords(T entity) {
		boolean flag = false;
		try {
			Method method = entity.getClass().getMethod("getId", new Class[] {});
			 Object value = method.invoke(entity, new Object[] {});
			Object o = entityManager.find(entity.getClass(), value);
			BeanUtils.copyPropertiesExcludeNull(entity, o);
			entityManager.merge(o);
			flag = true;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LogicException("更新记录中未找到ID字段");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LogicException("更新失败"+e.getLocalizedMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LogicException("更新失败"+e.getLocalizedMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LogicException("更新失败"+e.getLocalizedMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LogicException("更新失败"+e.getLocalizedMessage());
		}

		return flag;
	}
}
