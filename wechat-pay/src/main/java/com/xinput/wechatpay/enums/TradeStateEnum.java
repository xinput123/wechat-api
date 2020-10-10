package com.xinput.wechatpay.enums;

/**
 * 微信交易类型
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 17:29
 */
public enum TradeStateEnum {

    // 支付成功
    SUCCESS("SUCCESS"),
    // 转入退款
    REFUND("REFUND"),
    // 未支付
    NOTPAY("NOTPAY"),
    // 已关闭
    CLOSED("CLOSED"),
    // 已撤销（刷卡支付）
    REVOKED("REVOKED"),
    // 用户支付中
    USERPAYING("USERPAYING"),
    // 支付失败(其他原因，如银行返回失败)
    PAYERROR("PAYERROR");

    private String tradeState;

    TradeStateEnum(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeState() {
        return tradeState;
    }
}
