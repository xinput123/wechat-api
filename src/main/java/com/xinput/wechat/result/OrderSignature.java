package com.xinput.wechat.result;

import com.xinput.bleach.util.ObjectId;
import com.xinput.bleach.util.SecureUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.response.UnifiedOrderResponse;

/**
 * 统一下单后数据签名
 */
public class OrderSignature {

    private String appId;

    private String nonceStr;

    /**
     * 微信返回的预付款订单Id，前端需要将这个值赋给 package
     */
    private String prepayId;

    private String signType;

    private String timeStamp;

    /**
     * paySign 的值是上面5个参数计算出来的
     */
    private String paySign;

    public OrderSignature(UnifiedOrderResponse response) throws Exception {
        this.appId = response.getAppid();
        this.nonceStr = ObjectId.stringId();
        this.prepayId = response.getPrepay_id();
        this.signType = SignTypeEnum.getAllowSign().getType();

        String stringSignTemp = "appId=" + this.appId
                + "&nonceStr=" + this.nonceStr
                + "&package=prepay_id=" + this.prepayId
                + "&signType=" + (SignTypeEnum.getAllowSign().getType())
                + "&timeStamp=" + timeStamp
                + "&key=" + WechatConfig.getWechatApiKey();

        if (WechatConfig.getUseSandbox()) {
            this.paySign = SecureUtils.MD5(stringSignTemp);
        } else {
            this.paySign = SecureUtils.HMACSHA256(stringSignTemp, WechatConfig.getWechatApiKey());
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }


}
