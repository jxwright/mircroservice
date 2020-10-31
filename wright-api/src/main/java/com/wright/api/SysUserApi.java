package com.wright.api;

import com.alibaba.fastjson.JSONObject;

public interface SysUserApi {

	boolean isExistUser();
	
	boolean saveUser(JSONObject user);
	
	JSONObject findUserByUsername(String username);
	
}
