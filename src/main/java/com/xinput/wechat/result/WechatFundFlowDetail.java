package com.xinput.wechat.result;

import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;

/**
 * 记账时间:2018-02-01 04:21:23 微信支付业务单号:50000305742018020103387128253 资金流水单号:1900009231201802015884652186 业务名称:退款
 * 业务类型:退款 收支类型:支出 收支金额（元）:0.02 账户结余（元）:0.17 资金变更提交申请人:system 备注:缺货 业务凭证号:REF4200000068201801293084726067
 *
 * @author cwivan
 */
public class WechatFundFlowDetail {

    /**
     * 记账时间
     */
    @Parsed(index = 0)
    private String billingTime;

    /**
     * 微信支付业务单号
     */
    @Parsed(index = 1)
    private String bizTransactionId;

    /**
     * 资金流水单号
     */
    @Parsed(index = 2)
    private String fundFlowId;

    /**
     * 业务名称
     */
    @Parsed(index = 3)
    private String bizName;

    /**
     * 业务类型
     */
    @Parsed(index = 4)
    private String bizType;

    /**
     * 收支类型
     */
    @Parsed(index = 5)
    private String financialType;

    /**
     * 收支金额（元）
     */
    @Parsed(index = 6)
    private BigDecimal financialFee;

    /**
     * 账户结余（元）
     */
    @Parsed(index = 7)
    private BigDecimal AccountBalance;

    /**
     * 资金变更提交申请人
     */
    @Parsed(index = 8)
    private String fundApplicant;

    /**
     * 备注
     */
    @Parsed(index = 9)
    private String memo;

    /**
     * 业务凭证号
     */
    @Parsed(index = 10)
    private String bizVoucherId;

    public String getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(String billingTime) {
        this.billingTime = billingTime;
    }

    public String getBizTransactionId() {
        return bizTransactionId;
    }

    public void setBizTransactionId(String bizTransactionId) {
        this.bizTransactionId = bizTransactionId;
    }

    public String getFundFlowId() {
        return fundFlowId;
    }

    public void setFundFlowId(String fundFlowId) {
        this.fundFlowId = fundFlowId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public BigDecimal getFinancialFee() {
        return financialFee;
    }

    public void setFinancialFee(BigDecimal financialFee) {
        this.financialFee = financialFee;
    }

    public BigDecimal getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        AccountBalance = accountBalance;
    }

    public String getFundApplicant() {
        return fundApplicant;
    }

    public void setFundApplicant(String fundApplicant) {
        this.fundApplicant = fundApplicant;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBizVoucherId() {
        return bizVoucherId;
    }

    public void setBizVoucherId(String bizVoucherId) {
        this.bizVoucherId = bizVoucherId;
    }
}
