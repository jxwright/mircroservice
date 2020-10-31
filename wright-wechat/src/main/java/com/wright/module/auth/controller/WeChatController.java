package com.wright.module.auth.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wright.utils.AesCbcUtil;
import com.wright.utils.CommonRequestUtil;
import com.wright.utils.RestResult;
import com.wright.utils.ResultUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestController
public class WeChatController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings("rawtypes")
	@GetMapping("/login")
	public String login(String code) throws Exception {
		String s = CommonRequestUtil.jscode2session("wx9f444e34417276f2", "b34118dada7824ae3eb80f417a292608", code,
				"authorization_code");

		System.out.println(s);
		ObjectMapper objectMapper = new ObjectMapper();
		Map tokenMap = objectMapper.readValue(s, Map.class);
		redisTemplate.opsForHash().put("weChatSessionKey", tokenMap.get("openid"), tokenMap.get("session_key"));
		// SysUserApi.isExistUser();
		return s;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public RestResult<?> loginPost(@RequestBody Map<String, Object> params) throws Exception {
		String encryptedData = params.get("encryptedData").toString();
		String iv = params.get("iv").toString();
		String code = params.get("code").toString();
		String phone = params.get("phone").toString();
		String password = params.get("password").toString();

		String s = CommonRequestUtil.jscode2session("wx9f444e34417276f2", "b34118dada7824ae3eb80f417a292608", code,
				"authorization_code");

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(s);
		ObjectMapper objectMapper = new ObjectMapper();
		Map tokenMap = objectMapper.readValue(s, Map.class);
		redisTemplate.opsForHash().put("weChatSessionKey", tokenMap.get("openid"), tokenMap.get("session_key"));

		Map<String, Object> authtoken = CommonRequestUtil.authToken(phone, password);

		String tokenResult = String.valueOf(authtoken.get("result"));
		if ("0".equals(tokenResult)) {
			return ResultUtil.error(String.valueOf(authtoken.get("msg")));
		}
		map.put("tokenKey", authtoken.get("msg"));
		try {
			String result = AesCbcUtil.decrypt(encryptedData, tokenMap.get("session_key").toString(), iv, "UTF-8");
			if (null != result && result.length() > 0) {
				Map userInfo = objectMapper.readValue(result, Map.class);
				// 解密unionId & openId;
				map.put("userInfo", userInfo);
				return ResultUtil.success(map);
			} else {
				return ResultUtil.error("用户验证解密失败，微信登录失效，稍后重试");
			}
		} catch (Exception e) {
			return ResultUtil.error(e.getLocalizedMessage());
		}
	}

	@PostMapping("/refreshToken")
	public RestResult<?> refreshToken(@RequestBody Map<String, Object> params) throws Exception {
		String refreshToken = params.get("refreshToken").toString();
		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> authtoken = CommonRequestUtil.refreshToken(refreshToken);

		String tokenResult = String.valueOf(authtoken.get("result"));
		if ("0".equals(tokenResult)) {
			return ResultUtil.error(String.valueOf(authtoken.get("msg")));
		}
		map.put("tokenKey", authtoken.get("msg"));
		return ResultUtil.success(map);
	}


	@GetMapping(value = "get")
	public Object get(String value) {
		return new BCryptPasswordEncoder().encode(value);
	}

	@ResponseBody
	@GetMapping(value = "get2")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Object get2(Authentication authentication) throws Exception {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		authentication.getCredentials();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
		String token = details.getTokenValue();
		
		return Jwts.parser().setSigningKey("jxwright1999".getBytes("UTF-8")).parseClaimsJws(token.toString()).getBody();
	}

}
