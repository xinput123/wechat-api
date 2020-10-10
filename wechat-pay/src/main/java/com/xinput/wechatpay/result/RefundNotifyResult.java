package com.xinput.wechatpay.result;import com.thoughtworks.xstream.annotations.XStreamAlias;/** * 从 RefundNotifyDto 中解析的微信退款通知内容 */@XStreamAlias("root")public class RefundNotifyResult {    /**     * 微信订单号     * 必填: 是     * 类型: String(32)     * 示例值: 1217752501201407033233368018     * 描述: 微信订单号     */    @XStreamAlias("transaction_id")    private String transaction_id;    /**     * 商户订单号     * 必填: 是     * 类型: String(32)     * 示例值: 5f646c8d280319c40fde6bf9     * 描述: 商户系统内部的订单号     */    @XStreamAlias("out_trade_no")    private String out_trade_no;    /**     * 微信退款单号     * 必填: 是     * 类型: String(32)     * 示例值: 50300705602020101003189078019     * 描述: 微信退款单号     */    @XStreamAlias("refund_id")    private String refund_id;    /**     * 商户退款单号     * 必填: 是     * 类型: String(32)     * 示例值: 5f814d11d10ae5ef871db3d8     * 描述: 微信退款单号     */    @XStreamAlias("out_refund_no")    private String out_refund_no;    /**     * 订单金额     * 必填: 是     * 类型: Int     * 示例值: 100     * 描述: 订单总金额，单位为分，只能为整数     */    @XStreamAlias("total_fee")    private Integer total_fee;    /**     * 应结订单金额     * 必填: 否     * 类型: Int     * 示例值: 100     * 描述: 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。     */    @XStreamAlias("settlement_total_fee")    private Integer settlement_total_fee;    /**     * 申请退款金额     * 必填: 是     * 类型: Int     * 示例值: 100     * 描述: 退款总金额,单位为分     */    @XStreamAlias("refund_fee")    private Integer refund_fee;    /**     * 退款金额     * 必填: 是     * 类型: Int     * 示例值: 100     * 描述: 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额     */    @XStreamAlias("settlement_refund_fee")    private Integer settlement_refund_fee;    /**     * 退款状态     * 必填: 是     * 类型: String(16)     * 示例值: SUCCESS     * 描述: SUCCESS-退款成功、CHANGE-退款异常、REFUNDCLOSE—退款关闭     */    @XStreamAlias("refund_status")    private String refund_status;    /**     * 退款成功时间     * 必填: 否     * 类型: String(20)     * 示例值: 2017-12-15 09:46:01     * 描述: 资金退款至用户帐号的时间，格式2017-12-15 09:46:01     */    @XStreamAlias("success_time")    private String success_time;    /**     * 退款入账账户     * 必填: 是     * 类型: String(64)     * 示例值: 招商银行信用卡0403     * 描述: 取当前退款单的退款入账方     * 1）退回银行卡：{银行名称}{卡类型}{卡尾号}     * 2）退回支付用户零钱: 支付用户零钱     * 3）退还商户: 商户基本账户 商户结算银行账户     * 4）退回支付用户零钱通: 支付用户零钱通     */    @XStreamAlias("refund_recv_accout")    private String refund_recv_accout;    /**     * 退款资金来源     * 必填: 是     * 类型: String(30)     * 示例值: REFUND_SOURCE_RECHARGE_FUNDS     * 描述: REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户、REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款     */    @XStreamAlias("refund_account")    private String refund_account;    /**     * 退款发起来源     * 必填: 是     * 类型: String(30)     * 示例值: API     * 描述: API接口、VENDOR_PLATFORM商户平台     */    @XStreamAlias("refund_request_source")    private String refund_request_source;    public String getTransaction_id() {        return transaction_id;    }    public void setTransaction_id(String transaction_id) {        this.transaction_id = transaction_id;    }    public String getOut_trade_no() {        return out_trade_no;    }    public void setOut_trade_no(String out_trade_no) {        this.out_trade_no = out_trade_no;    }    public String getRefund_id() {        return refund_id;    }    public void setRefund_id(String refund_id) {        this.refund_id = refund_id;    }    public String getOut_refund_no() {        return out_refund_no;    }    public void setOut_refund_no(String out_refund_no) {        this.out_refund_no = out_refund_no;    }    public Integer getTotal_fee() {        return total_fee;    }    public void setTotal_fee(Integer total_fee) {        this.total_fee = total_fee;    }    public Integer getSettlement_total_fee() {        return settlement_total_fee;    }    public void setSettlement_total_fee(Integer settlement_total_fee) {        this.settlement_total_fee = settlement_total_fee;    }    public Integer getRefund_fee() {        return refund_fee;    }    public void setRefund_fee(Integer refund_fee) {        this.refund_fee = refund_fee;    }    public Integer getSettlement_refund_fee() {        return settlement_refund_fee;    }    public void setSettlement_refund_fee(Integer settlement_refund_fee) {        this.settlement_refund_fee = settlement_refund_fee;    }    public String getRefund_status() {        return refund_status;    }    public void setRefund_status(String refund_status) {        this.refund_status = refund_status;    }    public String getSuccess_time() {        return success_time;    }    public void setSuccess_time(String success_time) {        this.success_time = success_time;    }    public String getRefund_recv_accout() {        return refund_recv_accout;    }    public void setRefund_recv_accout(String refund_recv_accout) {        this.refund_recv_accout = refund_recv_accout;    }    public String getRefund_account() {        return refund_account;    }    public void setRefund_account(String refund_account) {        this.refund_account = refund_account;    }    public String getRefund_request_source() {        return refund_request_source;    }    public void setRefund_request_source(String refund_request_source) {        this.refund_request_source = refund_request_source;    }}