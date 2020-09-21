package com.xinput.wechat.enums;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 17:24
 */
public enum PayUrlEnum {
    DOMAIN("https://api.mch.weixin.qq.com", "微信支付请求路径"),
    UNIFIED_ORDER("/pay/unifiedorder", "统一下单"),
    ORDER_QUERY("/pay/orderquery", "查询订单"),
    CLOSE_ORDER("/pay/closeorder", "关闭订单"),
    REFUND("/secapi/pay/refund", "申请退款"),
    REFUND_QUERY("/pay/refundquery", "查询退款"),
    DOWNLOAD_BILL("/pay/downloadbill", "下载交易账单"),
    DOWNLOAD_FUND_FLOW("/pay/downloadfundflow", "下载资金账单"),
    PAYITIL_REPORT("/payitil/report", "交易保障"),
    BILL_COMMENT("/billcommentsp/batchquerycomment", "拉取订单评价数据");

    private String url;

    private String desc;

    PayUrlEnum(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

}
