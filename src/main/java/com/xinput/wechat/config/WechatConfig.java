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
    public static String getWechatAppid() {
        return get(WechatConsts.WECHAT_APPID);
    }

    /**
     * 获取微信小程序 secret
     */
    public static String getWechatSecret() {
        return get(WechatConsts.WECHAT_SECRET);
    }

    /**
     * 获取微信API密钥
     */
    public static String getWechatApiKey() {
        return get(WechatConsts.WECHAT_API_KEY);
    }

    /**
     * 获取商户号
     */
    public static String getWechatMchId() {
        return get(WechatConsts.WECHAT_MCH_ID);
    }

    /**
     * 获取证书存放地址
     */
    public static String getWechatApiCertPath() {
        return get(WechatConsts.WECHAT_API_CERT_PATH);
    }

    /**
     * 微信支付api请求超时时间
     */
    public static int getWechatTimeout() {
        return SP.getIntProperty(WechatConsts.WECHAT_TIMEOUT, 5);
    }

    /**
     * 获取自定义key对应的value,如果不存在，使用默认值 defaultValue
     *
     * @param key
     * @return
     */
    public static final String get(String key) {
        String value = SP.getStringProperty(key);
        if (StringUtils.isNullOrEmpty(value)) {
            logger.error("key : [{}] not exist.", key);
        }
        return value;
    }
}
