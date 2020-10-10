package com.xinput.wechatpay.response;

/**
 * 代金券
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-22 17:35
 */
public class Coupon {

    /**
     * 代金券下标
     */
    private Integer index;

    /**
     * 代金券类型
     * 必填: 否
     * 类型: String
     * 示例值: CASH
     * 描述: CASH--充值代金券
     * NO_CASH---非充值优惠券
     * <p>
     * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）
     */
    private String coupon_type;

    /**
     * 代金券
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 代金券ID, $n为下标，从0开始编号
     */
    private String coupon_id;

    /**
     * 代金券批次
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 代金券批次, $n为下标，从0开始编号
     */
    private String coupon_batch;

    /**
     * 单个代金券支付金额
     * 必填: 否
     * 类型: Int
     * 示例值: 10000
     * 描述: 单个代金券支付金额, $n为下标，从0开始编号
     */
    private Integer coupon_fee;

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

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_batch() {
        return coupon_batch;
    }

    public void setCoupon_batch(String coupon_batch) {
        this.coupon_batch = coupon_batch;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }
}
