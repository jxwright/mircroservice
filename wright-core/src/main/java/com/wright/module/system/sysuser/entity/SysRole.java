package com.wright.module.system.sysuser.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SysRole {
	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id;
	    private String roleName; // 角色标识程序中判断使用,如"admin",这个是唯一的:
	    private String roleType; // 角色描述,UI界面显示使用
	    private String roleLevel; // 角色描述,UI界面显示使用
	    private String description; // 角色描述,UI界面显示使用
	   
	    //角色 -- 权限关系：多对多关系;
	   // @JsonIgnore
	    @ManyToMany(fetch= FetchType.EAGER)
	    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
	    private List<SysPermission> permissions;

	    // 用户 - 角色关系定义;
	    @JsonIgnore
	    @ManyToMany
	    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
	    private List<SysUser> userList;// 一个角色对应多个用户	    

}
