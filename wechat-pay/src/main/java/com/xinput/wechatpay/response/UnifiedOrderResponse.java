package com.xinput.wechatpay.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechatpay.enums.TradeTypeEnum;

/**
 * 统一下单返回值
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-14 10:55
 */
@XStreamAlias("xml")
public class UnifiedOrderResponse extends BaseWeChatPayResponse {

    // 以下字段在return_code为SUCCESS的时候有返回
    /**
     * 设备号
     * 必填: 否
     * 类型: String(32)
     * 示例值: 013467007045764
     * 描述: 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("device_info")
    private String device_info;


    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /**
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: JSAPI
     * 描述: {@link TradeTypeEnum}
     */
    @XStreamAlias("trade_type")
    private String trade_type;

    /**
     * 预支付交易会话标识
     * 必填: 是
     * 类型: String(64)
     * 示例值: wx201410272009395522657a690389285100
     * 描述: 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias("prepay_id")
    private String prepay_id;

    /**
     * 二维码链接
     * 必填: 否
     * 类型: String(64)
     * 示例值: weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00
     * 描述: trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意: code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    @XStreamAlias("code_url")
    private String code_url;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
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
