package com.xinput.wechatpay.enums;

/**
 * 退款渠道
 *
 * @author xinput
 * @date 2020-09-24 15:19
 */
public enum RefundChannelEnum {

    ORIGINAL("ORIGINAL", "原路退款"),
    BALANCE("BALANCE", "退回到余额"),
    OTHER_BALANCE("OTHER_BALANCE", "原账户异常退到其他余额账户"),
    OTHER_BANKCARD("OTHER_BANKCARD", "原银行卡异常退到其他银行卡");

    private String channel;

    private String desc;

    RefundChannelEnum(String channel, String desc) {
        this.channel = channel;
        this.desc = desc;
    }

    public String getChannel() {
        return channel;
    }

    public String getDesc() {
        return desc;
    }
}
