package com.xinput.wechat.response.pay;

/**
 * 退款代金券明细
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-24 15:34
 */
public class QueryRefundDetail {

    private Integer detailIndex;

    /**
     * 退款代金券ID
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 退款代金券ID, $n为下标，$m为下标，从0开始编号
     * 举例: coupon_refund_id_$0_$1
     */
    private String coupon_refund_id;

    /**
     * 代金券类型
     * 必填: 否
     * 类型: String(8)
     * 示例值: 100
     * 描述: CASH--充值代金券
     * NO_CASH---非充值优惠券
     * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,$m为下标,从0开始编号，
     * 举例：coupon_type_$0_$1
     */
    private String coupon_type;

    /**
     * 单个代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
     * 举例: coupon_refund_fee_$n_$m
     */
    private Integer coupon_refund_fee;

    public Integer getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Integer detailIndex) {
        this.detailIndex = detailIndex;
    }

    public String getCoupon_refund_id() {
        return coupon_refund_id;
    }

    public void setCoupon_refund_id(String coupon_refund_id) {
        this.coupon_refund_id = coupon_refund_id;
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
}
