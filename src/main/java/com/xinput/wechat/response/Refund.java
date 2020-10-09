package com.xinput.wechat.response;

import java.util.List;

public class Refund {

    /**
     * 仅用于下标进行排序
     */
    private Integer index;

    /**
     * 微信退款单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    private String refund_id;

    /**
     * 退款状态
     * 必填: 是
     * 类型: String(16)
     * 示例值: SUCCESS
     * 描述: 退款状态：
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    private String refund_status;

    /**
     * 总代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
     */
    private Integer coupon_refund_fee;


    /**
     * 申请退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款总金额,单位为分,可以做部分退款
     */
    private Integer refund_fee;

    /**
     * 退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private Integer settlement_refund_fee;

    /**
     * 退款入账账户
     * 必填: 否
     * 类型: String(64)
     * 示例值: 招商银行信用卡0403
     * 描述: 取当前退款单的退款入账方
     * 1）退回银行卡: {银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱: 支付用户零钱
     * 3）退还商户: 商户基本账户、商户结算银行账户
     * 4）退回支付用户零钱通: 支付用户零钱通
     */
    private String refund_recv_accout;

    /**
     * 退款渠道
     * 必填: 否
     * 类型: String(16)
     * 示例值: ORIGINAL
     * 描述: {@link com.xinput.wechat.enums.RefundChannelEnum}
     */
    private String refund_channel;

    /**
     * 商户退款单号
     * 必填: 是
     * 类型: String(64)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    private String out_refund_no;

    /**
     * 退款代金券使用数量
     * 必填: 否
     * 类型: Integer
     * 示例值: 1
     * 描述: 退款代金券使用数量 ,$n为下标,从0开始编号
     */
    private Integer coupon_refund_count;

    private List<RefundDetail> queryRefundDetails;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(Integer settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public String getRefund_recv_accout() {
        return refund_recv_accout;
    }

    public void setRefund_recv_accout(String refund_recv_accout) {
        this.refund_recv_accout = refund_recv_accout;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public List<RefundDetail> getQueryRefundDetails() {
        return queryRefundDetails;
    }

    public void setQueryRefundDetails(List<RefundDetail> queryRefundDetails) {
        this.queryRefundDetails = queryRefundDetails;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }
}
