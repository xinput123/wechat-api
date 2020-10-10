package com.xinput.wechatpay.testcase;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.ObjectId;
import com.xinput.wechatpay.WechatPayApi;
import com.xinput.wechatpay.config.WechatConfig;
import com.xinput.wechatpay.request.UnifiedOrderRequest;
import com.xinput.wechatpay.response.OrderQueryResponse;
import com.xinput.wechatpay.response.UnifiedOrderResponse;
import com.xinput.wechatpay.result.OrderSignature;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

/**
 * 测试用例3-3: 1003-可选用例-公众号/APP/扫码正常支付
 *
 * <p>
 * 用例描述:
 * 订单金额 5.51 元，其中 0.01 元使用免充值券，实际支付 5.50 元。
 * 验证正常支付流程，商户使用免充值代金券支付
 *
 * <p>
 * 测试步骤
 * （（1）用客户端扫码,选择商品下单（此操作可选，若扫码时出现二维码过期等提示，则忽略该提示，不影响测试结果），
 * 调起微信支付交易确认页（支付时出现收银台 total_fee 错误可忽略，因为微信支付生产环境里不存在此笔沙箱订单，
 * 所以会有此错误提示），或直接组包调用统一下单 api（https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder)；
 * （2）根据商户内部单号（out_trade_no），调用查单 api （https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery）查询订单状态，与商户
 * 自有订单的关键信息进行核对。
 *
 * <p>
 * 预期返回
 */
public class Case3_3 {

    private static final Logger logger = Logs.get();

    private static final String openId = "o21I_5QRM2KroSRO_uvjR4X-3BHg";

    @Test
    public void test() {
        String outTradeNo = ObjectId.stringId();
        logger.info("outTradeNo : {}", outTradeNo);

        unifiedOrder(outTradeNo);
        orderQuery(outTradeNo);
    }

    public void unifiedOrder(String outTradeNo) {
        UnifiedOrderRequest request = BuilderUtils.of(UnifiedOrderRequest::new)
                // 商品名称
                .with(UnifiedOrderRequest::setBody, "嘉云升-测试用例3.3:551")
                // 获取客户端的ip地址
                .with(UnifiedOrderRequest::setSpbill_create_ip, "192.168.10.13")
                // 商户订单号,自己的订单ID
                .with(UnifiedOrderRequest::setOut_trade_no, outTradeNo)
                // 支付金额，这边需要转成字符串类型，否则后面的签名会失败
                .with(UnifiedOrderRequest::setTotal_fee, 551)
                // 支付成功后的回调地址
                .with(UnifiedOrderRequest::setNotify_url, WechatConfig.getWechatNotifyUnifiedOrderUrl())
                // 支付方式
                .with(UnifiedOrderRequest::setTrade_type, "JSAPI")
                // 用户的openID，自己获取
                .with(UnifiedOrderRequest::setOpenid, "2234")
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

    public void orderQuery(String outTradeNo) {
        try {
            OrderQueryResponse response = WechatPayApi.queryOrderByOutTradeNo(outTradeNo);
            logger.info("查询订单结果:{}", JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
