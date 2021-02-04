package com.wright.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.wright.api.SysUserApi;
import com.wright.module.system.sysuser.entity.SysUser;
import com.wright.module.system.sysuser.repository.SysUserRepository;

@DubboService
public class SysUserApiImpl   implements SysUserApi{

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Override
	public boolean isExistUser() {
		SysUser user2 = new SysUser();
		user2.setUserName("wright");
		sysUserRepository.save(user2);
		return false;
	}

	@Override
	public boolean saveUser(JSONObject user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONObject findUserByUsername(String username) {
		return  JSONObject.parseObject(JSONObject.toJSONString(sysUserRepository.findByUserName(username)));
	}
	
	

}
