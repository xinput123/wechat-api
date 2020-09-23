package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-21 11:50
 */
@XStreamAlias("xml")
public class SandboxSignKeyResponse {

    @XStreamAlias("return_code")
    private String returnCode;

    @XStreamAlias("return_msg")
    private String return_msg;

    @XStreamAlias("sandbox_signkey")
    private String sandbox_signkey;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getSandbox_signkey() {
        return sandbox_signkey;
    }

    public void setSandbox_signkey(String sandbox_signkey) {
        this.sandbox_signkey = sandbox_signkey;
    }
}
