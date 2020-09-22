package com.xinput.wechat.request.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-21 11:32
 */
@XStreamAlias("xml")
public class SandboxSignKeyRequest {

    @XStreamAlias("mch_id")
    private String mch_id;

    @XStreamAlias("nonce_str")
    private String nonce_str;

    @XStreamAlias("sign_type")
    private String sign_type;

    @XStreamAlias("sign")
    private String sign;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
