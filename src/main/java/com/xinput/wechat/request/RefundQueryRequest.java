package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询退款 - 不需要证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:37
 */
@XStreamAlias("xml")
public class RefundQueryRequest extends BaseWeChatPayReq {

    /**
     * 微信订单号
     * 必填: 和【商户订单号】【商户退款单号】【微信退款单号】四选一
     * 类型: String(32)
     * 示例值: 1009660380201506130728806387
     * 描述: 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
     */
    @XStreamAlias("transaction_id")
    private String transaction_id;

    /**
     * 商户订单号
     * 必填: 和【微信订单号】【商户退款单号】【微信退款单号】四选一
     * 类型: String(32)
     * 示例值: 1009660380201506130728806387
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 商户退款单号
     * 必填: 和【微信订单号】【商户订单号】【微信退款单号】四选一
     * 类型: String(64)
     * 示例值: 1217752501201407033233368018
     * 描述: 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no")
    private String out_refund_no;

    /**
     * 微信退款单号
     * 必填: 和【微信订单号】【商户订单号】【商户退款单号】四选一
     * 类型: String(32)
     * 示例值: 2007752501201407033233368018
     * 描述: 微信生成的退款单号，在申请退款接口有返回
     */
    @XStreamAlias("refund_id")
    private String refund_id;

    /**
     * 偏移量
     * 必填: 否
     * 类型: Int
     * 示例值: 15
     * 描述: 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     */
    @XStreamAlias("offset")
    private Integer offset;

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "RefundQueryRequest{" +
                super.toString() + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", out_refund_no='" + out_refund_no + '\'' +
                ", refund_id='" + refund_id + '\'' +
                ", offset=" + offset +
                '}';
    }
}
