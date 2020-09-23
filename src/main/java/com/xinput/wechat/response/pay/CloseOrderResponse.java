package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;

/**
 * 关闭订单响应值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:22
 */
@XStreamAlias("xml")
public class CloseOrderResponse extends BaseWeChatPayResp {

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 业务结果描述
     * 必填: 是
     * 类型: String(32)
     * 示例值: OK
     * 描述: 对于业务执行的详细描述
     */
    @XStreamAlias("result_msg")
    private String result_msg;

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
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
