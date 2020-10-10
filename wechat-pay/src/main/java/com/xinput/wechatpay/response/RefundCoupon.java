package com.xinput.wechatpay.response;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-24 11:27
 */
public class RefundCoupon {

    /**
     * 代金券下标,非返回数据，仅仅是用来标识微信返回数据的顺序
     */
    private Integer index;

    /**
     * 代金券类型
     * 必填: 否
     * 类型: String(8)
     * 示例值: CASH
     * 描述: CASH--充值代金券
     * NO_CASH---非充值优惠券
     * <p>
     * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     */
    private String coupon_type;

    /**
     * 单个代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
     */
    private Integer coupon_refund_fee;

    /**
     * 退款代金券ID
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 退款代金券ID, $n为下标，从0开始编号
     */
    private String coupon_refund_id;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public String getCoupon_refund_id() {
        return coupon_refund_id;
    }

    public void setCoupon_refund_id(String coupon_refund_id) {
        this.coupon_refund_id = coupon_refund_id;
    }
}
