package com.wright.system.sysuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wright.system.sysuser.entity.SysUser;





public interface SysUserRespository extends JpaSpecificationExecutor<SysUser>,JpaRepository <SysUser, Integer> {
	SysUser findByUserName(String userName);
	
	SysUser findByPhone(String phone);
   // void deleteByUserId(String userId);
//    public Page<SysUser> findAll(Specification<SysUser> specification,Pageable pageable);
}