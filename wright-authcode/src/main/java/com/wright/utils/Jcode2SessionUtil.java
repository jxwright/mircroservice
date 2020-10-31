package com.wright.utils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wright.config.WeChatUrl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Jcode2SessionUtil {

	public static String jscode2session(String appid,String secret,String code,String grantType)throws Exception{
        String tokenUrl = WeChatUrl.JS_CODE_2_SESSION.getUrl();
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
            ObjectMapper objectMapper = new ObjectMapper();
            Map tokenMap = objectMapper.readValue(result,Map.class);
            String accessToken = tokenMap.get("access_token").toString();
            Claims claims = Jwts.parser()
                    .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(accessToken)
                    .getBody();
            String userName = claims.get("user_name").toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
