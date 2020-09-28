package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 20:04
 */
public class BaseWeChatPayRequest {

    /**
     * 小程序ID
     * 必填: 是
     * 类型: String(32)
     * 示例值: wxd678efh567hg6787
     * 描述: 微信分配的小程序ID
     */
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1230000109
     * 描述: 微信支付分配的商户号
     */
    @XStreamAlias("mch_id")
    private String mch_id;

    /**
     * 随机字符串
     * 必填: 是
     * 类型: String(32)
     * 示例值: 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述: 随机字符串，长度要求在32位以内。推荐 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
     */
    @XStreamAlias("nonce_str")
    private String nonce_str;

    /**
     * 签名类型
     * 必填: 否
     * 类型: String(32)
     * 示例值: MD5
     * 描述: 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    @XStreamAlias("sign_type")
    private String sign_type;

    /**
     * 签名
     * 必填: 是
     * 类型: String(64)
     * 示例值: C380BEC2BFD727A4B6845133519F3AD6
     * 描述:  通过签名算法计算得出的签名值 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
     */
    @XStreamAlias("sign")
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    @Override
    public String toString() {
        return "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", sign='" + sign;
    }
}
