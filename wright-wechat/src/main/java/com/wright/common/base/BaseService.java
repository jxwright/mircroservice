package com.wright.common.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.wright.common.exception.LogicException;
import com.wright.utils.BeanUtils;
import com.wright.utils.Page;
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
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getDataList(String sql,String dto)
	{
		Query query = entityManager.createNativeQuery(sql,dto);
		return query.getResultList();
	}
	
	protected  Page getPageDataList(String sql,String dto,int pageNum,int pageSize)
	{
		String tsql = sql.toLowerCase();
		int i1 = tsql.indexOf("from ");
		System.out.println(tsql.substring(i1, tsql.length()));
		String countSql = "select count(1) " +tsql.substring(i1, tsql.length());
		Query cq = entityManager.createNativeQuery(countSql);
		List<?> rows = cq.getResultList(); 
		int c = Integer.parseInt(rows.get(0).toString());
		Query query = entityManager.createNativeQuery(sql,dto)
						.setFirstResult((pageNum-1)*pageSize)
						.setMaxResults((pageNum-1)*pageSize + pageSize); 
		Page p=new Page(pageNum,c,pageSize,query.getResultList());
		return p;
	}
}
