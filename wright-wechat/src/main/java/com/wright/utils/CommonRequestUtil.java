package com.wright.utils;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wright.config.RequestUrl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommonRequestUtil {

	public static String jscode2session(String appid,String secret,String code,String grantType)throws Exception{
        String tokenUrl = RequestUrl.WECHAT_JS_CODE_2_SESSION.getUrl();
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("appid", appid)
                .add("secret", secret)
                .add("js_code",code)
                .add("grant_type", grantType)
                .build();

        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(body)
                .build();
        
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map tokenMap = objectMapper.readValue(result,Map.class);
//            String accessToken = tokenMap.get("access_token").toString();
//            Claims claims = Jwts.parser()
//                    .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
//                    .parseClaimsJws(accessToken)
//                    .getBody();
//            String userName = claims.get("user_name").toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	 /**
     * 获取微信接口调用凭证
     * @param appid
     * @param secret
     * @return 返回String 可转JSON
     */
    public static String getAccessToken(String appid,String secret){
    	String tokenUrl = RequestUrl.WECHAT_JS_CODE_2_SESSION.getUrl();
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credential")
                .add("appid", appid)
                .add("secret",secret)
                .build();

        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(body)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
	@SuppressWarnings("unchecked")
	public static Map<String,Object> authToken(String username,String password) {
    	Map<String,Object> r = new HashMap<String,Object>();
    	String tokenUrl = RequestUrl.AUTH_TOKEN.getUrl();
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", username)
                .add("password",password)
                .add("scope","all")
                .build();

        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(body)
                .addHeader("Authorization", "Basic dXNlci1jbGllbnQ6dXNlci1zZWNyZXQtODg4OA==")
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> tokenMap = objectMapper.readValue(result,Map.class);
            Object error = tokenMap.get("error");
            if(null!=error&&!"".equals(error.toString()))
            {
            	r.put("result", "0");
            	r.put("msg", tokenMap.get("error_description").toString());
            }
            else
            {
            	Claims jinfo = Jwts.parser().setSigningKey("jxwright1999".getBytes("UTF-8")).parseClaimsJws(tokenMap.get("access_token").toString()).getBody();
            	tokenMap.put("userId",jinfo.get("userId"));
            	r.put("result", "1");
            	r.put("msg", tokenMap);
            	
            	
            }
            System.out.println(result);
        } catch (Exception e) {
        	r.put("result", "0");
        	r.put("msg", e.getLocalizedMessage());
        }
        return r;
    }
    
    @SuppressWarnings("rawtypes")
    public static Map<String,Object> refreshToken(String refreshToken) {
    	Map<String,Object> r = new HashMap<String,Object>();
    	String tokenUrl = RequestUrl.AUTH_TOKEN.getUrl();
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "refresh_token")
                .add("refresh_token", refreshToken)
                .build();

        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(body)
                .addHeader("Authorization", "Basic dXNlci1jbGllbnQ6dXNlci1zZWNyZXQtODg4OA==")
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            Map tokenMap = objectMapper.readValue(result,Map.class);
            Object error = tokenMap.get("error");
            if(null!=error&&!"".equals(error.toString()))
            {
            	r.put("result", "0");
            	r.put("msg", tokenMap.get("error_description").toString());
            }
            else
            {
            	r.put("result", "1");
            	r.put("msg", tokenMap);
            }
            System.out.println(result);
        } catch (Exception e) {
        	r.put("result", "0");
        	r.put("msg", e.getLocalizedMessage());
        }
        return r;
    }
}
