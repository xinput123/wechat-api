package com.xinput.wechatpay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechatpay.exception.WechatPayException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 申请退款 - 需要双向证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:37
 */
@XStreamAlias("xml")
public class RefundRequest extends BaseWeChatPayRequest {

    /**
     * 微信订单号
     * 必填: 和【商户订单号】二选一
     * 类型: String(32)
     * 示例值: 1009660380201506130728806387
     * 描述: 微信的订单号，优先使用
     */
    @XStreamAlias("transaction_id")
    private String transaction_id;

    /**
     * 商户订单号
     * 必填: 和【微信订单号】二选一
     * 类型: String(32)
     * 示例值: 1009660380201506130728806387
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 商户退款单号
     * 必填: 是
     * 类型: String(64)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @NotEmpty(message = "[out_refund_no] 不能为空")
    @XStreamAlias("out_refund_no")
    private String out_refund_no;

    /**
     * 订单金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 订单总金额，单位为分，只能为整数
     */
    @NotNull(message = "[total_fee] 不能为空")
    @XStreamAlias("total_fee")
    private Integer total_fee;

    /**
     * 退款金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 100
     * 描述: 退款总金额，订单总金额，单位为分，只能为整数
     */
    @NotNull(message = "[refund_fee] 不能为空")
    @XStreamAlias("refund_fee")
    private Integer refund_fee;

    /**
     * 货币种类
     * 必填: 否
     * 类型: string(8)
     * 示例值: CNY
     * 描述: 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("refund_fee_type")
    private String refund_fee_type;

    /**
     * 退款原因
     * 必填: 否
     * 类型: string(80)
     * 示例值: 商品已售完
     * 描述: 若商户传入，会在下发给用户的退款消息中体现退款原因
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     */
    @XStreamAlias("refund_desc")
    private String refund_desc;

    /**
     * 退款资金来源
     * 必填: 否
     * 类型: string(30)
     * 示例值: REFUND_SOURCE_RECHARGE_FUNDS
     * 描述: 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    @XStreamAlias("refund_account")
    private String refund_account;

    /**
     * 退款结果通知url
     * 必填: 否
     * 类型: string(256)
     * 示例值: https://weixin.qq.com/notify/
     * 描述: 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
     */
    @XStreamAlias("notify_url")
    private String notify_url;

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

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @Override
    public void checkConstraints() throws WechatPayException {
        checkField();

        if (StringUtils.isAllEmpty(this.transaction_id, this.out_trade_no)) {
            throw new WechatPayException("申请退款时，[transaction_id] 和 [out_trade_no] 不能同时为空");
        }

//        if (this.total_fee <= 0) {
//            throw new WechatPayException("[total_fee] 的值必须大于0");
//        }
//
//        if (this.refund_fee <= 0) {
//            throw new WechatPayException("[refund_fee] 的值必须大于0");
//        }
//
//        if (this.total_fee < this.refund_fee) {
//            throw new WechatPayException(String.format("退款金额不能大于账单总金额,请检查. total_fee:[%s], refund_fee:[%s]", this.total_fee, this.refund_fee));
//        }
    }
}
