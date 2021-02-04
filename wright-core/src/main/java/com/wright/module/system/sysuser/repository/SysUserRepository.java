package com.wright.module.system.sysuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.wright.module.system.sysuser.entity.SysUser;





public interface SysUserRepository  extends JpaRepository<SysUser, Integer>, QuerydslPredicateExecutor<SysUser> {
			
	SysUser findByUserName(String userName);
	
	 @Query("select s from SysUser s where s.userName =:name" )
	 List<SysUser> getShopName(@Param("name") String name);
	 
		//nativeQuery为true时定义为直接将SQL语句放于数据库中执行
	 @Query(value="select * from sys_user where user_name like %:name% ",nativeQuery = true ) 
	 List<SysUser> selectShopName(@Param("name") String name);
}
