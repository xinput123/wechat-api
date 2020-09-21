package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询退款响应值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 06:54
 */
@XStreamAlias("xml")
public class RefundQueryResponse extends BaseWeChatPayResp {

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 订单总退款次数
     * 必填: 否
     * 类型: Int
     * 示例值: 35
     * 描述: 订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     */
    @XStreamAlias("total_refund_count")
    public Integer total_refund_count;

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
     * 描述: 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlement_total_fee;

    /**
     * 标价币种
     * 必填: 否
     * 类型: String(8)
     * 示例值: CNY
     * 描述: 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("fee_type")
    private String fee_type;

    /**
     * 现金支付金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 现金支付金额订单现金支付金额
     */
    @XStreamAlias("cash_fee")
    private Integer cash_fee;

    /**
     * 退款笔数
     * 必填: 是
     * 类型: Int
     * 示例值: 1
     * 描述: 当前返回退款笔数
     */
    @XStreamAlias("refund_count")
    public Integer refund_count;

    /**
     * 商户退款单号
     * 必填: 是
     * 类型: String(64)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no_$n")
    public String out_refund_no_$n;

    /**
     * 微信退款单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("refund_id_$n")
    public String refund_id_$n;

    /**
     * 退款渠道
     * 必填: 否
     * 类型: String(16)
     * 示例值: ORIGINAL
     * 描述: ORIGINAL—原路退款
     * BALANCE—退回到余额
     * OTHER_BALANCE—原账户异常退到其他余额账户
     * OTHER_BANKCARD—原银行卡异常退到其他银行卡
     */
    @XStreamAlias("refund_channel_$n")
    public String refund_channel_$n;

    /**
     * 申请退款金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias("refund_fee_$n")
    public Integer refund_fee_$n;

    /**
     * 退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XStreamAlias("settlement_refund_fee_$n")
    public Integer settlement_refund_fee_$n;

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
    @XStreamAlias("coupon_type_$n_$m")
    public String coupon_type_$n_$m;

    /**
     * 总代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
     */
    @XStreamAlias("coupon_refund_fee_$n")
    public Integer coupon_refund_fee_$n;

    /**
     * 总代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 1
     * 描述: 退款代金券使用数量 ,$n为下标,从0开始编号
     */
    @XStreamAlias("coupon_refund_count_$n")
    public Integer coupon_refund_count_$n;

    /**
     * 退款代金券ID
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 退款代金券ID, $n为下标，$m为下标，从0开始编号
     */
    @XStreamAlias("coupon_refund_id_$n_$m")
    public String coupon_refund_id_$n_$m;

    /**
     * 单个代金券退款金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
     */
    @XStreamAlias("coupon_refund_fee_$n_$m")
    public Integer coupon_refund_fee_$n_$m;

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
    @XStreamAlias("refund_status_$n")
    public String refund_status_$n;

    /**
     * 退款资金来源
     * 必填: 否
     * 类型: String(30)
     * 示例值: REFUND_SOURCE_RECHARGE_FUNDS
     * 描述: REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     * $n为下标，从0开始编号。
     */
    @XStreamAlias("refund_account_$n")
    public String refund_account_$n;

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
    @XStreamAlias("refund_recv_accout_$n")
    public String refund_recv_accout_$n;

    /**
     * 退款成功时间
     * 必填: 否
     * 类型: String(20)
     * 示例值: 2016-07-25 15:26:26
     * 描述: 退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     */
    @XStreamAlias("refund_success_time_$n")
    public String refund_success_time_$n;

    public Integer getTotal_refund_count() {
        return total_refund_count;
    }

    public void setTotal_refund_count(Integer total_refund_count) {
        this.total_refund_count = total_refund_count;
    }

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

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_$n() {
        return out_refund_no_$n;
    }

    public void setOut_refund_no_$n(String out_refund_no_$n) {
        this.out_refund_no_$n = out_refund_no_$n;
    }

    public String getRefund_id_$n() {
        return refund_id_$n;
    }

    public void setRefund_id_$n(String refund_id_$n) {
        this.refund_id_$n = refund_id_$n;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public Integer getRefund_fee_$n() {
        return refund_fee_$n;
    }

    public void setRefund_fee_$n(Integer refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public Integer getSettlement_refund_fee_$n() {
        return settlement_refund_fee_$n;
    }

    public void setSettlement_refund_fee_$n(Integer settlement_refund_fee_$n) {
        this.settlement_refund_fee_$n = settlement_refund_fee_$n;
    }

    public String getCoupon_type_$n_$m() {
        return coupon_type_$n_$m;
    }

    public void setCoupon_type_$n_$m(String coupon_type_$n_$m) {
        this.coupon_type_$n_$m = coupon_type_$n_$m;
    }

    public Integer getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(Integer coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public Integer getCoupon_refund_count_$n() {
        return coupon_refund_count_$n;
    }

    public void setCoupon_refund_count_$n(Integer coupon_refund_count_$n) {
        this.coupon_refund_count_$n = coupon_refund_count_$n;
    }

    public String getCoupon_refund_id_$n_$m() {
        return coupon_refund_id_$n_$m;
    }

    public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
        this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
    }

    public Integer getCoupon_refund_fee_$n_$m() {
        return coupon_refund_fee_$n_$m;
    }

    public void setCoupon_refund_fee_$n_$m(Integer coupon_refund_fee_$n_$m) {
        this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }

    public String getRefund_account_$n() {
        return refund_account_$n;
    }

    public void setRefund_account_$n(String refund_account_$n) {
        this.refund_account_$n = refund_account_$n;
    }

    public String getRefund_recv_accout_$n() {
        return refund_recv_accout_$n;
    }

    public void setRefund_recv_accout_$n(String refund_recv_accout_$n) {
        this.refund_recv_accout_$n = refund_recv_accout_$n;
    }

    public String getRefund_success_time_$n() {
        return refund_success_time_$n;
    }

    public void setRefund_success_time_$n(String refund_success_time_$n) {
        this.refund_success_time_$n = refund_success_time_$n;
    }

}
