package com.xinput.wechat.config;

import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.SimpleProperties;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechat.enums.WechatConfigEnum;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 12:55
 */
public class WechatConfig {

    private static final Logger logger = Logs.get();

    /**
     * 默认配置文件名称
     */
    private static final String DEFAULT_CONFIG_FILE = "system.properties";

    private static SimpleProperties SP;

    static {
        try {
            SP = SimpleProperties.readConfiguration(DEFAULT_CONFIG_FILE);
        } catch (Exception e) {
            SP = null;
            logger.error("read file: [{}] exception.", DEFAULT_CONFIG_FILE, e);
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
     * 微信支付通知回调 - 下单
     */
    private static String wechatNotifyUnifiedOrderUrl;

    /**
     * 微信支付通知回调 - 退款
     */
    private static String wechatNotifyRefundUrl;

    /**
     * 是否使用沙箱环境，默认是，如果想使用正式环境，设置 wechat.use.sandbox=false 即可
     */
    private static Boolean useSandbox;


    public static String getWechatAppid() {
        if (StringUtils.isNullOrEmpty(wechatAppid)) {
            wechatAppid = get(WechatConfigEnum.WECHAT_APPID.getKey());
        }

        return wechatAppid;
    }

    public static String getWechatSecret() {
        if (StringUtils.isNullOrEmpty(wechatSecret)) {
            wechatSecret = get(WechatConfigEnum.WECHAT_SECRET.getKey());
        }
        return wechatSecret;
    }

    public static String getWechatApiKey() {
        if (StringUtils.isNullOrEmpty(wechatApiKey)) {
            wechatApiKey = get(WechatConfigEnum.WECHAT_API_KEY.getKey());
        }
        return wechatApiKey;
    }

    public static String getWechatMchId() {
        if (StringUtils.isNullOrEmpty(wechatMchId)) {
            wechatMchId = get(WechatConfigEnum.WECHAT_MCH_ID.getKey());
        }
        return wechatMchId;
    }

    public static String getWechatApiCertPath() {
        if (StringUtils.isNullOrEmpty(wechatApiCertPath)) {
            wechatApiCertPath = get(WechatConfigEnum.WECHAT_API_CERT_PATH.getKey());
        }
        return wechatApiCertPath;
    }

    public static int getWechatTimeout() {
        if (wechatTimeout == null) {
            wechatTimeout = SP.getIntProperty(WechatConfigEnum.WECHAT_TIMEOUT.getKey(), 5);
        }
        return wechatTimeout;
    }

    public static boolean getUseSandbox() {
        if (null == useSandbox) {
            useSandbox = SP.getBooleanProperty(WechatConfigEnum.WECHAT_USE_SANDBOX.getKey(), false);
        }

        return useSandbox;
    }

    public static String getWechatNotifyUnifiedOrderUrl() {
        if (StringUtils.isNullOrEmpty(wechatNotifyUnifiedOrderUrl)) {
            wechatNotifyUnifiedOrderUrl = get(WechatConfigEnum.WECHAT_NOTIFY_UNIFIEDORDER_URL.getKey());
        }

        return wechatNotifyUnifiedOrderUrl;
    }


    public static String getWechatNotifyRefundUrl() {
        if (StringUtils.isNullOrEmpty(wechatNotifyRefundUrl)) {
            wechatNotifyRefundUrl = get(WechatConfigEnum.WECHAT_NOTIFY_REFUND_URL.getKey());
        }

        return wechatNotifyRefundUrl;
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
