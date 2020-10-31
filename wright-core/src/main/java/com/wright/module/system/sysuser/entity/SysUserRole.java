package com.wright.module.system.sysuser.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class SysUserRole {
	
	@Id
	@GeneratedValue
	private Integer id; // 编号0271
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private SysUser sysUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private SysRole sysRole;
}
