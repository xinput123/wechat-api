package com.xinput.wechat.enums;

import com.xinput.bleach.util.StringUtils;

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
    REFUND("REFUND"),
    // 返回当日充值退款订单
    RECHARGE_REFUND("RECHARGE_REFUND");

    private String billType;

    BillTypeEnum(String billType) {
        this.billType = billType;
    }

    public String getBillType() {
        return billType;
    }

    public static BillTypeEnum get(String billType) {
        if (StringUtils.isNotNullOrEmpty(billType)) {
            for (BillTypeEnum billTypeEnum : BillTypeEnum.values()) {
                if (StringUtils.equalsIgnoreCase(billTypeEnum.billType, billType)) {
                    return billTypeEnum;
                }
            }
        }

        return null;
    }
}
