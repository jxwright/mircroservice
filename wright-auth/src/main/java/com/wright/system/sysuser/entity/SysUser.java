package com.wright.system.sysuser.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wright.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@NamedQueries({
	@NamedQuery(name=SysUser.FIND_SINGER_BY_ID,query="select distinct s from SysUser s where s.id=:id")
})
public class SysUser extends BaseEntity {

	
	public static final String FIND_SINGER_BY_ID = "SysUser.findById";
	
	private static final long serialVersionUID = -1292846506294441280L;
	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id;
	
    @Column(unique =true)
    private String userName;//帐号
    private String realName;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String emplId;//加密密码的盐
    private String useType;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
  
    private String  userLevel;//描述
    private byte  isActive;//手机
    private byte  isValid;//审核原因
    private byte  pwUnlimit;
    private byte  pwReadonly;
    private String  description;
    
    private String  userAvatar;
   
    @Column(unique =true)
    private String  phone;
    
    
    @JsonIgnore
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    private List<SysRole> roleList;// 一个用户具有多个角色
    
	
    
}
