package com.xinput.wechat.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.exception.WechatException;
import com.xinput.wechat.util.WechatPayUtils;

import java.util.List;
import java.util.Map;

/**
 * 申请退款响应值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:45
 */
@XStreamAlias("xml")
public class RefundResponse extends BaseWeChatPayResponse {

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: JSAPI
     * 描述: {@link com.xinput.wechat.enums.TradeTypeEnum}
     */
    @XStreamAlias("trade_type")
    private String trade_type;

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
     * 示例值: Y
     * 描述: 用户在商户appid 下的唯一标识
     */
    @XStreamAlias("openid")
    private String openid;

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
     * 现金退款类型
     * 必填: 否
     * 类型: String
     * 示例值: CNY
     */
    @XStreamAlias("refund_fee_type")
    private String refund_fee_type;

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
    private String cash_fee_type;

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
     * 现金退款金额
     * 必填: 否
     * 类型: String
     * 示例值: CNY
     */
    @XStreamAlias("cash_refund_fee_type")
    private String cash_refund_fee_type;

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
     * 错误信息
     * 必填: 否
     * 类型: String(128)
     * 示例值: SUCCESS
     * 描述: 错误信息描述
     */
    @XStreamAlias("err_msg")
    private String err_msg;

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
     * 退款代金券使用数量
     * 必填: 否
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款代金券使用数量
     */
    @XStreamAlias("coupon_refund_count")
    private Integer coupon_refund_count;

    /**
     * 退还代金券数据
     */
    private List<RefundCoupon> refundCoupons;

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

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(Integer cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

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

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getCash_refund_fee_type() {
        return cash_refund_fee_type;
    }

    public void setCash_refund_fee_type(String cash_refund_fee_type) {
        this.cash_refund_fee_type = cash_refund_fee_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public List<RefundCoupon> getRefundCoupons() {
        return refundCoupons;
    }

    public void setRefundCoupons(List<RefundCoupon> refundCoupons) {
        this.refundCoupons = refundCoupons;
    }

    @Override
    public boolean isSuccess() {
        if (StringUtils.equalsIgnoreCase("SUCCESS", this.getReturn_code())
                && StringUtils.equalsIgnoreCase("SUCCESS", this.getResult_code())) {
            return true;
        }
        return false;
    }

    public static RefundResponse createRefundResponse(Map<String, Object> params, SignTypeEnum signTypeEnum) throws Exception {
        RefundResponse refundResponse = BeanMapUtils.toBean(params, RefundResponse.class);
        // 验证签名是否合法
        if (params.containsKey("sign")
                && !WechatPayUtils.isSignatureValid(params, signTypeEnum)) {
            throw new WechatException(String.format("Invalid sign value in close order response : [%s]", JsonUtils.toJsonString(refundResponse, true)));
        }

        Integer couponRefundCount = refundResponse.getCoupon_refund_count();
        if (couponRefundCount == null || couponRefundCount < 1) {
            return refundResponse;
        }

        // 对于微信支付返回的带有下标的 _0,_1,_2 类型参数进行封装
        List<RefundCoupon> refundCoupons = Lists.newArrayListWithCapacity(couponRefundCount);
        for (int i = 0; i < couponRefundCount; i++) {
            RefundCoupon refundCoupon = BuilderUtils.of(RefundCoupon::new)
                    .with(RefundCoupon::setIndex, i)
                    .with(RefundCoupon::setCoupon_refund_id, String.valueOf(params.get("coupon_refund_id_" + i)))
                    .with(RefundCoupon::setCoupon_type, String.valueOf(params.get("coupon_type_" + i)))
                    .with(RefundCoupon::setCoupon_refund_fee, Integer.valueOf(String.valueOf(params.get("coupon_refund_fee_" + i))))
                    .build();
            refundCoupons.add(refundCoupon);
        }
        refundResponse.setRefundCoupons(refundCoupons);

        return refundResponse;
    }
}
