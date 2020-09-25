package com.xinput.wechat.enums;

/**
 * 微信交易类型
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 17:29
 */
public enum TradeTypeEnum {

    JSAPI("JSAPI", "JSAPI支付（或小程序支付）"),
    NATIVE("NATIVE", "Native支付"),
    APP("APP", "app支付"),
    MWEB("MWEB", "H5支付");;

    private String tradeType;
    private String desc;

    TradeTypeEnum(String tradeType, String desc) {
        this.tradeType = tradeType;
        this.desc = desc;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getDesc() {
        return desc;
    }
}
