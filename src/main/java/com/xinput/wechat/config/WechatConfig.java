package com.xinput.wechat.config;

import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.SimpleProperties;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechat.consts.WechatConsts;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 12:55
 */
public class WechatConfig {

    private static final Logger logger = Logs.get();

    public static SimpleProperties SP;

    private static final String CONFIG_FILE = WechatConsts.DEFAULT_SYSTEM_FILE;

    static {
        try {
            SP = SimpleProperties.readConfiguration(CONFIG_FILE);
        } catch (Exception e) {
            SP = null;
            logger.error("read file: [{}] exception.", CONFIG_FILE, e);
            throw e;
        }
    }

    /**
     * 获取微信小程序id
     */
    private static String wechatAppid;

    /**
     * 获取微信小程序 secret
     */
    private static String wechatSecret;

    /**
     * 获取微信API密钥
     */
    private static String wechatApiKey;

    /**
     * 获取商户号
     */
    private static String wechatMchId;

    /**
     * 获取证书存放地址
     */
    private static String wechatApiCertPath;

    /**
     * 微信支付api请求超时时间
     */
    private static Integer wechatTimeout;

    /**
     * 是否使用沙箱环境，默认是，如果想使用正式环境，设置 wechat.use.sandbox=false 即可
     */
    private static Boolean useSandbox;


    public static String getWechatAppid() {
        if (StringUtils.isNullOrEmpty(wechatAppid)) {
            wechatAppid = get(WechatConsts.WECHAT_APPID);
        }

        return wechatAppid;
    }

    public static String getWechatSecret() {
        if (StringUtils.isNullOrEmpty(wechatSecret)) {
            wechatSecret = get(WechatConsts.WECHAT_SECRET);
        }
        return wechatSecret;
    }

    public static String getWechatApiKey() {
        if (StringUtils.isNullOrEmpty(wechatApiKey)) {
            wechatApiKey = get(WechatConsts.WECHAT_API_KEY);
        }
        return wechatApiKey;
    }

    public static String getWechatMchId() {
        if (StringUtils.isNullOrEmpty(wechatMchId)) {
            wechatMchId = get(WechatConsts.WECHAT_MCH_ID);
        }
        return wechatMchId;
    }

    public static String getWechatApiCertPath() {
        if (StringUtils.isNullOrEmpty(wechatApiCertPath)) {
            wechatApiCertPath = get(WechatConsts.WECHAT_API_CERT_PATH);
        }
        return wechatApiCertPath;
    }

    public static int getWechatTimeout() {
        if (wechatTimeout == null) {
            wechatTimeout = SP.getIntProperty(WechatConsts.WECHAT_TIMEOUT, 5);
        }
        return wechatTimeout;
    }

    public static boolean getUseSandbox() {
        if (null == useSandbox) {
            useSandbox = SP.getBooleanProperty(WechatConsts.WECHAT_USE_SANDBOX, false);
        }

        return useSandbox;
    }


    /**
     * 获取自定义key对应的value,如果不存在，使用默认值 defaultValue
     *
     * @param key
     * @return
     */
    public static final String get(String key) {
        return SP.getStringProperty(key);
    }
}
