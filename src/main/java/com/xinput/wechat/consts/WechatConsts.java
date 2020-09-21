package com.xinput.wechat.consts;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 15:42
 */
public class WechatConsts {

    /**
     * 默认配置文件名称
     */
    public static final String DEFAULT_SYSTEM_FILE = "system.properties";

    /**
     * 微信小程序设置: 应用id，微信分配
     */
    public static final String WECHAT_APPID = "wechat.app.id";

    /**
     * 微信小程序设置: 密钥
     */
    public static final String WECHAT_SECRET = "wechat.app.secret";

    /**
     * Api密钥 微信商户平台(pay.weixin.qq.com)-->账户中心-->账户设置-->API安全-->密钥设置
     */
    public static final String WECHAT_API_KEY = "wechat.api.key";

    /**
     * 请求微信支付超时时间，单位秒，默认5
     */
    public static final String WECHAT_TIMEOUT = "wechat.timeout";

    /**
     * 商户号
     */
    public static final String WECHAT_MCH_ID = "wechat.mch.id";

    /**
     * 证书存放地址
     */
    public static final String WECHAT_API_CERT_PATH = "wechat.api.cert.path";

    /**
     * 是否使用沙箱环境
     */
    public static final String WECHAT_USE_SANDBOX = "wechat.use.sandbox";
}
