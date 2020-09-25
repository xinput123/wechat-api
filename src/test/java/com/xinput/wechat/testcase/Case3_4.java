package com.xinput.wechat.testcase;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.ObjectId;
import com.xinput.wechat.WechatPayApi;
import com.xinput.wechat.request.pay.RefundQueryRequest;
import com.xinput.wechat.request.pay.UnifiedOrderRequest;
import com.xinput.wechat.response.pay.RefundQueryResponse;
import com.xinput.wechat.response.pay.RefundResponse;
import com.xinput.wechat.response.pay.UnifiedOrderResponse;
import com.xinput.wechat.result.OrderSignature;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * 【1004-可选用例-公众号/APP/扫码支付退款
 * <p>
 * 订单金额 5.52 元，其中 0.01 元使用免充值券，实际支付 5.51 元。
 * <p>
 * 测试步骤
 * （1）进行 5.52 元的订单支付；
 * （2）根据商户内部单号（out_trade_no），调查单 api 进行查询，与商户自有订单的关键信息进行核对；
 * （3）根据商户内部单号（out_trade_no），调用退款 api 进行退款；
 * （4）根据商户内部单号（out_trade_no），调用退款查询 api 查询退款结果。
 */
public class Case3_4 {

    private static final Logger logger = Logs.get();

    private static final String openId = "o21I_5QRM2KroSRO_uvjR4X-3BHg";

    @Test
    public void T3_4() {
        String outTradeNo = ObjectId.stringId();
        outTradeNo = "5f6c3aaf6b5adf84b8b34098";
//        unifiedOrder(outTradeNo);
//        refund(outTradeNo);
        refundquery(outTradeNo);
    }

    /**
     * 统一下单
     */
    public void unifiedOrder(String outTradeNo) {
        UnifiedOrderRequest request = BuilderUtils.of(UnifiedOrderRequest::new)
                // 商品名称
                .with(UnifiedOrderRequest::setBody, "嘉云升-552")
                // 获取客户端的ip地址
                .with(UnifiedOrderRequest::setSpbill_create_ip, "192.168.10.13")
                .with(UnifiedOrderRequest::setSign_type, "MD5")
                // 商户订单号,自己的订单ID
                .with(UnifiedOrderRequest::setOut_trade_no, outTradeNo)
                // 支付金额，这边需要转成字符串类型，否则后面的签名会失败
                .with(UnifiedOrderRequest::setTotal_fee, 552)
                // 支付方式
                .with(UnifiedOrderRequest::setTrade_type, "JSAPI")
                // 用户的openID，自己获取
                .with(UnifiedOrderRequest::setOpenid, openId)
                .with(UnifiedOrderRequest::setDevice_info, "sandbox")
                .build();
        try {
            UnifiedOrderResponse response = WechatPayApi.unifiedOrder(request);
            logger.info("UnifiedOrderResponse : {}", JsonUtils.toJsonString(response, true));

            // 再次签名
            OrderSignature orderSignature = new OrderSignature(response);
            logger.info("orderSignature : " + JsonUtils.toJsonString(orderSignature, true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refund(String outTradeNo) {
        try {
            RefundResponse response = WechatPayApi.refund(outTradeNo, 502, 502);
            logger.info(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询退款
     */
    public void refundquery(String outTradeNo) {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setOut_trade_no, outTradeNo)
                .with(RefundQueryRequest::setOffset, 10)
                .build();
        logger.info("查询退款:{}.", JsonUtils.toJsonString(request));

        try {
            RefundQueryResponse response = WechatPayApi.refundQuery(request);
            logger.info(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
