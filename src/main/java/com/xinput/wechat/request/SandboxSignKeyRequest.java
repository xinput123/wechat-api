package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechat.exception.WechatPayException;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-21 11:32
 */
@XStreamAlias("xml")
public class SandboxSignKeyRequest {

    @NotNull(message = "[mch_id] 不能为空")
    @XStreamAlias("mch_id")
    private String mch_id;

    @XStreamAlias("nonce_str")
    private String nonce_str;

    @XStreamAlias("sign_type")
    private String sign_type;

    @NotNull(message = "[sign] 不能为空")
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

    public void checkConstraints() throws WechatPayException {
        if (StringUtils.isNullOrEmpty(this.mch_id)) {

        }
    }
}
