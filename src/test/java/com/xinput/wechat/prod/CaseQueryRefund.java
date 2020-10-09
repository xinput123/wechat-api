package com.xinput.wechat.prod;import com.xinput.bleach.util.JsonUtils;import com.xinput.bleach.util.Logs;import com.xinput.wechat.WechatPayApi;import com.xinput.wechat.exception.WechatPayException;import com.xinput.wechat.response.RefundQueryResponse;import org.junit.jupiter.api.Test;import org.slf4j.Logger;/** * 查询退款 */public class CaseQueryRefund {    private static final Logger logger = Logs.get();    private static final String transactionId = "4200000764202009188340047847";    private static final String outTradeNo = "5f646c8d280319c40fde6bf9";    private static final String refundId = "50300705602020100903170066000";    private static final String outRefundNo = "5f8013f6d10ae598aa50366f";    @Test    public void queryRefund01() throws WechatPayException {        RefundQueryResponse response = WechatPayApi.refundQueryByTransactionId(transactionId);        logger.info(JsonUtils.toJsonString(response, true));    }    @Test    public void queryRefund02() throws WechatPayException {        RefundQueryResponse response = WechatPayApi.refundQueryByOutTradeNo(outTradeNo);        logger.info(JsonUtils.toJsonString(response, true));    }    @Test    public void queryRefund03() throws WechatPayException {        RefundQueryResponse response = WechatPayApi.refundQueryByRefundId(refundId);        logger.info(JsonUtils.toJsonString(response, true));    }    @Test    public void queryRefund04() throws WechatPayException {        RefundQueryResponse response = WechatPayApi.refundQueryByOutRefundNo(outRefundNo);        logger.info(JsonUtils.toJsonString(response, true));    }    @Test    public void queryRefund05() throws WechatPayException {        String refundId = "50300705602020091802766031606";        RefundQueryResponse response = WechatPayApi.refundQueryByRefundId(refundId);        logger.info(JsonUtils.toJsonString(response, true));    }}