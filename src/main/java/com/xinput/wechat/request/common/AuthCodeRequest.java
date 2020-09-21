package com.xinput.wechat.request.common;

/**
 * 用户唯一标识 OpenID 和 会话密钥 session_key
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 15:26
 */
public class AuthCodeRequest {

    /**
     * 微信分配的应用Id
     */
    private String appid;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 登录时获取的 code，前端获取
     */
    private String code;

    /**
     * 授权类型，此处只需填写 authorization_code
     */
    private final String grant_type = "authorization_code";

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }
}
