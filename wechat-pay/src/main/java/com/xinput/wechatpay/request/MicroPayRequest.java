package com.xinput.wechatpay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.xinput.bleach.util.xml.XStreamCDataConverter;
import com.xinput.wechatpay.exception.WechatPayException;

/**
 * 付款码支付 - 不需要证书
 * <p>
 * 收银员使用扫码设备读取微信用户付款码以后，二维码或条码信息会传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
 * <p>
 * 提醒1：提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查
 * 询支付实际交易结果；当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或
 * 超时(建议30秒)；
 * <p>
 * 提醒2：在调用查询接口返回后，如果交易状况不明晰，请调用【撤销订单API】，此时如果交易失败则关闭订单，该单不能再支付
 * 成功；如果交易成功，则将扣款退回到用户账户。当撤销无返回或错误时，请再次调用。注意：请勿扣款后立即调用【撤销订
 * 单API】,建议至少15秒后再调用。撤销订单API需要双向证书。
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-14 10:55
 */
@XStreamAlias("xml")
public class MicroPayRequest extends BaseWeChatPayRequest {

    /**
     * 设备号
     * 必填: 否
     * 类型: String(32)
     * 示例值: 013467007045764
     * 描述: 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("device_info")
    private String device_info;

    /**
     * 商品描述
     * 必填: 是
     * 类型: String(128)
     * 示例值: 腾讯充值中心-QQ会员充值
     * 描述: 商品简单描述，该字段请按照规范传递，具体请见参数规定 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 商品详情
     * 必填: 否
     * 类型: String(6000)
     * 示例值:
     * 描述: 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传 https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     */
    @XStreamAlias("detail")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String detail;

    /**
     * 附加数据
     * 必填: 否
     * 类型: String(128)
     * 示例值: 深圳分店
     * 描述: 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 商户订单号
     * 必填: 是
     * 类型: String(32)
     * 示例值: 20150806125346
     * 描述: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("out_trade_no")
    private String out_trade_no;

    /**
     * 标价金额
     * 必填: 是
     * 类型: Integer
     * 示例值: 88
     * 描述: 订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    private Integer total_fee;

    /**
     * 标价币种
     * 必填: 否
     * 类型: String(32)
     * 示例值: CNY
     * 描述: 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("fee_type")
    private String fee_type;

    /**
     * 终端IP
     * 必填: 是
     * 类型: String(64)
     * 示例值: 123.12.12.123
     * 描述: 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbill_create_ip;

    /**
     * 订单优惠标记
     * 必填: 否
     * 类型: String(32)
     * 示例值: WXG
     * 描述: 订单优惠标记，使用代金券或立减优惠功能时需要的参数
     */
    @XStreamAlias("goods_tag")
    private String goods_tag;

    /**
     * 指定支付方式
     * 必填: 否
     * 类型: String(32)
     * 示例值: no_credit
     * 描述: 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    @XStreamAlias("limit_pay")
    private String limit_pay;

    /**
     * 交易起始时间
     * 必填: 否
     * 类型: String(14)
     * 示例值: 20091225091010
     * 描述: 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("time_start")
    private String time_start;

    /**
     * 交易结束时间
     * 必填: 否
     * 类型: String(14)
     * 示例值: 20091227091010
     * 描述: 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
     * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。
     * 建议：最短失效时间间隔大于1分钟
     */
    @XStreamAlias("time_expire")
    private String time_expire;

    /**
     * 电子发票入口开放标识
     * 必填: 否
     * 类型: String(8)
     * 示例值: Y
     * 描述: 传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @XStreamAlias("receipt")
    private String receipt;

    /**
     * 付款码
     * 必填: 是
     * 类型: String(128)
     * 示例值: 120061098828009406
     * 描述: 扫码支付付款码，设备读取用户微信中的条码或者二维码信息
     * （注：用户付款码条形码规则：18位纯数字，以10、11、12、13、14、15开头）
     */
    @XStreamAlias("auth_code")
    private String auth_code;

//    /**
//     * 场景信息
//     * 必填: 否
//     * 类型: String(32)
//     * 示例值: {"store_info" : { "id": "SZTX001", "name": "腾大餐厅", "area_code": "440305", "address": "科技园中一路腾讯大厦" }}
//     * 描述: 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据
//     */
//    private String sceneInfo;


    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    @Override
    public void checkConstraints() throws WechatPayException {

    }
}
