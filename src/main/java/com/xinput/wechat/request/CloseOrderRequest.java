package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关闭订单 - 不需要证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 22:28
 */
@XStreamAlias("xml")
public class CloseOrderRequest extends BaseWeChatPayRequest {

    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public String toString() {
        return "CloseOrderRequest{" +
                super.toString() + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                '}';
    }
}
