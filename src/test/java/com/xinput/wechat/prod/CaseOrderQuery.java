package com.xinput.wechat.prod;import com.xinput.bleach.util.BuilderUtils;import com.xinput.bleach.util.JsonUtils;import com.xinput.wechat.WechatPayApi;import com.xinput.wechat.exception.WechatPayException;import com.xinput.wechat.request.OrderQueryRequest;import com.xinput.wechat.response.OrderQueryResponse;import org.junit.jupiter.api.Test;/** * 订单查询，不包括优惠券之类 */public class CaseOrderQuery {    /**     * 根据微信订单号查询订单信息     */    @Test    public void query01() throws WechatPayException {        OrderQueryResponse response = WechatPayApi.queryOrderByTransactionId("4200000764202009188340047847");        System.out.println(JsonUtils.toJsonString(response, true));    }    /**     * 根据商家订单号查询订单信息     */    @Test    public void query02() throws WechatPayException {        OrderQueryResponse response = WechatPayApi.queryOrderByOutTradeNo("5f646c8d280319c40fde6bf9");        System.out.println(JsonUtils.toJsonString(response, true));    }    @Test    public void query03() throws WechatPayException {        OrderQueryRequest orderQueryRequest = BuilderUtils.of(OrderQueryRequest::new)                .with(OrderQueryRequest::setTransaction_id, "4200000764202009188340047847")                .with(OrderQueryRequest::setOut_trade_no, "5f646c8d280319c40fde6bf9")                .build();        OrderQueryResponse response = WechatPayApi.queryOrder(orderQueryRequest);        System.out.println(JsonUtils.toJsonString(response, true));    }}