package com.xinput.wechat;

import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.http.SimpleHttpUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.consts.WechatConsts;
import com.xinput.wechat.exception.WechatException;
import com.xinput.wechat.request.common.AuthCodeRequest;
import com.xinput.wechat.response.common.AuthCodeResponse;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * 微信统一接口
 * 登录
 * 接口调用凭证
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 12:29
 */
public class WechatApi {

    private static final Logger logger = Logs.get();

    private final static String wxUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=#{3}";

    /**
     * 获取用户openId
     *
     * @param code 临时登录凭证code
     * @return
     */
    public static AuthCodeResponse code2Session(final String code) {
        if (StringUtils.isNullOrEmpty(WechatConfig.getWechatAppid()) || StringUtils.isNullOrEmpty(WechatConfig.getWechatSecret())) {
            throw new WechatException(String.format("[%s] or [%s] not found.", WechatConsts.WECHAT_APPID, WechatConfig.getWechatSecret()));
        }

        String requestUrl = MessageFormat.format(
                wxUrl,
                WechatConfig.getWechatAppid(),
                WechatConfig.getWechatSecret(),
                code,
                "authorization_code");
        return code2Session(requestUrl, code);
    }

    /**
     * 获取用户openId
     *
     * @param appId     微信应用Id
     * @param secretKey 微信应用密钥
     * @param code      获取用户openId
     * @return
     */
    public static AuthCodeResponse code2Session(final String appId, final String secretKey, final String code) {
        String requestUrl = MessageFormat.format(wxUrl, appId, secretKey, code, "authorization_code");
        return code2Session(requestUrl, code);
    }

    /**
     * 获取用户openId
     *
     * @param url  已准备就绪的url
     * @param code 获取用户openId
     * @return
     */
    public static AuthCodeResponse code2Session(final String url, final String code) {
        AuthCodeResponse authInfo = null;
        try {
            String result = SimpleHttpUtils.get(url);
            logger.info("code2session信息:[{}]", result);
            authInfo = JsonUtils.toBean(result, AuthCodeResponse.class);
        } catch (ClientProtocolException e) {
            logger.error("登录凭证校验失败,code:[{}].", code, e);
        } catch (IOException e) {
            logger.error("登录凭证校验失败,code:[{}]", code, e);
        }

        return authInfo;
    }

    /**
     * 获取用户openId
     *
     * @param authCodeRequest
     * @return
     */
    public static AuthCodeResponse code2Session(final AuthCodeRequest authCodeRequest) {
        String requestUrl = MessageFormat.format(wxUrl, authCodeRequest.getAppid(), authCodeRequest.getAppSecret(), authCodeRequest.getCode(), authCodeRequest.getGrant_type());
        return code2Session(requestUrl, authCodeRequest.getCode());
    }
}
