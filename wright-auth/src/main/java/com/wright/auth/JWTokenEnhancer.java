package com.wright.auth;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.wright.system.sysuser.entity.SysUser;
import com.wright.system.sysuser.repository.SysUserRespository;

public class JWTokenEnhancer implements TokenEnhancer {

	
	@Autowired
	private SysUserRespository sysUserRespository;
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
    	User user = (User) oAuth2Authentication.getPrincipal();
    	Map<String, Object> info = new HashMap<>();
    	SysUser s = sysUserRespository.findByPhone(user.getUsername());
        info.put("userId", s.getId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
