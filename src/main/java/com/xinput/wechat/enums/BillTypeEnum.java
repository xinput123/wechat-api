package com.xinput.wechat.enums;

/**
 * 账单类型
 *
 * @author xinput
 * @date 2020-09-18 15:20
 */
public enum BillTypeEnum {

    // 返回当日所有订单信息（不含充值退款订单), 默认值
    ALL("ALL"),
    // 返回当日成功支付的订单（不含充值退款订单）
    SUCCESS("SUCCESS"),
    // 返回当日退款订单（不含充值退款订单）
    REFUND("REFUND");

    private String billType;

    BillTypeEnum(String billType) {
        this.billType = billType;
    }

    public String getBillType() {
        return billType;
    }
}
