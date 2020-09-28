package com.xinput.wechat.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 20:04
 */
public abstract class BaseWeChatPayResponse {

    // 返回结果
    /**
     * 返回状态码
     * 必填: 是
     * 类型: String(16)
     * 示例值: SUCCESS
     * 描述: SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XStreamAlias("return_code")
    private String return_code;

    /**
     * 返回信息
     * 必填: 否
     * 类型: String(128)
     * 示例值: 签名失败
     * 描述: 返回信息，如非空，为错误原因: 签名失败、参数格式校验错误
     */
    @XStreamAlias("return_msg")
    private String return_msg;

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 小程序ID
     * 必填: 是
     * 类型: String(32)
     * 示例值: wx8888888888888888
     * 描述: 调用接口提交的小程序ID
     */
    @JsonIgnore
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1900000109
     * 描述: 调用接口提交的商户号
     */
    @JsonIgnore
    @XStreamAlias("mch_id")
    private String mch_id;

    /**
     * 随机字符串
     * 必填: 是
     * 类型: String(32)
     * 示例值: 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述: 随机字符串，长度要求在32位以内。推荐 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
     */
    @XStreamAlias("nonce_str")
    private String nonce_str;

    /**
     * 签名
     * 必填: 是
     * 类型: String(64)
     * 示例值: C380BEC2BFD727A4B6845133519F3AD6
     * 描述:  通过签名算法计算得出的签名值 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 业务结果
     * 必填: 是
     * 类型: String(16)
     * 示例值: SUCCESS
     * 描述: SUCCESS/FAIL
     */
    @XStreamAlias("result_code")
    private String result_code;

    /**
     * 错误代码
     * 必填: 否
     * 类型: String(32)
     * 示例值: SYSTEMERROR
     * 描述:
     */
    @XStreamAlias("err_code")
    private String err_code;

    /**
     * 错误代码描述
     * 必填: 否
     * 类型: String(128)
     * 示例值: 系统错误
     * 描述: 错误信息描述
     */
    @XStreamAlias("err_code_des")
    private String err_code_des;


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public abstract boolean isSuccess();

}
