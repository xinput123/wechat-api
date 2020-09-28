package com.xinput.wechat.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;

/**
 * 付款码支付返回值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-14 10:55
 */
@XStreamAlias("xml")
public class MicroPayResponse extends BaseWeChatPayResponse {

    // 以下字段在return_code为SUCCESS的时候有返回
    /**
     * 设备号
     * 必填: 否
     * 类型: String(32)
     * 示例值: 013467007045764
     * 描述: 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("device_info")
    private String device_info;


    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回

    /**
     * 用户标识
     * 必填: 是
     * 类型: String(128)
     * 示例值: Y
     * 描述: 用户在商户appid 下的唯一标识
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 是否关注公众账号
     * 必填: 是
     * 类型: String(1)
     * 示例值: Y
     * 描述: 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
     */
    @XStreamAlias("is_subscribe")
    private String is_subscribe;

    /**
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: MICROPAY
     * 描述: MICROPAY 付款码支付
     */
    @XStreamAlias("trade_type")
    private String trade_type;

    /**
     * 付款银行
     * 必填: 是
     * 类型: String(16)
     * 示例值: CMC
     * 描述: 银行类型，采用字符串类型的银行标识
     * {@link com.xinput.wechat.enums.BankEnum}
     */
    @XStreamAlias("bank_type")
    private String bank_type;

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
     * 代金券金额
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额
     */
    @XStreamAlias("coupon_fee")
    private Integer coupon_fee;

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
     * 现金支付金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 现金支付金额订单现金支付金额
     */
    @XStreamAlias("cash_fee")
    private Integer cash_fee;

    /**
     * 微信订单号
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
     * 示例值: 33368018
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 商家数据包
     * 必填: 否
     * 类型: String(128)
     * 示例值: 123456
     * 描述: 商家数据包，原样返回
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
     * 营销详情
     * 必填: 否
     * 类型: String(6000)
     * 示例值: 20141030133525
     * 描述: 新增返回，单品优惠功能字段
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_101&index=1
     */
    @XStreamAlias("promotion_detail")
    private String promotion_detail;

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

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
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

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
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

    public String getPromotion_detail() {
        return promotion_detail;
    }

    public void setPromotion_detail(String promotion_detail) {
        this.promotion_detail = promotion_detail;
    }

    @Override
    public boolean isSuccess() {
        if (StringUtils.equalsIgnoreCase("SUCCESS", this.getReturn_code())
                && StringUtils.equalsIgnoreCase("SUCCESS", this.getResult_code())) {
            return true;
        }
        return false;
    }
}
