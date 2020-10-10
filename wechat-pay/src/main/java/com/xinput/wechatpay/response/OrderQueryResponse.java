package com.xinput.wechatpay.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechatpay.enums.BankEnum;
import com.xinput.wechatpay.enums.SignTypeEnum;
import com.xinput.wechatpay.enums.TradeStateEnum;
import com.xinput.wechatpay.exception.WechatPayException;
import com.xinput.wechatpay.util.WechatPayUtils;

import java.util.List;
import java.util.Map;

/**
 * 查询订单返回值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 20:01
 */
@XStreamAlias("xml")
public class OrderQueryResponse extends BaseWeChatPayResponse {

    /**
     * 错误原因
     * 必填: 否
     * 类型: String(32)
     * 示例值: 请确认请求参数是否正确param out_trade_no invalid]
     * 描述: 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("retmsg")
    private String retmsg;

    /**
     * 错误状态
     * 必填: 否
     * 类型: String(32)
     * 示例值: 1
     */
    @XStreamAlias("retcode")
    private Integer retcode;


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
     * 子商户号
     * 必填: 否
     * 类型: String(32)
     * 示例值: Y
     * 描述: 子商户号
     */
    @XStreamAlias("sub_mch_id")
    private String sub_mch_id;

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
     * 描述: {@link TradeStateEnum}
     */
    @XStreamAlias("trade_state")
    private String trade_state;

    /**
     * 付款银行
     * 必填: 是
     * 类型: String(16)
     * 示例值: CMC
     * 描述: 银行类型，采用字符串类型的银行标识
     * {@link BankEnum}
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

    private List<Coupon> coupons;

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

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
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

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    @Override
    public boolean isSuccess() {
        if (StringUtils.equalsIgnoreCase("SUCCESS", this.getReturn_code())
                && StringUtils.equalsIgnoreCase("SUCCESS", this.getTrade_state())) {
            return true;
        }
        return false;
    }

    public static OrderQueryResponse createOrderQueryResponse(Map<String, Object> params, SignTypeEnum signTypeEnum) throws WechatPayException {
        OrderQueryResponse response = BeanMapUtils.toBean(params, OrderQueryResponse.class);

        // 验证签名是否合法
        if (params.containsKey("sign")
                && !WechatPayUtils.isSignatureValid(params, signTypeEnum)) {
            throw new WechatPayException(String.format("Invalid sign value in query order response : [%s]", JsonUtils.toJsonString(response, true)));
        }

        Integer couponCount = response.getCoupon_count();
        if (couponCount == null || couponCount < 1) {
            return response;
        }

        // 对于微信支付返回的带有下标的 _0,_1,_2 类型参数进行封装
        List<Coupon> coupons = Lists.newArrayListWithCapacity(couponCount);
        for (int i = 0; i < couponCount; i++) {
            Coupon coupon = BuilderUtils.of(Coupon::new)
                    .with(Coupon::setIndex, i)
                    .with(Coupon::setCoupon_id, String.valueOf(params.get("coupon_id_" + i)))
                    .with(Coupon::setCoupon_type, String.valueOf(params.get("coupon_type_" + i)))
                    .with(Coupon::setCoupon_fee, Integer.valueOf(String.valueOf(params.get("coupon_fee_" + i))))
                    .with(Coupon::setCoupon_batch, String.valueOf(params.get("coupon_batch_id_" + i)))
                    .build();
            coupons.add(coupon);
        }

        response.setCoupons(coupons);
        return response;
    }
}
