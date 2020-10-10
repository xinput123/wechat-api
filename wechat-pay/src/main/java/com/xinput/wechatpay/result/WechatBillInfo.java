package com.xinput.wechatpay.result;

import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;

/**
 * 交易账单 - 当日所有订单
 *
 * @author <a href="mailto:xinputxx@gmailcom">xinput</a>
 * @date 2020-09-25 13:51
 */
public class WechatBillInfo {

    /**
     * 交易时间
     */
    @Parsed(index = 0)
    private String tradeTime;

    /**
     * 公众账号ID
     */
    @Parsed(index = 1)
    private String appId;

    /**
     * 商户号
     */
    @Parsed(index = 2)
    private String mchId;

    /**
     * 子商户号
     */
    @Parsed(index = 3)
    private String subMchId;

    /**
     * 设备号
     */
    @Parsed(index = 4)
    private String deviceInfo;

    /**
     * 微信订单号
     */
    @Parsed(index = 5)
    private String transactionId;

    /**
     * 商户订单号
     */
    @Parsed(index = 6)
    private String outTradeNo;

    /**
     * 用户标识
     */
    @Parsed(index = 7)
    private String openId;

    /**
     * 交易类型
     */
    @Parsed(index = 8)
    private String tradeType;

    /**
     * 交易状态
     */
    @Parsed(index = 9)
    private String tradeState;

    /**
     * 付款银行
     */
    @Parsed(index = 10)
    private String bankType;

    /**
     * 货币种类
     */
    @Parsed(index = 11)
    private String feeType;

    /**
     * 应结订单金额
     */
    @Parsed(index = 12)
    private BigDecimal totalFee;

    /**
     * 代金券金额
     */
    @Parsed(index = 13)
    private BigDecimal couponFee;

    /**
     * 微信退款单号
     */
    @Parsed(index = 14)
    private String refundId;

    /**
     * 商户退款单号
     */
    @Parsed(index = 15)
    private String outRefundNo;

    /**
     * 退款金额
     */
    @Parsed(index = 16)
    private BigDecimal settlementRefundFee;

    /**
     * 充值券退款金额
     */
    @Parsed(index = 17)
    private BigDecimal couponRefundFee;

    /**
     * 退款类型
     */
    @Parsed(index = 18)
    private String refundChannel;

    /**
     * 退款状态
     */
    @Parsed(index = 19)
    private String refundState;

    /**
     * 商品名称
     */
    @Parsed(index = 20)
    private String body;

    /**
     * 商户数据包
     */
    @Parsed(index = 21)
    private String attach;

    /**
     * 手续费
     */
    @Parsed(index = 22)
    private BigDecimal poundage;

    /**
     * 费率
     */
    @Parsed(index = 23)
    private String poundageRate;

    /**
     * 订单金额
     */
    @Parsed(index = 24)
    private BigDecimal totalAmount;

    /**
     * 申请退款金额
     */
    @Parsed(index = 25)
    private BigDecimal appliedRefundAmount;

    /**
     * 费率备注
     */
    @Parsed(index = 26)
    private String feeRemark;

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(BigDecimal couponFee) {
        this.couponFee = couponFee;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public BigDecimal getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(BigDecimal settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public BigDecimal getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(BigDecimal couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public String getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(String poundageRate) {
        this.poundageRate = poundageRate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAppliedRefundAmount() {
        return appliedRefundAmount;
    }

    public void setAppliedRefundAmount(BigDecimal appliedRefundAmount) {
        this.appliedRefundAmount = appliedRefundAmount;
    }

    public String getFeeRemark() {
        return feeRemark;
    }

    public void setFeeRemark(String feeRemark) {
        this.feeRemark = feeRemark;
    }
}
