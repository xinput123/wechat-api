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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 查询退款响应值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 06:54
 */
@XStreamAlias("xml")
public class RefundQueryResponse extends BaseWeChatPayResponse {

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

    /**
     * 用户标识
     * 必填: 否
     * 类型: String(128)
     * 示例值: Y
     * 描述:
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 付款银行
     * 必填: 否
     * 类型: String(16)
     * 示例值: CMC
     * 描述: 银行类型，采用字符串类型的银行标识
     * {@link com.xinput.wechat.enums.BankEnum}
     */
    @XStreamAlias("bank_type")
    private String bank_type;

    /**
     * 订单总退款次数
     * 必填: 否
     * 类型: Int
     * 示例值: 35
     * 描述: 订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     */
    @XStreamAlias("total_refund_count")
    private Integer total_refund_count;

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
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: JSAPI
     * 描述: {@link com.xinput.wechat.enums.TradeTypeEnum}
     */
    @XStreamAlias("trade_type")
    private String trade_type;

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
    private Integer refund_count;

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
     * 费率
     * 必填: 否
     * 类型: number
     * 示例值: 1.0
     * 描述: 费率
     */
    @XStreamAlias("rate")
    private BigDecimal rate;

    private List<QueryRefund> queryRefunds;

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

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<QueryRefund> getQueryRefunds() {
        return queryRefunds;
    }

    public void setQueryRefunds(List<QueryRefund> queryRefunds) {
        this.queryRefunds = queryRefunds;
    }

    @Override
    public boolean isSuccess() {
        if (StringUtils.equalsIgnoreCase("SUCCESS", this.getReturn_code())
                && StringUtils.equalsIgnoreCase("SUCCESS", this.getResult_code())) {
            return true;
        }
        return false;
    }

    public static RefundQueryResponse createRefundQueryResponse(Map<String, Object> params, SignTypeEnum signTypeEnum) throws Exception {
        RefundQueryResponse refundQueryResponse = BeanMapUtils.toBean(params, RefundQueryResponse.class);

        // 验证签名是否合法
        if (params.containsKey("sign")
                && !WechatPayUtils.isSignatureValid(params, signTypeEnum)) {
            throw new WechatException(String.format("Invalid sign value in WechatPayApi.refundQuery response : [%s]", JsonUtils.toJsonString(refundQueryResponse, true)));
        }

        Integer refundCount = refundQueryResponse.getRefund_count();
        if (refundCount == null || refundCount < 1) {
            return refundQueryResponse;
        }

        // 对于微信支付返回的带有下标的 _0,_1,_2 类型参数进行封装
        List<QueryRefund> queryRefunds = Lists.newArrayListWithCapacity(refundCount);
        for (int i = 0; i < refundCount; i++) {
            Integer couponRefundCount = Integer.valueOf(String.valueOf(params.get("coupon_refund_count_" + i)));
            QueryRefund refundCoupon = BuilderUtils.of(QueryRefund::new)
                    .with(QueryRefund::setIndex, i)
                    .with(QueryRefund::setRefund_id, String.valueOf(params.get("refund_id_" + i)))
                    .with(QueryRefund::setRefund_status, String.valueOf(params.get("refund_status_" + i)))
                    .with(QueryRefund::setCoupon_refund_fee, Integer.valueOf(String.valueOf(params.get("coupon_refund_fee_" + i))))
                    .with(QueryRefund::setRefund_fee, Integer.valueOf(String.valueOf(params.get("refund_fee_" + i))))
                    .with(QueryRefund::setSettlement_refund_fee, Integer.valueOf(String.valueOf(params.get("settlement_refund_fee_" + i))))
                    .with(QueryRefund::setRefund_recv_accout, String.valueOf(params.get("refund_recv_accout_" + i)))
                    .with(QueryRefund::setRefund_channel, String.valueOf(params.get("refund_channel_" + i)))
                    .with(QueryRefund::setOut_refund_no, String.valueOf(params.get("out_refund_no_" + i)))
                    .with(QueryRefund::setCoupon_refund_count, couponRefundCount)
                    .build();
            if (couponRefundCount > 0) {
                List<QueryRefundDetail> details = Lists.newArrayListWithCapacity(couponRefundCount);
                for (int k = 0; k < couponRefundCount; k++) {
                    String suffixKey = i + "_" + k;
                    QueryRefundDetail detail = BuilderUtils.of(QueryRefundDetail::new)
                            .with(QueryRefundDetail::setDetailIndex, k)
                            .with(QueryRefundDetail::setCoupon_refund_id, String.valueOf(params.get("refund_channel_" + suffixKey)))
                            .with(QueryRefundDetail::setCoupon_type, String.valueOf(params.get("coupon_type_" + suffixKey)))
                            .with(QueryRefundDetail::setCoupon_refund_fee, Integer.valueOf(String.valueOf(params.get("coupon_refund_fee_" + suffixKey))))
                            .build();
                    details.add(detail);
                }
                refundCoupon.setQueryRefundDetails(details);
            }

            queryRefunds.add(refundCoupon);
        }
        refundQueryResponse.setQueryRefunds(queryRefunds);

        return refundQueryResponse;
    }
}
