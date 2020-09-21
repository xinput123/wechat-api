package com.xinput.wechat.response.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 20:04
 */
public class BaseWeChatPayResp {

    // 返回结果
    /**
     * 返回状态码
     * 必填: 是
     * 类型: String(16)
     * 示例值: SUCCESS
     * 描述: SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息
     * 必填: 否
     * 类型: String(128)
     * 示例值: 签名失败
     * 描述: 返回信息，如非空，为错误原因: 签名失败、参数格式校验错误
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 小程序ID
     * 必填: 是
     * 类型: String(32)
     * 示例值: wx8888888888888888
     * 描述: 调用接口提交的小程序ID
     */
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 1900000109
     * 描述: 调用接口提交的商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串
     * 必填: 是
     * 类型: String(32)
     * 示例值: 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述: 随机字符串，长度要求在32位以内。推荐 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

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
    private String resultCode;

    /**
     * 错误代码
     * 必填: 否
     * 类型: String(32)
     * 示例值: SYSTEMERROR
     * 描述:
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     * 必填: 否
     * 类型: String(128)
     * 示例值: 系统错误
     * 描述: 错误信息描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDesc;


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDesc() {
        return errCodeDesc;
    }

    public void setErrCodeDesc(String errCodeDesc) {
        this.errCodeDesc = errCodeDesc;
    }

}
