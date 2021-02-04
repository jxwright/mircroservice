package com.wright.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wright.system.sysuser.entity.SysRole;
import com.wright.system.sysuser.entity.SysUser;
import com.wright.system.sysuser.repository.SysUserRespository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthUserDetailsService implements UserDetailsService {
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	 private SysUserRespository sysUserRespository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 log.info("usernameis:" + username);
		
		 log.info("usernameis:" + username);
		 SysUser user = sysUserRespository.findByPhone(username);
	        // 查询数据库操作
		 	if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }else{
	        	List<SysRole> roles =user.getRoleList();
	            // 用户角色也应在数据库中获取
	            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	            for(SysRole r:roles)
	        	{
	            	 authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
	        	}
	            
	            // 线上环境应该通过用户名查询数据库获取加密后的密码
	            String password = user.getPassword();
	            return new org.springframework.security.core.userdetails.User(username,password, authorities);
	        }
	}

}
