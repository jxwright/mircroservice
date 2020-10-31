package com.wright.config;

public enum  RequestUrl {
	WECHAT_JS_CODE_2_SESSION("https://api.weixin.qq.com/sns/jscode2session"),
    WECHAT_GET_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token"),
    WECHAT_SEND_TEMPLATE_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send"),
    AUTH_TOKEN("http://localhost:8088/oauth/token");

    private String url;

    RequestUrl() {
    }

    RequestUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public RequestUrl setUrl(String url) {
        this.url = url;
        return this;
    }
}
