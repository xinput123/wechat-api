package com.xinput.wechatpay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechatpay.exception.WechatPayException;

/**
 * 查询订单参数 - 不需要证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 20:01
 */
@XStreamAlias("xml")
public class OrderQueryRequest extends BaseWeChatPayRequest {

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

    @Override
    public void checkConstraints() throws WechatPayException {
        checkField();

        if (StringUtils.isAllEmpty(this.transaction_id, this.out_trade_no)) {
            throw new WechatPayException("[transaction_id] 和 [out_trade_no] 不能同时为空");
        }

    }
}
