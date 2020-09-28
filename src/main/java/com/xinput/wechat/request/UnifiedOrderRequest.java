package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.xinput.bleach.util.xml.XStreamCDataConverter;

/**
 * 统一下单 - 不需要证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-14 10:55
 */
@XStreamAlias("xml")
public class UnifiedOrderRequest extends BaseWeChatPayRequest {

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
     * 标价币种
     * 必填: 否
     * 类型: String(32)
     * 示例值: CNY
     * 描述: 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @XStreamAlias("fee_type")
    private String fee_type;

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
     * 终端IP
     * 必填: 是
     * 类型: String(64)
     * 示例值: 123.12.12.123
     * 描述: 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbill_create_ip;

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
     * 订单优惠标记
     * 必填: 否
     * 类型: String(32)
     * 示例值: WXG
     * 描述: 订单优惠标记，使用代金券或立减优惠功能时需要的参数
     */
    @XStreamAlias("goods_tag")
    private String goods_tag;

    /**
     * 通知地址
     * 必填: 是
     * 类型: String(256)
     * 示例值: http://www.weixin.qq.com/wxpay/pay.php
     * 描述: 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @XStreamAlias("notify_url")
    private String notify_url;

    /**
     * 交易类型
     * 必填: 是
     * 类型: String(16)
     * 示例值: JSAPI
     * 描述: 小程序取值如下：JSAPI。 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
     */
    @XStreamAlias("trade_type")
    private String trade_type;

    /**
     * 商品ID
     * 必填: 否
     * 类型: String(16)
     * 示例值: 12235413214070356458058
     * 描述:
     */
    @XStreamAlias("product_id")
    private String product_id;

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
     * 用户标识
     * 必填: 否
     * 类型: String(128)
     * 示例值: oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 描述: trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 电子发票入口开放标识
     * 必填: 否
     * 类型: String(8)
     * 示例值: Y
     * 描述: 传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @XStreamAlias("receipt")
    private String receipt;

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

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
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

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {

        return "UnifiedOrderRequest{" +
                super.toString() + '\'' +
                ", device_info='" + device_info + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", total_fee=" + total_fee +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", time_start='" + time_start + '\'' +
                ", time_expire='" + time_expire + '\'' +
                ", goods_tag='" + goods_tag + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", product_id='" + product_id + '\'' +
                ", limit_pay='" + limit_pay + '\'' +
                ", openid='" + openid + '\'' +
                ", receipt='" + receipt + '\'' +
                '}';
    }
}
