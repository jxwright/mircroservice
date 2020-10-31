package com.wright.module.system.sysuser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wright.module.system.sysuser.entity.SysUser;






public interface SysUserDao extends JpaSpecificationExecutor<SysUser>,JpaRepository <SysUser, Integer> {
    SysUser findByUserName(String userName);
   // void deleteByUserId(String userId);
//    public Page<SysUser> findAll(Specification<SysUser> specification,Pageable pageable);
}