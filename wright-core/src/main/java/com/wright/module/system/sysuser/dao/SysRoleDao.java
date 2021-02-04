package com.wright.module.system.sysuser.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wright.module.system.sysuser.entity.SysRole;





public interface SysRoleDao extends JpaSpecificationExecutor<SysRole>,JpaRepository <SysRole, Integer> {
    public Page<SysRole> findAll(Specification<SysRole> specification,Pageable pageable);
}