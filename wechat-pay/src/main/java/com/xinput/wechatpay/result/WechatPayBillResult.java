package com.xinput.wechatpay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;
import java.util.List;

/**
 * 微信对账单结果类
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-25 14:28
 */
@XStreamAlias("xml")
public class WechatPayBillResult {

    // 异常或数据不存在时，返回以下三个参数

    @XStreamAlias("return_code")
    private String return_code;

    @XStreamAlias("return_msg")
    private String return_msg;

    @XStreamAlias("error_code")
    private String error_code;

    /**
     * 总交易单数
     */
    @Parsed(index = 0)
    private Integer totalRecord;

    /**
     * 应结订单总金额
     */
    @Parsed(index = 1)
    private BigDecimal totalFee;

    /**
     * 退款总金额
     */
    @Parsed(index = 2)
    private BigDecimal totalRefundFee;

    /**
     * 充值券退款总金额
     */
    @Parsed(index = 3)
    private BigDecimal totalCouponFee;

    /**
     * 手续费总金额
     */
    @Parsed(index = 4)
    private BigDecimal totalPoundageFee;

    /**
     * 订单总金额
     */
    @Parsed(index = 5)
    private BigDecimal totalAmount;

    /**
     * 申请退款总金额
     */
    @Parsed(index = 6)
    private BigDecimal totalAppliedRefundFee;

    /**
     * 对账明细列表
     */
    private List<WechatBillInfo> wechatBillInfos;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<WechatBillInfo> getWechatBillInfos() {
        return wechatBillInfos;
    }

    public void setWechatBillInfos(List<WechatBillInfo> wechatBillInfos) {
        this.wechatBillInfos = wechatBillInfos;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalRefundFee() {
        return totalRefundFee;
    }

    public void setTotalRefundFee(BigDecimal totalRefundFee) {
        this.totalRefundFee = totalRefundFee;
    }

    public BigDecimal getTotalCouponFee() {
        return totalCouponFee;
    }

    public void setTotalCouponFee(BigDecimal totalCouponFee) {
        this.totalCouponFee = totalCouponFee;
    }

    public BigDecimal getTotalPoundageFee() {
        return totalPoundageFee;
    }

    public void setTotalPoundageFee(BigDecimal totalPoundageFee) {
        this.totalPoundageFee = totalPoundageFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAppliedRefundFee() {
        return totalAppliedRefundFee;
    }

    public void setTotalAppliedRefundFee(BigDecimal totalAppliedRefundFee) {
        this.totalAppliedRefundFee = totalAppliedRefundFee;
    }
}
