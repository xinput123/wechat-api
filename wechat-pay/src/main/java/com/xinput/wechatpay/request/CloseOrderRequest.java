package com.xinput.wechatpay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.wechatpay.exception.WechatPayException;

import javax.validation.constraints.NotEmpty;

/**
 * 关闭订单
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 22:28
 */
@XStreamAlias("xml")
public class CloseOrderRequest extends BaseWeChatPayRequest {

    @NotEmpty(message = "[out_trade_no] 不能为空")
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public void checkConstraints() throws WechatPayException {
        checkField();
    }
}
