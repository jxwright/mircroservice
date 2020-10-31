package com.wright.module.system.sysuser.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.querydsl.core.QueryResults;

import com.wright.common.base.BaseService;
import com.wright.module.system.sysuser.dao.SysRoleDao;
import com.wright.module.system.sysuser.entity.QSysRole;
import com.wright.module.system.sysuser.entity.SysRole;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;

@Service
public class SysRoleService  extends BaseService {
     @Autowired 
     private SysRoleDao sysRoleDao; 
     
    /**
     * * Spring boot开启事务只需要添加@Transactional即可！ *
     * 
     * @throws Exception
     */
    public Page<SysRole> findAll(SysRole SysRole, Pageable pageable) {
		Page<SysRole> page = sysRoleDao.findAll(
				(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
					List<Predicate> predicates = new ArrayList<Predicate>();
				//	if (null != SysRole.getName() && !"".equals(SysRole.getName()))
				//	predicates.add(cb.like(root.get("name").as(String.class), "%"+SysRole.getName() + "%"));
				//	if (null != SysRole.getPhone() && !"".equals(SysRole.getPhone()))
			//		predicates.add(cb.like(root.get("phone").as(String.class), "%"+SysRole.getPhone() + "%"));
				//	predicates.add(cb.equal(root.get("isValid").as(String.class), SysRole.getIsValid()));
					
//					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//					try {
//						if (null != SysRole.getCreateTimeStart() && !"".equals(SysRole.getCreateTimeStart()))
//							predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
//									f.parse(SysRole.getCreateTimeStart())));
//						if (null != SysRole.getCreateTimeEnd() && !"".equals(SysRole.getCreateTimeEnd()))
//							predicates.add(cb.lessThan(root.get("createTime").as(Date.class),
//									new Date(f.parse(SysRole.getCreateTimeEnd()).getTime() + 24 * 3600 * 1000)));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}, pageable);
		return page;
 
	}
    
	/**
	 * 分页查询所有的实体,根据name字段排序
	 *
	 * @return
	 */
	public QueryResults<SysRole> findAllPage(Pageable pageable) {
		QSysRole user = QSysRole.sysRole;
		return queryFactory.selectFrom(user).orderBy(user.roleName.asc()).offset(pageable.getOffset()) // 起始页
				.limit(pageable.getPageSize()) // 每页大小
				.fetchResults(); // 获取结果，该结果封装了实体集合、分页的信息，需要这些信息直接从该对象里面拿取即可
	}

    }
