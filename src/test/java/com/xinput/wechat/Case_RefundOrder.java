package com.xinput.wechat;import com.xinput.bleach.util.BuilderUtils;import com.xinput.bleach.util.JsonUtils;import com.xinput.bleach.util.Logs;import com.xinput.wechat.exception.WechatPayException;import com.xinput.wechat.request.RefundRequest;import com.xinput.wechat.response.RefundResponse;import org.junit.jupiter.api.Test;import org.slf4j.Logger;/** * @author <a href="mailto:xinput.xx@gmail.com">xinput</a> * @date 2020-09-29 21:27 */public class Case_RefundOrder {    private static final Logger logger = Logs.get();    private static final String transaction_id = "4436436506420200929185248653427";    private static final String out_trade_no = "5f7311da6b5adf9bcfafc3ba";    @Test    public void closeOrder() throws WechatPayException {    }    @Test    public void closeOrder2() throws WechatPayException {        String outTradeNo = "5f6991d36b5adfaa6b9783e6"; // 101        RefundRequest refundRequest = BuilderUtils.of(RefundRequest::new)                .with(RefundRequest::setOut_trade_no, outTradeNo)                .build();        RefundResponse response = WechatPayApi.refund(refundRequest);        logger.info(JsonUtils.toJsonString(response, true));    }}