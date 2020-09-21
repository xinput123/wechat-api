package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询订单返回值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 20:01
 */
@XStreamAlias("xml")
public class OrderQueryResponse extends BaseWeChatPayResp {

    // 以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，
    // 如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）
    /**
     * 设备号
     * 必填: 否
     * 类型: String(32)
     * 示例值: 013467007045764
     * 描述: 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("device_info")
    private String device_info;

    /**
     * 用户标识
     * 必填: 是
     * 类型: String(128)
     * 示例值: oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 描述: 用户在商户appid下的唯一标识
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 是否关注公众账号
     * 必填: 是
     * 类型: String(1)
     * 示例值: Y
     * 描述: 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XStreamAlias("is_subscribe")
    private String is_subscribe;

    /**
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: JSAPI
     * 描述: 小程序取值如下：JSAPI
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("trade_type")
    private String trade_type;

    /**
     * 交易状态
     * 必填: 是
     * 类型: String(32)
     * 示例值: SUCCESS
     * 描述: {@link com.xinput.wechatpay.consts.TradeStateEnum}
     */
    @XStreamAlias("trade_state")
    private String trade_state;

    /**
     * 付款银行
     * 必填: 是
     * 类型: String(16)
     * 示例值: CMC
     * 描述: 银行类型，采用字符串类型的银行标识
     * {@link com.xinput.wechatpay.consts.BankConsts}
     */
    @XStreamAlias("bank_type")
    private String bank_type;

    /**
     * 标价金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 订单总金额，单位为分
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
     * 现金支付币种
     * 必填: 否
     * 类型: String(16)
     * 示例值: CNY
     * 描述: 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("cash_fee_type")
    private String cash_fee_type;

    /**
     * 代金券金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额
     */
    @XStreamAlias("coupon_fee")
    private Integer coupon_fee;

    /**
     * 代金券使用数量
     * 必填: 否
     * 类型: Integer
     * 示例值: 1
     * 描述: 代金券使用数量
     */
    @XStreamAlias("coupon_count")
    private Integer coupon_count;

    /**
     * 代金券类型
     * 必填: 否
     * 类型: String
     * 示例值: CASH
     * 描述: CASH--充值代金券
     * NO_CASH---非充值优惠券
     * <p>
     * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     */
    @XStreamAlias("coupon_type_$n")
    private String coupon_type_$n;

    /**
     * 代金券ID
     * 必填: 否
     * 类型: String(20)
     * 示例值: 10000
     * 描述: 代金券ID, $n为下标，从0开始编号
     */
    @XStreamAlias("coupon_id_$n")
    private String coupon_id_$n;

    /**
     * 单个代金券支付金额
     * 必填: 否
     * 类型: Int
     * 示例值: 100
     * 描述: 单个代金券支付金额, $n为下标，从0开始编号
     */
    @XStreamAlias("coupon_fee_$n")
    private Integer coupon_fee_$n;

    /**
     * 微信支付单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1009660380201506130728806387
     * 描述: 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transaction_id;

    /**
     * 商户订单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 20150806125346
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 附加数据
     * 必填: 否
     * 类型: String(128)
     * 示例值: 深圳分店
     * 描述: 附加数据，原样返回
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 支付完成时间
     * 必填: 是
     * 类型: String(14)
     * 示例值: 20141030133525
     * 描述: 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    @XStreamAlias("time_end")
    private String time_end;

    /**
     * 交易状态描述
     * 必填: 是
     * 类型: String(256)
     * 示例值: 支付失败，请重新下单支付
     * 描述: 对当前查询订单状态的描述和下一步操作的指引
     */
    @XStreamAlias("trade_state_desc")
    private String trade_state_desc;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
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

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public Integer getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public Integer getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(Integer coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
}
