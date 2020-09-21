package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 申请退款响应值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:45
 */
@XStreamAlias("xml")
public class RefundResponse extends BaseWeChatPayResp {

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 微信订单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 4007752501201407033233368018
     * 描述: 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transaction_id;

    /**
     * 商户订单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 33368018
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 商户退款单号
     * 必填: 是
     * 类型: String(64)
     * 示例值: 121775250
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no")
    private String out_refund_no;

    /**
     * 微信退款单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 2007752501201407033233368018
     * 描述: 微信退款单号
     */
    @XStreamAlias("refund_id")
    private String refund_id;

    /**
     * 退款金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias("refund_fee")
    private Integer refund_fee;

    /**
     * 应结退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlement_refund_fee;

    /**
     * 标价金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 订单总金额，单位为分，只能为整数
     */
    @XStreamAlias("total_fee")
    private Integer total_fee;

    /**
     * 应结订单金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlement_total_fee;

    /**
     * 标价币种
     * 必填: 否
     * 类型: String(8)
     * 示例值: CNY
     * 描述: 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("fee_type")
    private String fee_type;

    /**
     * 现金支付金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 现金支付金额，单位为分，只能为整数
     */
    @XStreamAlias("cash_fee")
    private Integer cash_fee;

    /**
     * 现金支付币种
     * 必填: 否
     * 类型: String(16)
     * 示例值: CNY
     * 描述: 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("cash_fee_type")
    private Integer cash_fee_type;

    /**
     * 现金退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 现金退款金额，单位为分，只能为整数
     */
    @XStreamAlias("cash_refund_fee")
    private Integer cash_refund_fee;

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
    @XStreamAlias("coupon_type_$n")
    private String coupon_type_$n;

    /**
     * 代金券退款总金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
     */
    @XStreamAlias("coupon_refund_fee")
    private Integer coupon_refund_fee;

    /**
     * 单个代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
     */
    @XStreamAlias("coupon_refund_fee_$n")
    private Integer coupon_refund_fee_$n;

    /**
     * 退款代金券使用数量
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款代金券使用数量
     */
    @XStreamAlias("coupon_refund_count")
    private Integer coupon_refund_count;

    /**
     * 退款代金券ID
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 退款代金券ID, $n为下标，从0开始编号
     */
    @XStreamAlias("coupon_refund_id_$n")
    private Integer coupon_refund_id_$n;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Integer getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(Integer settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(Integer settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public Integer getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(Integer cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(Integer cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(Integer coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public Integer getCoupon_refund_id_$n() {
        return coupon_refund_id_$n;
    }

    public void setCoupon_refund_id_$n(Integer coupon_refund_id_$n) {
        this.coupon_refund_id_$n = coupon_refund_id_$n;
    }

}
