package com.wright.module.system.sysuser.service;


import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;


import com.wright.common.base.BaseService;
import com.wright.module.system.sysuser.dto.UserRoleDTO;
import com.wright.module.system.sysuser.entity.QSysUser;
import com.wright.module.system.sysuser.entity.QSysUserRole;
import com.wright.module.system.sysuser.entity.SysUser;
import com.wright.module.system.sysuser.repository.SysUserRepository;
import com.wright.utils.BeanUtils;

//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.criteria.CriteriaQuery;


@Service
public class SysUserService extends BaseService{
	
	private static Logger logger = LoggerFactory.getLogger(SysUserService.class);
	@Autowired
	private SysUserRepository sysUserRepository;


	/**
	 * * Spring boot开启事务只需要添加@Transactional即可！ *
	 * 
	 * @throws Exception
	 */
	// @Transactional
	public void insertTwo() {
		SysUser user1 = new SysUser();
		user1.setUserName("222");
		sysUserRepository.save(user1);
		// 将数据库字段设置为2个字节，超过就报错
		SysUser user2 = new SysUser();
		user2.setUserName("11111111111111");
		sysUserRepository.save(user2);
	}

	public SysUser findByUsername(String username) {
		return sysUserRepository.findByUserName(username);
	}
/*
	public Page<SysUser> findAll(SysUser sysUser, Pageable pageable) {				
		Page<SysUser> page = sysUserDao.findAll((Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (null != sysUser.getName() && !"".equals(sysUser.getName()))
				predicates.add(cb.like(root.get("name").as(String.class), "%" + sysUser.getName() + "%"));
			if (null != sysUser.getPhone() && !"".equals(sysUser.getPhone()))
				predicates.add(cb.like(root.get("phone").as(String.class), "%" + sysUser.getPhone() + "%"));
			// predicates.add(cb.equal(root.get("isValid").as(String.class),
			// sysUser.getIsValid()));

//					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//					try {
//						if (null != sysUser.getCreateTimeStart() && !"".equals(sysUser.getCreateTimeStart()))
//							predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
//									f.parse(sysUser.getCreateTimeStart())));
//						if (null != sysUser.getCreateTimeEnd() && !"".equals(sysUser.getCreateTimeEnd()))
//							predicates.add(cb.lessThan(root.get("createTime").as(Date.class),
//									new Date(f.parse(sysUser.getCreateTimeEnd()).getTime() + 24 * 3600 * 1000)));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
			return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
		}, pageable);
		return page;

	}
*/
	
	
	public Page<SysUser> findAll(SysUser sysUser, Pageable pageable) {
		QSysUser user = QSysUser.sysUser;
	        //初始化组装条件(类似where 1=1)
	        Predicate predicate = user.isNotNull().or(user.isNull());	        
	        //执行动态条件拼装	        
	        if (null != sysUser.getUserName() && !"".equals(sysUser.getUserName()))
	        	ExpressionUtils.and(predicate,user.userName.like(sysUser.getUserName()));
			if (null != sysUser.getPhone() && !"".equals(sysUser.getPhone()))
				ExpressionUtils.and(predicate,user.phone.eq(sysUser.getPhone()));
	 
	        Page<SysUser> page = sysUserRepository.findAll(predicate, pageable);
	        return page;
	}
	
	/**
	 * 	分页查询所有的实体,根据name字段排序
	 *	queryFactory主要用于对复杂查询语句处理 selectFrom(user)这个例子可以用上述方法执行。
	 * @return
	 */
	public Page<SysUser> findAllCom(SysUser sysUser, Pageable pageable) {
		QSysUser user = QSysUser.sysUser;
		Predicate predicate = user.isNotNull().or(user.isNull());
        
        //执行动态条件拼装
        
        if (null != sysUser.getUserName() && !"".equals(sysUser.getUserName()))
        	predicate = ExpressionUtils.and(predicate,user.userName.like("%"+sysUser.getUserName()+"%"));
		if (null != sysUser.getPhone() && !"".equals(sysUser.getPhone()))
			predicate = ExpressionUtils.and(predicate,user.phone.eq(sysUser.getPhone()));
		
		QueryResults<SysUser> qr = queryFactory.selectFrom(user)
				//.leftJoin(target)//多表查询
				.where(predicate)
				.orderBy(user.userName.asc())
			//	.having(user.id.longValue().max().gt(7))
				.offset(pageable.getOffset()) // 起始页
				.limit(pageable.getPageSize()) // 每页大小
				.fetchResults(); // 获取结果，该结果封装了实体集合、分页的信息，需要这些信息直接从该对象里面拿取即可
		return  new PageImpl<SysUser>(qr.getResults(), pageable,
				qr.getTotal());
	}
	
	 /**
	  * 部分字段映射查询
	  * 投影为UserRes,lambda方式(灵活，类型可以在lambda中修改)
     *
     * @return
     */
    public QueryResults<UserRoleDTO> findAllUserDto(Pageable pageable) {
    	QSysUserRole user = QSysUserRole.sysUserRole;
    	
    //	Predicate predicate = user.isNotNull().or(user.isNull());
    	return queryFactory.select(
    			Projections.bean(
    					UserRoleDTO.class,//返回自定义实体的类型
                		user.sysUser.id.as("userId"),
                        user.sysUser.userName.as("username"),
                        user.sysRole.id.as("roleId"),
                        user.sysRole.roleName.as("roleName")
                        )
                )
                .from(user)
                
               // .where(user.sysUser.name.eq("1"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }
    
    
    public Long updateRecord() {
    	QSysUser user = QSysUser.sysUser;
    	Long id = queryFactory.update(user).set(user.userName, "qqqqq")
    			.where(user.id.eq(1l))
    			.execute();
    	return id;
    }
    
    public Long deleteRecord() {
    	
    	sysUserRepository.deleteById(4);
    	return 1l;
    }
    
    
public SysUser getSysUserById(String  id)
{
	TypedQuery<SysUser> query = entityManager.createNamedQuery(SysUser.FIND_SINGER_BY_ID,SysUser.class);
	query.setParameter("id", id);
	
	SysUser o = query.getSingleResult();
	return o;
}
    public boolean updateRecord2(SysUser user) {
    	if(user.getId()==null)
    	{
    		logger.info("insert SysUser");
    		entityManager.persist(user);
    	}
    	else
    	{	
    		SysUser o = entityManager.find(SysUser.class, user.getId());    	   		
    		BeanUtils.copyPropertiesExcludeNull(user, o);
    		entityManager.merge(o);
    	}
    	return true;
    }
}
